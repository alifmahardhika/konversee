package com.development.konversee.repository;

import com.development.konversee.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface AccountDb extends JpaRepository<AccountModel, Long> {
    List<AccountModel> findAll();
    AccountModel getAccountById(Long idAccount);
}
