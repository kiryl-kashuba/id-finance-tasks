package com.mm.task04.repository;

import static com.mm.task04.db.tables.Borrower.BORROWER;
import static com.mm.task04.db.tables.Credit.CREDIT;
import static com.mm.task04.db.tables.Work.WORK;
import static org.jooq.impl.DSL.count;

import com.mm.task04.converter.CreditConverter;
import com.mm.task04.db.tables.pojos.Credit;
import com.mm.task04.model.CreditModel;
import com.mm.types.Education;
import java.util.Map;
import org.jooq.DSLContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class JooqCreditRepository implements CreditRepository {

  private final DSLContext dslContext;
  private final CreditConverter creditConverter;

  public JooqCreditRepository(DSLContext dslContext, CreditConverter creditConverter) {
    this.dslContext = dslContext;
    this.creditConverter = creditConverter;
  }

  @Override
  public Mono<Long> create(CreditModel creditModel) {
    return Mono.fromSupplier(() -> dslContext
        .insertInto(CREDIT)
        .set(dslContext.newRecord(CREDIT, creditConverter.convertFromModel(creditModel)))
        .returning(CREDIT.ID)
        .fetchOne()
        .getId());
  }

  /**
   * Return count of credits for all educations.
   */
  @Override
  public Flux<CreditModel> getList(long borrowerId) {
    return Flux.defer(() -> Flux.fromIterable(dslContext
        .select()
        .from(CREDIT)
        .where(CREDIT.BORROWER_ID.eq(borrowerId))
        .fetch()
        .into(Credit.class))
        .map(creditConverter::convertToModel));
  }

  /**
   * Return count of credits for all educations.
   */
  @Override
  public Flux<Map<Education, Integer>> getCountOfCredits() {
    return Flux.defer(() -> Flux.just(dslContext
        .select(WORK.EDUCATION, count(CREDIT))
        .from(WORK)
        .join(BORROWER).on(BORROWER.WORK_ID.eq(WORK.ID))
        .join(CREDIT).on(CREDIT.BORROWER_ID.eq(BORROWER.ID))
        .groupBy(WORK.EDUCATION)
        .fetchMap(WORK.EDUCATION, count())));
  }
}
