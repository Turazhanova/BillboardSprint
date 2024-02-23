package com.example.billboardproject.controller;

import com.example.billboardproject.model.Role;
import com.example.billboardproject.model.User;
import com.example.billboardproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/")
    public String authPage() {
        return "redirect:/auth/";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/mainPage")
    public String profilePage() {
        User user = userService.getCurrentUser();
        for (Role r: user.getRoles()) {
            if (r.getRole().equals("MANAGER")) return "redirect:/admin/main";
        }
        return "mainPage";
    }



}
