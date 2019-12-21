package com.development.konversee.repository;

import com.development.konversee.model.RoleModel;
import com.development.konversee.model.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long> {
    UsersModel findByNama(String nama);

    Optional<RoleModel> findById(Long id);
}