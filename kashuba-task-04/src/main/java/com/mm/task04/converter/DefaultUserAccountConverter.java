package com.mm.task04.converter;

import com.mm.task04.db.tables.pojos.UserAccount;
import com.mm.task04.model.UserAccountModel;

public class DefaultUserAccountConverter implements UserAccountConverter {

  @Override
  public UserAccountModel convertToModel(UserAccount userAccount) {
    return new UserAccountModel(userAccount.getId(),
        userAccount.getDateCreated(),
        userAccount.getEmail(),
        userAccount.getName(),
        userAccount.getMobilePhone());
  }

  @Override
  public UserAccount convertFromModel(UserAccountModel userAccountModel) {
    return new UserAccount(userAccountModel.getId(),
        userAccountModel.getDateCreated(),
        userAccountModel.getEmail(),
        userAccountModel.getName(),
        userAccountModel.getMobilePhone());
  }
}
