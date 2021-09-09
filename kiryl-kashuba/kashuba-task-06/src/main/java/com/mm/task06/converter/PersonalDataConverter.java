package com.mm.task06.converter;

import com.mm.task06.db.tables.pojos.PersonalData;
import com.mm.task06.model.PersonalDataModel;

public interface PersonalDataConverter {

  PersonalDataModel convertToModel(PersonalData personalData);

  PersonalData convertFromModel(PersonalDataModel personalDataModel);
}
