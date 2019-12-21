package com.development.konversee.service;



import com.development.konversee.model.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> findAll();

    RoleModel getRoleById(Long id);

    void addRole(RoleModel role);

    RoleModel findRoleByName(String namaRole);
}
