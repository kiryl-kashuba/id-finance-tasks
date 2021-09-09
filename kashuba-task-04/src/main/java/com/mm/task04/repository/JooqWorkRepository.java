package com.mm.task04.repository;

import static com.mm.task04.db.tables.Work.WORK;

import com.mm.task04.converter.WorkConverter;
import com.mm.task04.db.tables.pojos.Work;
import com.mm.task04.db.tables.records.WorkRecord;
import com.mm.task04.model.WorkModel;
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
        .from(WORK)
        .where(WORK.ID.eq(id))
        .fetchOne()
        .into(Work.class))
        .map(workConverter::convertToModel);
  }

  @Override
  public Mono<Long> create(WorkModel workModel) {
    return Mono.fromSupplier(() -> dslContext
        .insertInto(WORK)
        .set(dslContext.newRecord(WORK, workConverter.convertFromModel(workModel)))
        .returning(WORK.ID)
        .fetchOne()
        .getId());
  }

  @Override
  public Mono<Long> update(WorkModel workModel) {
    return Mono.fromSupplier(() ->
    {
      WorkRecord workRecord = dslContext.newRecord(WORK, workConverter.convertFromModel(workModel));
      workRecord.update();
      return workRecord.getId();
    });
  }
}
