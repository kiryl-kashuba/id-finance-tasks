package com.mm.task04.converter;

import com.mm.task04.db.tables.pojos.PersonalData;
import com.mm.task04.model.PersonalDataModel;

public interface PersonalDataConverter {

  PersonalDataModel convertToModel(PersonalData personalData);

  PersonalData convertFromModel(PersonalDataModel personalDataModel);
}
