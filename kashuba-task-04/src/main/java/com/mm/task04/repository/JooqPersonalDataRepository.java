package com.mm.task04.repository;

import static com.mm.task04.db.tables.PersonalData.PERSONAL_DATA;

import com.mm.task04.converter.PersonalDataConverter;
import com.mm.task04.db.tables.pojos.PersonalData;
import com.mm.task04.db.tables.records.PersonalDataRecord;
import com.mm.task04.model.PersonalDataModel;
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
        .from(PERSONAL_DATA)
        .where(PERSONAL_DATA.ID.eq(id))
        .fetchOne()
        .into(PersonalData.class))
        .map(personalDataConverter::convertToModel);
  }

  @Override
  public Mono<Long> create(PersonalDataModel personalDataModel) {
    return Mono.fromSupplier(() -> dslContext
        .insertInto(PERSONAL_DATA)
        .set(dslContext.newRecord(PERSONAL_DATA, personalDataConverter.convertFromModel(personalDataModel)))
        .returning(PERSONAL_DATA.ID)
        .fetchOne()
        .getId());
  }

  @Override
  public Mono<Long> update(PersonalDataModel personalDataModel) {
    return Mono.fromSupplier(() -> {
      PersonalDataRecord dataRecord = dslContext
          .newRecord(PERSONAL_DATA, personalDataConverter.convertFromModel(personalDataModel));
      dataRecord.update();
      return dataRecord.getId();
    });
  }
}
