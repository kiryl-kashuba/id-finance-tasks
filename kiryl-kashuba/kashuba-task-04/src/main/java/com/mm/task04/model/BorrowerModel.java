package com.mm.task04.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class BorrowerModel {

  private final Long id;
  private final PersonalDataModel personalDataModel;
  private final Long userAccountId;
  private final WorkModel workModel;

  @JsonCreator
  public BorrowerModel(
      @JsonProperty("id") Long id,
      @JsonProperty("personalDataModel") PersonalDataModel personalDataModel,
      @JsonProperty("userAccountId") Long userAccountId,
      @JsonProperty("workModel") WorkModel workModel) {
    this.id = id;
    this.personalDataModel = personalDataModel;
    this.userAccountId = userAccountId;
    this.workModel = workModel;
  }

  public Long getId() {
    return id;
  }

  public PersonalDataModel getPersonalDataModel() {
    return personalDataModel;
  }

  public Long getUserAccountId() {
    return userAccountId;
  }

  public WorkModel getWorkModel() {
    return workModel;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BorrowerModel that = (BorrowerModel) o;
    return Objects.equals(id, that.id) && Objects.equals(personalDataModel, that.personalDataModel)
        && Objects.equals(userAccountId, that.userAccountId) && Objects.equals(workModel,
        that.workModel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personalDataModel, userAccountId, workModel);
  }
}
