package com.mm.task06.repository;

import static org.jooq.impl.DSL.count;

import com.mm.task06.converter.CreditConverter;
import com.mm.task06.db.tables.Borrower;
import com.mm.task06.db.tables.Credit;
import com.mm.task06.db.tables.Work;
import com.mm.task06.model.CreditModel;
import com.mm.types.Education;
import java.util.Map;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
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
        .insertInto(Credit.CREDIT)
        .set(dslContext.newRecord(Credit.CREDIT, creditConverter.convertFromModel(creditModel)))
        .returning(Credit.CREDIT.ID)
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
            .from(Credit.CREDIT)
            .where(Credit.CREDIT.BORROWER_ID.eq(borrowerId))
            .fetch()
            .into(com.mm.task06.db.tables.pojos.Credit.class))
        .map(creditConverter::convertToModel));
  }

  /**
   * Return count of credits for all educations.
   */
  @Override
  public Flux<Map<Education, Integer>> getCountOfCredits() {
    return Flux.defer(() -> Flux.just(dslContext
        .select(Work.WORK.EDUCATION, DSL.count(Credit.CREDIT))
        .from(Work.WORK)
        .join(Borrower.BORROWER).on(Borrower.BORROWER.WORK_ID.eq(Work.WORK.ID))
        .join(Credit.CREDIT).on(Credit.CREDIT.BORROWER_ID.eq(Borrower.BORROWER.ID))
        .groupBy(Work.WORK.EDUCATION)
        .fetchMap(Work.WORK.EDUCATION, count())));
  }
}
