package com.development.konversee.service;

import com.development.konversee.model.AccountModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.repository.AccountDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AccountServiceImpl implements  AccountService{
    @Autowired
    AccountDb accountDb;

    @Override
    public void addNewAccount(AccountModel accountModel){
        accountDb.save(accountModel);
    }

    @Override
    public List<AccountModel> getAccountList(){
        return accountDb.findAll();
    }

    @Override
    public Set<UsersModel> getAccountOwnerList(AccountModel accountModel){
        return accountModel.getOwners();
    }

    @Override
    public AccountModel findByInfo(String username, BigInteger phoneNumber){
        List<AccountModel> allAccount = getAccountList();
        for (AccountModel account: allAccount){
            if (account.getUsername().equalsIgnoreCase(username) && account.getPhoneNumber() == phoneNumber){
                return account;
            }
        }
        return null;
    }


}
