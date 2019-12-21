package com.development.konversee.service;

import com.development.konversee.model.AccountTypeModel;
import com.development.konversee.repository.AccountTypeDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService{
    @Autowired
    AccountTypeDb accountTypeDb;

    @Override
    public void addNewType(AccountTypeModel accountType){
        accountTypeDb.save(accountType);
    }

    @Override
    public List<AccountTypeModel> getAccountTypeList(){
        return accountTypeDb.findAllByOrderByNamaAsc();
    }

    @Override
    public boolean deleteType(Long idAccountType){
        accountTypeDb.deleteById(idAccountType);
        return true;
    }

    @Override
    public Optional<AccountTypeModel> getAccountTypeById(Long idAccountType){
        try {
            return accountTypeDb.findById(idAccountType);
        } catch (NoSuchElementException x) {
            return null;
        }
    }

    @Override
    public boolean checkAccountType(String nama){
        List<AccountTypeModel> temp = accountTypeDb.findAllByOrderByNamaAsc();
        if (temp.size() == 0) {
            return true;
        }

        for (AccountTypeModel a : temp) {
            if (nama.equals(a.getNama())) {
                return false;
            }
        }
        return true;
    }
}
