package com.mm.task04.converter;

import com.mm.task04.db.tables.pojos.Credit;
import com.mm.task04.model.CreditModel;

public class DefaultCreditConverter implements CreditConverter {

  @Override
  public CreditModel convertToModel(Credit credit) {
    return new CreditModel(credit.getId(),
        credit.getAmountToPay(),
        credit.getCreditCountDays(),
        credit.getDateRequested(),
        credit.getInitialAmount(),
        credit.getPercentPerDay(),
        credit.getStatus(),
        credit.getBorrowerId());
  }

  @Override
  public Credit convertFromModel(CreditModel creditModel) {
    return new Credit(creditModel.getId(),
        creditModel.getAmountToPay(),
        creditModel.getCreditCountDays(),
        creditModel.getDateRequested(),
        creditModel.getInitialAmount(),
        creditModel.getPercentPerDay(),
        creditModel.getStatus(),
        creditModel.getBorrowerId());
  }
}
