package com.development.konversee.service;

import com.development.konversee.model.AccountModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDb userDb;

    @Override
    public UsersModel addUser(UsersModel user){
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UsersModel getUser(String username) {
        return userDb.findByUsername(username);
    }

    @Override
    public Optional<UsersModel> getUserById(Long uuid) {
        return userDb.findById(uuid);
    }


    @Override
    public Boolean validatePassword(String oldpass, String oldpasscoba) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(oldpasscoba, oldpass);
    }

    @Override
    public void updatePassword(String username, String newpass) {
        UsersModel user = userDb.findByUsername(username);
        String newpassencrypt = encrypt(newpass);
        user.setPassword(newpassencrypt);
        userDb.save(user);
    }

    @Override
    public Set<AccountModel> getOwnerAccountList(UsersModel user) { //akun yg pernah digunakan
        return user.getAccountsOwned();
    }

}