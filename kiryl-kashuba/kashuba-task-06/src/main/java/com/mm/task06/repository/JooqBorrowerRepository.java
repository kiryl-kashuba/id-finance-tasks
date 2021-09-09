package com.mm.task06.repository;

import static com.mm.task06.db.tables.BorrowerDocument.BORROWER_DOCUMENT;
import static org.jooq.impl.DSL.max;

import com.mm.task06.db.tables.Borrower;
import com.mm.task06.db.tables.Credit;
import com.mm.task06.db.tables.PersonalData;
import com.mm.task06.db.tables.UserAccount;
import com.mm.task06.db.tables.Work;
import com.mm.task06.db.tables.records.BorrowerRecord;
import com.mm.task06.model.BorrowerModel;
import com.mm.types.CreditStatus;
import com.mm.types.Education;
import java.util.Map;
import org.jooq.DSLContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class JooqBorrowerRepository implements BorrowerRepository {

  private final DSLContext dslContext;
  private final PersonalDataRepository personalDataRepository;
  private final WorkRepository workRepository;

  public JooqBorrowerRepository(DSLContext dslContext,
      PersonalDataRepository personalDataRepository,
      WorkRepository workRepository) {
    this.dslContext = dslContext;
    this.personalDataRepository = personalDataRepository;
    this.workRepository = workRepository;
  }

  @Override
  public Mono<Long> create(BorrowerModel borrowerModel) {
    return Mono.defer(() ->
        Mono.zip(personalDataRepository.create(borrowerModel.getPersonalDataModel()),
                workRepository.create(borrowerModel.getWorkModel()))
            .map(result ->
                dslContext
                    .insertInto(Borrower.BORROWER)
                    .set(Borrower.BORROWER.PERSONAL_DATA_ID, result.getT1())
                    .set(Borrower.BORROWER.WORK_ID, result.getT2())
                    .set(Borrower.BORROWER.USER_ACCOUNT_ID, borrowerModel.getUserAccountId())
                    .returning(Borrower.BORROWER.ID)
                    .fetchOne()
                    .getId()));
  }

  @Override
  public Mono<BorrowerModel> get(Long id) {
    return Mono.fromSupplier(() -> dslContext
        .select(Borrower.BORROWER.ID,
            PersonalData.PERSONAL_DATA.ID.as("personalData.id"),
            PersonalData.PERSONAL_DATA.BIRTHDAY.as("personalData.birthday"),
            PersonalData.PERSONAL_DATA.FIRST_NAME.as("personalData.firstName"),
            PersonalData.PERSONAL_DATA.LAST_NAME.as("personalData.lastName"),
            PersonalData.PERSONAL_DATA.BIRTHPLACE.as("personalData.birthplace"),
            PersonalData.PERSONAL_DATA.MARITALSTATUS.as("personalData.maritalStatus"),
            Borrower.BORROWER.USER_ACCOUNT_ID.as("borrower.userAccountId"),
            Work.WORK.ID.as("work.id"),
            Work.WORK.SALARY.as("work.salary"),
            Work.WORK.EDUCATION.as("work.education"),
            Work.WORK.NEXT_INCOME_DATE.as("work.nextIncomeDate"))
        .from(Borrower.BORROWER)
        .join(PersonalData.PERSONAL_DATA).on(PersonalData.PERSONAL_DATA.ID.eq(Borrower.BORROWER.PERSONAL_DATA_ID))
        .join(Work.WORK).on(Work.WORK.ID.eq(Borrower.BORROWER.WORK_ID))
        .where(Borrower.BORROWER.ID.eq(id))
        .fetchOne()
        .into(BorrowerModel.class));
  }

  /**
   * Return all data about borrower without credit. Data contain: id, first name, last name, birthday, birthplace,
   * marital status, date creation, email, mobile phone, name.
   */
  @Override
  public Mono<Map<String, Object>> getFullBorrowerInfo(Long id) {
    return Mono.fromSupplier(() -> dslContext
        .select(Borrower.BORROWER.asterisk().except(Borrower.BORROWER.ID),
            PersonalData.PERSONAL_DATA.asterisk().except(PersonalData.PERSONAL_DATA.ID),
            UserAccount.USER_ACCOUNT.asterisk().except(UserAccount.USER_ACCOUNT.ID),
            Work.WORK.asterisk().except(Work.WORK.ID))
        .from(Borrower.BORROWER)
        .join(PersonalData.PERSONAL_DATA).on(Borrower.BORROWER.PERSONAL_DATA_ID.eq(PersonalData.PERSONAL_DATA.ID))
        .join(UserAccount.USER_ACCOUNT).on(Borrower.BORROWER.USER_ACCOUNT_ID.eq(UserAccount.USER_ACCOUNT.ID))
        .join(Work.WORK).on(Borrower.BORROWER.WORK_ID.eq(Work.WORK.ID))
        .where(Borrower.BORROWER.ID.eq(id))
        .fetchOne()
        .intoMap());
  }

  /**
   * Return partial data about borrower. Data contain: id, birthplace, marital status, work next income date.
   */
  @Override
  public Mono<Map<String, Object>> getPartialBorrowerInfo(Long id) {
    return Mono.fromSupplier(() -> dslContext
        .select(Borrower.BORROWER.ID,
            PersonalData.PERSONAL_DATA.BIRTHPLACE,
            PersonalData.PERSONAL_DATA.MARITALSTATUS,
            Work.WORK.NEXT_INCOME_DATE)
        .from(Borrower.BORROWER)
        .join(PersonalData.PERSONAL_DATA).on(Borrower.BORROWER.PERSONAL_DATA_ID.eq(PersonalData.PERSONAL_DATA.ID))
        .join(UserAccount.USER_ACCOUNT).on(Borrower.BORROWER.USER_ACCOUNT_ID.eq(UserAccount.USER_ACCOUNT.ID))
        .join(Work.WORK).on(Borrower.BORROWER.WORK_ID.eq(Work.WORK.ID))
        .where(Borrower.BORROWER.ID.eq(id))
        .fetchOne()
        .intoMap());
  }

  /**
   * Return birthplace of borrower who has the highest salary.
   */
  @Override
  public Mono<String> getBirthplaceOfRichBorrower() {
    return Mono.fromSupplier(() -> dslContext
        .selectDistinct(PersonalData.PERSONAL_DATA.BIRTHPLACE)
        .from(PersonalData.PERSONAL_DATA)
        .join(Borrower.BORROWER).on(Borrower.BORROWER.PERSONAL_DATA_ID.eq(PersonalData.PERSONAL_DATA.ID))
        .join(Work.WORK).on(Work.WORK.ID.eq(Borrower.BORROWER.WORK_ID))
        .where(Work.WORK.SALARY.eq(dslContext.select(max(Work.WORK.SALARY)).from(Work.WORK)))
        .fetchOne()
        .get(PersonalData.PERSONAL_DATA.BIRTHPLACE));
  }

  /**
   * Return all borrowers who have education and credit status from parameter, default values education = 'HIGHER',
   * credit status = 'CANCELLED'.
   */
  @Override
  public Flux<BorrowerModel> getFilteredBorrowers(Education education, CreditStatus creditStatus) {
    return Flux.defer(() -> Flux.fromIterable(dslContext
        .select(Borrower.BORROWER.ID,
            PersonalData.PERSONAL_DATA.ID.as("personalData.id"),
            PersonalData.PERSONAL_DATA.BIRTHDAY.as("personalData.birthday"),
            PersonalData.PERSONAL_DATA.FIRST_NAME.as("personalData.firstName"),
            PersonalData.PERSONAL_DATA.LAST_NAME.as("personalData.lastName"),
            PersonalData.PERSONAL_DATA.BIRTHPLACE.as("personalData.birthplace"),
            PersonalData.PERSONAL_DATA.MARITALSTATUS.as("personalData.maritalStatus"),
            Borrower.BORROWER.USER_ACCOUNT_ID.as("borrower.userAccountId"),
            Work.WORK.ID.as("work.id"),
            Work.WORK.SALARY.as("work.salary"),
            Work.WORK.EDUCATION.as("work.education"),
            Work.WORK.NEXT_INCOME_DATE.as("work.nextIncomeDate"))
        .from(Borrower.BORROWER)
        .join(Work.WORK).on(Work.WORK.ID.eq(Borrower.BORROWER.WORK_ID))
        .join(PersonalData.PERSONAL_DATA).on(PersonalData.PERSONAL_DATA.ID.eq(Borrower.BORROWER.PERSONAL_DATA_ID))
        .join(Credit.CREDIT).on(Credit.CREDIT.BORROWER_ID.eq(Borrower.BORROWER.ID))
        .where(Work.WORK.EDUCATION.eq(education))
        .and(Credit.CREDIT.STATUS.eq(creditStatus))
        .fetch()
        .into(BorrowerModel.class)));
  }

  @Override
  public Mono<Void> delete(long id) {
    return Mono.defer(() -> {
      BorrowerRecord borrowerRecord = dslContext.fetchOne(Borrower.BORROWER, Borrower.BORROWER.ID.eq(id));
      dslContext
          .deleteFrom(Credit.CREDIT)
          .where(Credit.CREDIT.BORROWER_ID.eq(id))
          .execute();
      dslContext
          .deleteFrom(BORROWER_DOCUMENT)
          .where(BORROWER_DOCUMENT.BORROWER_ID.eq(id))
          .execute();
      dslContext
          .deleteFrom(Borrower.BORROWER)
          .where(Borrower.BORROWER.ID.eq(id))
          .execute();
      dslContext
          .deleteFrom(PersonalData.PERSONAL_DATA)
          .where(PersonalData.PERSONAL_DATA.ID.eq(borrowerRecord.getPersonalDataId()))
          .execute();
      dslContext
          .deleteFrom(Work.WORK)
          .where(Work.WORK.ID.eq(borrowerRecord.getWorkId()))
          .execute();
      dslContext
          .deleteFrom(UserAccount.USER_ACCOUNT)
          .where(UserAccount.USER_ACCOUNT.ID.eq(borrowerRecord.getUserAccountId()))
          .execute();
      return Mono.empty();
    });
  }
}

