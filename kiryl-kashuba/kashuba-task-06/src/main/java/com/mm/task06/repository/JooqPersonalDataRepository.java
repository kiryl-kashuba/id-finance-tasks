package com.mm.task06.repository;

import com.mm.task06.converter.PersonalDataConverter;
import com.mm.task06.db.tables.PersonalData;
import com.mm.task06.db.tables.records.PersonalDataRecord;
import com.mm.task06.model.PersonalDataModel;
import org.jooq.DSLContext;
import reactor.core.publisher.Mono;

public class JooqPersonalDataRepository implements PersonalDataRepository {

  private final DSLContext dslContext;
  private final PersonalDataConverter personalDataConverter;

  public JooqPersonalDataRepository(DSLContext dslContext,
      PersonalDataConverter personalDataConverter) {
    this.dslContext = dslContext;
    this.personalDataConverter = personalDataConverter;
  }

  @Override
  public Mono<PersonalDataModel> get(long id) {
    return Mono.fromSupplier(() -> dslContext
            .select()
            .from(PersonalData.PERSONAL_DATA)
            .where(PersonalData.PERSONAL_DATA.ID.eq(id))
            .fetchOne()
            .into(com.mm.task06.db.tables.pojos.PersonalData.class))
        .map(personalDataConverter::convertToModel);
  }

  @Override
  public Mono<Long> create(PersonalDataModel personalDataModel) {
    return Mono.fromSupplier(() -> dslContext
        .insertInto(PersonalData.PERSONAL_DATA)
        .set(
            dslContext.newRecord(PersonalData.PERSONAL_DATA, personalDataConverter.convertFromModel(personalDataModel)))
        .returning(PersonalData.PERSONAL_DATA.ID)
        .fetchOne()
        .getId());
  }

  @Override
  public Mono<Long> update(PersonalDataModel personalDataModel) {
    return Mono.fromSupplier(() -> {
      PersonalDataRecord dataRecord = dslContext
          .newRecord(PersonalData.PERSONAL_DATA, personalDataConverter.convertFromModel(personalDataModel));
      dataRecord.update();
      return dataRecord.getId();
    });
  }
}
