package com.mm.task04.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.Objects;

public class UserAccountModel {

  private final Long id;
  private final LocalDateTime dateCreated;
  private final String email;
  private final String name;
  private final String mobilePhone;

  @JsonCreator
  public UserAccountModel(
      @JsonProperty("id") Long id,
      @JsonProperty("dateCreated") LocalDateTime dateCreated,
      @JsonProperty("email") String email,
      @JsonProperty("name") String name,
      @JsonProperty("mobilePhone") String mobilePhone) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.email = email;
    this.name = name;
    this.mobilePhone = mobilePhone;
  }

  public Long getId() {
    return id;
  }

  public LocalDateTime getDateCreated() {
    return dateCreated;
  }

  public String getEmail() {
    return email;
  }

  public String getName() {
    return name;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAccountModel that = (UserAccountModel) o;
    return Objects.equals(email, that.email) && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, name);
  }

  @Override
  public String toString() {
    return "UserAccountModel{" +
        "id=" + id +
        ", dateCreated=" + dateCreated +
        ", email='" + email + '\'' +
        ", name='" + name + '\'' +
        ", mobilePhone='" + mobilePhone + '\'' +
        '}';
  }
}
