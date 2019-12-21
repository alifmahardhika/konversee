package com.development.konversee.controller;

import com.development.konversee.model.TransactionModel;
import com.development.konversee.service.AccountTypeService;
import com.development.konversee.service.TransactionService;
import com.development.konversee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransactionController {
    @Qualifier("transactionServiceImpl")
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountTypeService accountTypeService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/transaction/create-transaction-form", method = RequestMethod.GET)
    public String createTransactionForm(Model model, Authentication auth) {
        TransactionModel newTransaction = new TransactionModel();
        model.addAttribute("transaction", newTransaction);
        model.addAttribute("accountTypes", accountTypeService.getAccountTypeList());
        model.addAttribute("registeredAccounts", userService.getOwnerAccountList(userService.getUser(auth.getName())));

        return "transaction/create-transaction-form";
    }
}
