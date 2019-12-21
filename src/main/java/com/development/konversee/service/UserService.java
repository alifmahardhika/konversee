package com.development.konversee.service;


import com.development.konversee.model.AccountModel;
import com.development.konversee.model.UsersModel;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    UsersModel addUser(UsersModel user);

    public String encrypt(String password);

    UsersModel getUser(String username);
    Optional<UsersModel> getUserById(Long uuid);

    Boolean validatePassword(String oldpass, String oldpasscoba);

    void updatePassword(String username, String newpass);

    Set<AccountModel> getOwnerAccountList(UsersModel user); //akun yg pernah digunakan

}
