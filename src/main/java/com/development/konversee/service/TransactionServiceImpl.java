package com.development.konversee.service;

import com.development.konversee.model.TransactionModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.repository.TransactionDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements  TransactionService{

    @Autowired
    TransactionDb transactionDb;

    @Override
    public void createTransaction(TransactionModel transactionModel){transactionDb.save(transactionModel);}

    @Override
    public TransactionModel findById(Long id){
        return transactionDb.findById(id).get();
    }

    @Override
    public List<TransactionModel> getAll(){
        return transactionDb.findAll();
    }

    @Override
    public List<TransactionModel> getMadeByUser(UsersModel user){
        List<TransactionModel> transactionList = getAll();
        ArrayList<TransactionModel> userMadeTransaction = new ArrayList<TransactionModel>();

        for (TransactionModel transactionModel:transactionList){
            if (transactionModel.getUser().equals(user)){
                userMadeTransaction.add(transactionModel);
            }
        }
        return userMadeTransaction;
    }

    @Override
    public List<TransactionModel> getBrokeredByBroker(UsersModel broker){
        List<TransactionModel> transactionList = getAll();
        ArrayList<TransactionModel> brokeredTransaction = new ArrayList<TransactionModel>();

        for (TransactionModel transactionModel:transactionList){
            if (transactionModel.getBroker().equals(broker)){
                brokeredTransaction.add(transactionModel);
            }
        }
        return brokeredTransaction;
    }
//
//    @Override
//    public List<TransactionModel> getCertainOriginType(String){
}
