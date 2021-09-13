package com.demidao.controllers;

import com.demidao.models.User;
import com.demidao.services.RoleService;
import com.demidao.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userService.index(); // заполнение рыбными данными базу данных
    }

    @GetMapping()
    public String homePage(Model model, Principal principal) {
        User signedUser = userService.findByEmail(userService.loadUserByUsername(principal.getName()).getUsername());
        model.addAttribute("userList", userService.index());
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("signedUser", signedUser);
        model.addAttribute("newUser", new User());

        if (signedUser.hasRole("ROLE_ADMIN")) {
            return "admin/index-admin";
        }
        return "index";
    }
}
