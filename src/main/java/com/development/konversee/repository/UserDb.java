package com.development.konversee.repository;

import com.development.konversee.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserDb extends JpaRepository<UsersModel, Long> {
    UsersModel findByUsername(String username);
    Optional<UsersModel> findById(Long uuid);
}

