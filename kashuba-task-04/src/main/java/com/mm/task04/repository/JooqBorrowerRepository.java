package com.mm.task04.repository;

import static com.mm.task04.db.tables.Borrower.BORROWER;
import static com.mm.task04.db.tables.BorrowerDocument.BORROWER_DOCUMENT;
import static com.mm.task04.db.tables.Credit.CREDIT;
import static com.mm.task04.db.tables.PersonalData.PERSONAL_DATA;
import static com.mm.task04.db.tables.UserAccount.USER_ACCOUNT;
import static com.mm.task04.db.tables.Work.WORK;
import static org.jooq.impl.DSL.max;

import com.mm.task04.db.tables.records.BorrowerRecord;
import com.mm.task04.model.BorrowerModel;
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
                    .insertInto(BORROWER)
                    .set(BORROWER.PERSONAL_DATA_ID, result.getT1())
                    .set(BORROWER.WORK_ID, result.getT2())
                    .set(BORROWER.USER_ACCOUNT_ID, borrowerModel.getUserAccountId())
                    .returning(BORROWER.ID)
                    .fetchOne()
                    .getId()));
  }

  @Override
  public Mono<BorrowerModel> get(Long id) {
    return Mono.fromSupplier(() -> dslContext
        .select(BORROWER.ID,
            PERSONAL_DATA.ID.as("personalData.id"),
            PERSONAL_DATA.BIRTHDAY.as("personalData.birthday"),
            PERSONAL_DATA.FIRST_NAME.as("personalData.firstName"),
            PERSONAL_DATA.LAST_NAME.as("personalData.lastName"),
            PERSONAL_DATA.BIRTHPLACE.as("personalData.birthplace"),
            PERSONAL_DATA.MARITALSTATUS.as("personalData.maritalStatus"),
            BORROWER.USER_ACCOUNT_ID.as("borrower.userAccountId"),
            WORK.ID.as("work.id"),
            WORK.SALARY.as("work.salary"),
            WORK.EDUCATION.as("work.education"),
            WORK.NEXT_INCOME_DATE.as("work.nextIncomeDate"))
        .from(BORROWER)
        .join(PERSONAL_DATA).on(PERSONAL_DATA.ID.eq(BORROWER.PERSONAL_DATA_ID))
        .join(WORK).on(WORK.ID.eq(BORROWER.WORK_ID))
        .where(BORROWER.ID.eq(id))
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
        .select(BORROWER.asterisk().except(BORROWER.ID),
            PERSONAL_DATA.asterisk().except(PERSONAL_DATA.ID),
            USER_ACCOUNT.asterisk().except(USER_ACCOUNT.ID),
            WORK.asterisk().except(WORK.ID))
        .from(BORROWER)
        .join(PERSONAL_DATA).on(BORROWER.PERSONAL_DATA_ID.eq(PERSONAL_DATA.ID))
        .join(USER_ACCOUNT).on(BORROWER.USER_ACCOUNT_ID.eq(USER_ACCOUNT.ID))
        .join(WORK).on(BORROWER.WORK_ID.eq(WORK.ID))
        .where(BORROWER.ID.eq(id))
        .fetchOne()
        .intoMap());
  }

  /**
   * Return partial data about borrower. Data contain: id, birthplace, marital status, work next income date.
   */
  @Override
  public Mono<Map<String, Object>> getPartialBorrowerInfo(Long id) {
    return Mono.fromSupplier(() -> dslContext
        .select(BORROWER.ID,
            PERSONAL_DATA.BIRTHPLACE,
            PERSONAL_DATA.MARITALSTATUS,
            WORK.NEXT_INCOME_DATE)
        .from(BORROWER)
        .join(PERSONAL_DATA).on(BORROWER.PERSONAL_DATA_ID.eq(PERSONAL_DATA.ID))
        .join(USER_ACCOUNT).on(BORROWER.USER_ACCOUNT_ID.eq(USER_ACCOUNT.ID))
        .join(WORK).on(BORROWER.WORK_ID.eq(WORK.ID))
        .where(BORROWER.ID.eq(id))
        .fetchOne()
        .intoMap());
  }

  /**
   * Return birthplace of borrower who has the highest salary.
   */
  @Override
  public Mono<String> getBirthplaceOfRichBorrower() {
    return Mono.fromSupplier(() -> dslContext
        .selectDistinct(PERSONAL_DATA.BIRTHPLACE)
        .from(PERSONAL_DATA)
        .join(BORROWER).on(BORROWER.PERSONAL_DATA_ID.eq(PERSONAL_DATA.ID))
        .join(WORK).on(WORK.ID.eq(BORROWER.WORK_ID))
        .where(WORK.SALARY.eq(dslContext.select(max(WORK.SALARY)).from(WORK)))
        .fetchOne()
        .get(PERSONAL_DATA.BIRTHPLACE));
  }

  /**
   * Return all borrowers who have education and credit status from parameter, default values education = 'HIGHER',
   * credit status = 'CANCELLED'.
   */
  @Override
  public Flux<BorrowerModel> getFilteredBorrowers(Education education, CreditStatus creditStatus) {
    return Flux.defer(() -> Flux.fromIterable(dslContext
        .select(BORROWER.ID,
            PERSONAL_DATA.ID.as("personalData.id"),
            PERSONAL_DATA.BIRTHDAY.as("personalData.birthday"),
            PERSONAL_DATA.FIRST_NAME.as("personalData.firstName"),
            PERSONAL_DATA.LAST_NAME.as("personalData.lastName"),
            PERSONAL_DATA.BIRTHPLACE.as("personalData.birthplace"),
            PERSONAL_DATA.MARITALSTATUS.as("personalData.maritalStatus"),
            BORROWER.USER_ACCOUNT_ID.as("borrower.userAccountId"),
            WORK.ID.as("work.id"),
            WORK.SALARY.as("work.salary"),
            WORK.EDUCATION.as("work.education"),
            WORK.NEXT_INCOME_DATE.as("work.nextIncomeDate"))
        .from(BORROWER)
        .join(WORK).on(WORK.ID.eq(BORROWER.WORK_ID))
        .join(PERSONAL_DATA).on(PERSONAL_DATA.ID.eq(BORROWER.PERSONAL_DATA_ID))
        .join(CREDIT).on(CREDIT.BORROWER_ID.eq(BORROWER.ID))
        .where(WORK.EDUCATION.eq(education))
        .and(CREDIT.STATUS.eq(creditStatus))
        .fetch()
        .into(BorrowerModel.class)));
  }

  @Override
  public Mono<Void> delete(long id) {
    return Mono.defer(() -> {
      BorrowerRecord borrowerRecord = dslContext.fetchOne(BORROWER, BORROWER.ID.eq(id));
      dslContext
          .deleteFrom(CREDIT)
          .where(CREDIT.BORROWER_ID.eq(id))
          .execute();
      dslContext
          .deleteFrom(BORROWER_DOCUMENT)
          .where(BORROWER_DOCUMENT.BORROWER_ID.eq(id))
          .execute();
      dslContext
          .deleteFrom(BORROWER)
          .where(BORROWER.ID.eq(id))
          .execute();
      dslContext
          .deleteFrom(PERSONAL_DATA)
          .where(PERSONAL_DATA.ID.eq(borrowerRecord.getPersonalDataId()))
          .execute();
      dslContext
          .deleteFrom(WORK)
          .where(WORK.ID.eq(borrowerRecord.getWorkId()))
          .execute();
      dslContext
          .deleteFrom(USER_ACCOUNT)
          .where(USER_ACCOUNT.ID.eq(borrowerRecord.getUserAccountId()))
          .execute();
      return Mono.empty();
    });
  }
}

