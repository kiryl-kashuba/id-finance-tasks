package com.mm.task06.repository;

import com.mm.task06.converter.WorkConverter;
import com.mm.task06.db.tables.Work;
import com.mm.task06.db.tables.records.WorkRecord;
import com.mm.task06.model.WorkModel;
import org.jooq.DSLContext;
import reactor.core.publisher.Mono;

public class JooqWorkRepository implements WorkRepository {

  private final DSLContext dslContext;
  private final WorkConverter workConverter;

  public JooqWorkRepository(DSLContext dslContext, WorkConverter workConverter) {
    this.dslContext = dslContext;
    this.workConverter = workConverter;
  }

  @Override
  public Mono<WorkModel> get(long id) {
    return Mono.fromSupplier(() -> dslContext
            .select()
            .from(Work.WORK)
            .where(Work.WORK.ID.eq(id))
            .fetchOne()
            .into(com.mm.task06.db.tables.pojos.Work.class))
        .map(workConverter::convertToModel);
  }

  @Override
  public Mono<Long> create(WorkModel workModel) {
    return Mono.fromSupplier(() -> dslContext
        .insertInto(Work.WORK)
        .set(dslContext.newRecord(Work.WORK, workConverter.convertFromModel(workModel)))
        .returning(Work.WORK.ID)
        .fetchOne()
        .getId());
  }

  @Override
  public Mono<Long> update(WorkModel workModel) {
    return Mono.fromSupplier(() ->
    {
      WorkRecord workRecord = dslContext.newRecord(Work.WORK, workConverter.convertFromModel(workModel));
      workRecord.update();
      return workRecord.getId();
    });
  }
}
