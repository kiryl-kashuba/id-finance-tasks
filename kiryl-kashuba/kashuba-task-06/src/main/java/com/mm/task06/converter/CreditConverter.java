package com.mm.task06.converter;

import com.mm.task06.db.tables.pojos.Credit;
import com.mm.task06.model.CreditModel;

public interface CreditConverter {

  CreditModel convertToModel(Credit credit);

  Credit convertFromModel(CreditModel creditModel);
}
