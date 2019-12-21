package com.development.konversee.service;


import com.development.konversee.model.RoleModel;
import com.development.konversee.repository.RoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleDb roleDb;

    @Override
    public List<RoleModel> findAll(){
        return roleDb.findAll();
    }

    @Override
    public RoleModel getRoleById(Long id) {
        return roleDb.findById(id).get();
    }

    @Override
    public void addRole(RoleModel role) {roleDb.save(role);}

    @Override
    public RoleModel findRoleByName(String roleName){
        if (findAll() == null){
            return  null;
        }
        List<RoleModel> listRole = findAll();
        for (RoleModel role:listRole){
            if (role.getNama().equalsIgnoreCase(roleName)){
                return role;
            }
        }
        return null;
    }
}
