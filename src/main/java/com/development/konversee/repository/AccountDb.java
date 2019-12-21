package com.development.konversee.repository;

import com.development.konversee.model.AccountModel;

import java.util.List;

public interface AccountDb {
    List<AccountModel> findAll();
    AccountModel getAccountById(Long idAccount);
}
