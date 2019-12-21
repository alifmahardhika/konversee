package com.development.konversee.service;

import com.development.konversee.model.AccountModel;

import java.util.List;

public interface AccountService {
    void addNewAccount(AccountModel accountModel);
    AccountModel findAccountByOwnerId(Long ownerId);
    List<AccountModel> getAccountList();
    List<AccountModel> getOwnerAccountListByOwnerId(List<AccountModel> allAccount, Long ownerId);

}
