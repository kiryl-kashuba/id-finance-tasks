package com.mm.task04.converter;

import com.mm.task04.db.tables.pojos.Credit;
import com.mm.task04.model.CreditModel;

public interface CreditConverter {

  CreditModel convertToModel(Credit credit);

  Credit convertFromModel(CreditModel creditModel);
}
