package com.development.konversee.controller;

import com.development.konversee.model.AccountTypeModel;
import com.development.konversee.service.AccountTypeService;
import com.development.konversee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AccountTypeController {
    @Qualifier("accountTypeServiceImpl")
    @Autowired
    AccountTypeService accountTypeService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/account/new-type-form", method = RequestMethod.GET)
    public String newTypeForm(Model model) {
        AccountTypeModel newType = new AccountTypeModel();
        model.addAttribute("accountType", newType);
        return "account/new-type-form";
    }

    @RequestMapping(value = "/account/create-new-type", method = RequestMethod.POST)
    public String submitNewType(@ModelAttribute AccountTypeModel accountTypeModel, Authentication auth, Model model) {
        accountTypeService.addNewType(accountTypeModel);
        model.addAttribute("typeName", accountTypeModel.getNama());
        return "account/create-new-type";
    }
}
