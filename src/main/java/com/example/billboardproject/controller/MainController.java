package com.example.billboardproject.controller;

import com.example.billboardproject.model.Role;
import com.example.billboardproject.model.User;
import com.example.billboardproject.service.impl.BillboardServiceImpl;
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
    @Autowired
    private BillboardServiceImpl billboardService;

    @GetMapping(value = "/")
    public String authPage() {
        return "redirect:/auth/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/mainPage")
    public String profilePage(Model model) {
        User user = userService.getCurrentUser();
        if (user.getRole() == Role.MANAGER) return "redirect:/admin/main";

        model.addAttribute("billboards", billboardService.getAllActiveBillboards());
        return "main2";
    }



}