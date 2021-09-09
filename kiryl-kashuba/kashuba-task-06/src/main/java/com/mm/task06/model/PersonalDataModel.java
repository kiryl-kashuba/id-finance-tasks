package com.mm.task06.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mm.types.MaritalStatus;
import java.sql.Date;
import java.util.Objects;

public class PersonalDataModel {

  private final Long id;
  private final Date birthday;
  private final String firstName;
  private final String lastName;
  private final String birthplace;
  private final MaritalStatus maritalStatus;

  @JsonCreator
  public PersonalDataModel(
      @JsonProperty("id") Long id,
      @JsonProperty("birthday") Date birthday,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("birthplace") String birthplace,
      @JsonProperty("maritalStatus") MaritalStatus maritalStatus) {
    this.id = id;
    this.birthday = birthday;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthplace = birthplace;
    this.maritalStatus = maritalStatus;
  }

  public Long getId() {
    return id;
  }

  public Date getBirthday() {
    return birthday;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getBirthplace() {
    return birthplace;
  }

  public MaritalStatus getMaritalStatus() {
    return maritalStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalDataModel that = (PersonalDataModel) o;
    return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName);
  }

  @Override
  public String toString() {
    return "PersonalDataModel{" +
        "id=" + id +
        ", birthday=" + birthday +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", birthplace='" + birthplace + '\'' +
        ", maritalStatus=" + maritalStatus +
        '}';
  }
}
