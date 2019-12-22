package com.development.konversee.controller;


import com.development.konversee.model.RoleModel;
import com.development.konversee.model.UsersModel;
import com.development.konversee.service.RoleService;
import com.development.konversee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @RequestMapping("/")
    public String home(Authentication authentication, Model model) {
        model.addAttribute("user", userService.getUser(authentication.getName()));
        return "home";
    }

    @RequestMapping("/sign-up")
    public String signUp(Model model) {

        model.addAttribute("listRole", roleService.findAll());

        return "add-user";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUserSubmit(@ModelAttribute UsersModel user,
                                @RequestParam(required=false) String nama, Model model) throws ParseException, java.text.ParseException {

        if (userService.getUser(user.getUsername()) != null) {
            model.addAttribute("errormsg", "Username tidak valid");
            return "/";
//             return "error";
        } else {
            user.setRole(roleService.findRoleByName("USER"));
            userService.addUser(user);
        }
        model.addAttribute("username", user.getUsername());

        return "add-user-success";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    private String halamanAdmin(Authentication authentication, Model model){
        List<RoleModel> listRole = roleService.findAll();
        UsersModel user = userService.getUser(authentication.getName());
        model.addAttribute("listRole", listRole);
        model.addAttribute("user", user);
        return "admin";
    }
}
