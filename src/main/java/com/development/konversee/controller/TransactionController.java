package com.development.konversee.controller;

import com.development.konversee.model.AccountModel;
import com.development.konversee.model.TransactionModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.service.*;
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

    @Autowired
    EmailService emailService;

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
        transactionModel.setKeterangan("Verification in process");
        transactionModel.setUser(userService.getUser(auth.getName()));
        AccountModel broker = accountService.assignBroker(transactionModel.getOrigin().getType());
        transactionModel.setBroker(broker);
        System.out.println(transactionModel.toString());
        //send email notification to user
        emailService.asyncMailSender(userService.getUser(auth.getName()).getEmailAddress(), "Konversee Transaction Confirmation",
                "This is Konversee transaction confirmation, proceed by sending specified amount of " + transactionModel.getOrigin().getType().getNama() +
                        " based on the following details:\n" +
                        " Transaction Type: " +transactionModel.getOrigin().getType().getNama() +" to "+ transactionModel.getTipeAkunTujuan().getNama()+"\n" +
                        " Destination Account: \nPhone number: " + transactionModel.getNoHpTujuan()+ ", user: " + transactionModel.getUserNameTujuan()+", type: " + transactionModel.getTipeAkunTujuan().getNama()+"\n"+
                        " \n\nAmount to transfer: " + transactionModel.getNominal()+", please send the specified amount to the following " + transactionModel.getOrigin().getType().getNama() + "account\n"+
                        " Username: " + transactionModel.getBroker().getUsername() + "\n" +
                        " Phone Number: " +transactionModel.getBroker().getPhoneNumber());
        //send notification to broker
        emailService.asyncMailSender("konversee.transaction@gmail.com", "[NEW TRANSACTION ALERT]",
                "This is Konversee transaction confirmation, proceed by sending specified amount of " + transactionModel.getOrigin().getType().getNama() +
                        " based on the following details:\n" +
                        " Transaction Type: " +transactionModel.getOrigin().getType().getNama() +" to "+ transactionModel.getTipeAkunTujuan().getNama()+"\n" +
                        " Destination Account: \nPhone number: " + transactionModel.getNoHpTujuan()+ ", user: " + transactionModel.getUserNameTujuan()+", type: " + transactionModel.getTipeAkunTujuan().getNama()+"\n"+
                        " \n\nAmount to transfer: " + transactionModel.getNominal()+", please send the specified amount to the following " + transactionModel.getOrigin().getType().getNama() + "account\n"+
                        " Username: " + transactionModel.getBroker().getUsername() + "\n" +
                        " Phone Number: " +transactionModel.getBroker().getPhoneNumber());
        System.out.println("mail sent===============================");
        return "login";
    }
}
