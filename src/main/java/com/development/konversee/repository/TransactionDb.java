package com.development.konversee.repository;

import com.development.konversee.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TransactionDb extends JpaRepository<TransactionModel, Long> {
    List<TransactionModel> findAll();
    TransactionModel getTransactionById(Long transactionId);
}
