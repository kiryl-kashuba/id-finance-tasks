package com.mm.task06.converter;

import com.mm.task06.db.tables.pojos.PersonalData;
import com.mm.task06.model.PersonalDataModel;

public class DefaultPersonalDataConverter implements PersonalDataConverter {

  @Override
  public PersonalDataModel convertToModel(PersonalData personalData) {
    return new PersonalDataModel(
        personalData.getId(),
        personalData.getBirthday(),
        personalData.getFirstName(),
        personalData.getLastName(),
        personalData.getBirthplace(),
        personalData.getMaritalstatus());
  }

  @Override
  public PersonalData convertFromModel(PersonalDataModel personalDataModel) {
    return new PersonalData(
        personalDataModel.getId(),
        personalDataModel.getBirthday(),
        personalDataModel.getFirstName(),
        personalDataModel.getLastName(),
        personalDataModel.getBirthplace(),
        personalDataModel.getMaritalStatus());
  }
}
