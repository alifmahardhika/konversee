package com.development.konversee.service;

import com.development.konversee.model.AccountTypeModel;

import java.util.List;
import java.util.Optional;

public interface AccountTypeService {
    // Method untuk menambah jenis surat
    void addNewType(AccountTypeModel accountType);

    // Method untuk mendapatkan semua data JenisSurat yang tersimpan
    List<AccountTypeModel> getAccountTypeList();

    // Method untuk menghapus data sebuah JenisSurat berdasarkan idJenisSurat
    boolean deleteType(Long idJenisSurat);

    // Method untuk mendapatkan jenis surat berdasarkan idnya
    Optional<AccountTypeModel> getAccountTypeById(Long idAccountType);

    // Method untuk mengecek apakah jenis surat sudah terdaftar dalam database
    boolean checkAccountType(String nama);
}