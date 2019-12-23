package com.development.konversee.service;

import com.development.konversee.model.AccountModel;
import com.development.konversee.model.AccountTypeModel;
import com.development.konversee.model.UsersModel;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public interface AccountService {
    void addNewAccount(AccountModel accountModel);
    List<AccountModel> getAccountList();
    AccountModel findByInfo(String username, String phoneNumber, AccountTypeModel type);
    Set<UsersModel> getAccountOwnerList(AccountModel accountModel); //pemilik/pengguna akun
    boolean checkSimilar(AccountModel accountModel);
    void addOwnerAccountRelationship(UsersModel user, AccountModel accountModel);
    AccountModel assignBroker(AccountTypeModel type);
}
