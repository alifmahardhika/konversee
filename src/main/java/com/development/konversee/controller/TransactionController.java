package com.development.konversee.controller;

import com.development.konversee.model.TransactionModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.service.AccountService;
import com.development.konversee.service.AccountTypeService;
import com.development.konversee.service.TransactionService;
import com.development.konversee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.Set;

@Controller
public class TransactionController {
    @Qualifier("transactionServiceImpl")
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountTypeService accountTypeService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/transaction/create-transaction-form", method = RequestMethod.GET)
    public String createTransactionForm(Model model, Authentication auth) {
        TransactionModel newTransaction = new TransactionModel();
        model.addAttribute("transaction", newTransaction);
        model.addAttribute("accountTypes", accountTypeService.getAccountTypeList());
        model.addAttribute("registeredAccounts", userService.getOwnerAccountList(userService.getUser(auth.getName())));

        return "transaction/create-transaction-form";
    }

    @RequestMapping(value = "/transaction/create-transaction", method = RequestMethod.POST)
    public String createTransaction(@ModelAttribute TransactionModel transactionModel, Authentication auth, Model model) {
        transactionModel.setTanggalDibuka(LocalDate.now());
        transactionModel.setStatus(0);
        transactionModel.setKeterangan("This transaction details is being verified");
        transactionModel.setUser(userService.getUser(auth.getName()));
        transactionModel.setBroker(accountService.assignBroker(transactionModel.getTipeAkunTujuan()));
        System.out.println(transactionModel.toString());
        return "login";
    }
}
