package com.mm.task04.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mm.types.CreditStatus;
import java.time.LocalDateTime;
import java.util.Objects;

public class CreditModel {

  private final Long id;
  private final Double amountToPay;
  private final Integer creditCountDays;
  private final LocalDateTime dateRequested;
  private final Double initialAmount;
  private final Double percentPerDay;
  private final CreditStatus status;
  private final long borrowerId;

  @JsonCreator
  public CreditModel(@JsonProperty("id") Long id,
      @JsonProperty("amountToPay") Double amountToPay,
      @JsonProperty("creditCountDays") Integer creditCountDays,
      @JsonProperty("dateRequested") LocalDateTime dateRequested,
      @JsonProperty("initialAmount") Double initialAmount,
      @JsonProperty("percentPerDay") Double percentPerDay,
      @JsonProperty("status") CreditStatus status,
      @JsonProperty("borrowerId") Long borrowerId) {
    this.id = id;
    this.amountToPay = amountToPay;
    this.creditCountDays = creditCountDays;
    this.dateRequested = dateRequested;
    this.initialAmount = initialAmount;
    this.percentPerDay = percentPerDay;
    this.status = status;
    this.borrowerId = borrowerId;
  }

  public Long getId() {
    return id;
  }

  public Double getAmountToPay() {
    return amountToPay;
  }

  public Integer getCreditCountDays() {
    return creditCountDays;
  }

  public LocalDateTime getDateRequested() {
    return dateRequested;
  }

  public Double getInitialAmount() {
    return initialAmount;
  }

  public Double getPercentPerDay() {
    return percentPerDay;
  }

  public CreditStatus getStatus() {
    return status;
  }

  public Long getBorrowerId() {
    return borrowerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreditModel that = (CreditModel) o;
    return Objects.equals(id, that.id) && Objects.equals(borrowerId, that.borrowerId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, borrowerId);
  }

  @Override
  public String toString() {
    return "CreditModel{" +
        "id=" + id +
        ", amountToPay=" + amountToPay +
        ", creditCountDays=" + creditCountDays +
        ", dateRequested=" + dateRequested +
        ", initialAmount=" + initialAmount +
        ", percentPerDay=" + percentPerDay +
        ", status=" + status +
        ", borrowerId=" + borrowerId +
        '}';
  }
}
