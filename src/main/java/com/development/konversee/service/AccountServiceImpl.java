package com.development.konversee.service;

import com.development.konversee.model.AccountModel;
import com.development.konversee.model.AccountTypeModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.repository.AccountDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class AccountServiceImpl implements  AccountService{
    @Autowired
    AccountDb accountDb;

    @Autowired
    UserService userService;

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
    public AccountModel findByInfo(String username, BigInteger phoneNumber, AccountTypeModel type){
        List<AccountModel> allAccount = getAccountList();
        for (AccountModel account: allAccount){
            if (account.getUsername().equalsIgnoreCase(username)){
                if (account.getPhoneNumber().equals(phoneNumber)){
                    if (account.getType() == type){
                        return  account;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean checkSimilar(AccountModel accountModel){ //kalau ada yg sama return true
        if (findByInfo(accountModel.getUsername(), accountModel.getPhoneNumber(), accountModel.getType()) != null){
            return true;
        }
        System.out.println(accountModel.getUsername() + " " + accountModel.getPhoneNumber());
        return false;
    }

    @Override
    public void addOwnerAccountRelationship(UsersModel user, AccountModel accountModel){
        if (accountModel.getOwners() ==null){
            Set<UsersModel> newset = new HashSet<UsersModel>();
            accountModel.setOwners(newset);
        }
        accountModel.getOwners().add(user);
        accountDb.save(accountModel);
    }

    @Override
    public AccountModel assignBroker(AccountTypeModel type){
        List<AccountModel> typeMatch = new ArrayList<AccountModel>();
        List<AccountModel> potentialAsignees = new ArrayList<AccountModel>();

        for (AccountModel potential:getAccountList()){
            for (UsersModel user: potential.getOwners()){
                if(user.getRole().getNama().equalsIgnoreCase("BROKER")){
                    potentialAsignees.add(potential);
                }
            }
        }

        for (AccountModel acc:potentialAsignees){
            if (acc.getType().equals(type) ){
                typeMatch.add(acc);
            }
        }
        return typeMatch.get(0);
    }
}
