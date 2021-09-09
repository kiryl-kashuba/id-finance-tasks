package com.mm.task06.converter;

import com.mm.task06.db.tables.pojos.UserAccount;
import com.mm.task06.model.UserAccountModel;

public interface UserAccountConverter {

  UserAccountModel convertToModel(UserAccount userAccount);

  UserAccount convertFromModel(UserAccountModel userAccountModel);
}
