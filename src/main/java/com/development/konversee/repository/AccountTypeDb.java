package com.development.konversee.repository;

import com.development.konversee.model.AccountTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountTypeDb extends JpaRepository<AccountTypeModel, Long> {
    Optional<AccountTypeModel> findById(Long idAccountType);
    AccountTypeModel findByNama(String nama);
    List<AccountTypeModel> findAllByOrderByNamaAsc();
}

