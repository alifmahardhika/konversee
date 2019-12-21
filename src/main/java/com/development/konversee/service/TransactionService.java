package com.development.konversee.service;

import com.development.konversee.model.TransactionModel;
import com.development.konversee.model.UsersModel;

import java.util.List;

public interface TransactionService {
    void createTransaction(TransactionModel transactionModel);
    TransactionModel findById(Long id);
    List<TransactionModel> getAll();
    List<TransactionModel> getMadeByUser(UsersModel user);
    List<TransactionModel> getBrokeredByBroker(UsersModel broker);

}
