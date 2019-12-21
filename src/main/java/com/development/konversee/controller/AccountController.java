package com.development.konversee.controller;

import com.development.konversee.model.AccountModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.service.AccountService;
import com.development.konversee.service.AccountTypeService;
import com.development.konversee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Set;

@Controller
public class AccountController {
    @Qualifier("accountServiceImpl")
    @Autowired
    AccountService accountService;

    @Autowired
    AccountTypeService accountTypeService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/account/add-account-form", method = RequestMethod.GET)
    public String newTypeForm(Model model) {
        AccountModel newAccount = new AccountModel();
        model.addAttribute("account", newAccount);
        model.addAttribute("listType", accountTypeService.getAccountTypeList());
        return "account/add-account-form";
    }

    @RequestMapping(value = "/account/add-account", method = RequestMethod.POST)
    public String submitNewType(@ModelAttribute AccountModel accountModel, Authentication auth, Model model) {
        model.addAttribute("username", accountModel.getUsername());
        model.addAttribute("phoneNumber", accountModel.getPhoneNumber());
        UsersModel user = userService.getUser(auth.getName());

        if (accountService.checkSimilar(accountModel)){
            accountModel = accountService.findByInfo(accountModel.getUsername(), accountModel.getPhoneNumber(), accountModel.getType());
            Set<UsersModel> relations = accountService.getAccountOwnerList(accountModel);
            for (UsersModel owner:relations){
                if (owner.getId().equalsIgnoreCase(user.getId())){ //todo: bikin casenya, account dengan data yg disubmit sudah pernah disimpan
                    return "account/add-account";
                }
                accountService.addOwnerAccountRelationship(user, accountModel);
            }
            return "account/add-account";
        }
        accountService.addOwnerAccountRelationship(user, accountModel);
        accountService.addNewAccount(accountModel);
        return "account/add-account";
    }

    @RequestMapping(value = "/account/view-all", method = RequestMethod.GET)
    public String viewAll(Model model, Authentication auth) {
        Set<AccountModel> accounts = userService.getOwnerAccountList(userService.getUser(auth.getName()));
        model.addAttribute("accounts", accounts);
        return "account/view-all";
    }
}
