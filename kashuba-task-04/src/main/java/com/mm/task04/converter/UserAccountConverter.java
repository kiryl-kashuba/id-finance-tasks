package com.mm.task04.converter;

import com.mm.task04.db.tables.pojos.UserAccount;
import com.mm.task04.model.UserAccountModel;

public interface UserAccountConverter {

  UserAccountModel convertToModel(UserAccount userAccount);

  UserAccount convertFromModel(UserAccountModel userAccountModel);
}
