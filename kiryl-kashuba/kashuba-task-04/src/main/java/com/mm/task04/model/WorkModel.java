package com.mm.task04.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mm.types.Education;
import java.time.LocalDateTime;
import java.util.Objects;

public class WorkModel {

  private final Long id;
  private final Double salary;
  private final Education education;
  private final LocalDateTime nextIncomeDate;

  @JsonCreator
  public WorkModel(
      @JsonProperty("id") Long id,
      @JsonProperty("salary") Double salary,
      @JsonProperty("education") Education education,
      @JsonProperty("nextIncomeDate") LocalDateTime nextIncomeDate) {
    this.id = id;
    this.salary = salary;
    this.education = education;
    this.nextIncomeDate = nextIncomeDate;
  }

  public Long getId() {
    return id;
  }

  public Double getSalary() {
    return salary;
  }

  public Education getEducation() {
    return education;
  }

  public LocalDateTime getNextIncomeDate() {
    return nextIncomeDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WorkModel work = (WorkModel) o;
    return Objects.equals(id, work.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "WorkModel{" +
        "id=" + id +
        ", salary=" + salary +
        ", education=" + education +
        ", nextIncomeDate=" + nextIncomeDate +
        '}';
  }
}
