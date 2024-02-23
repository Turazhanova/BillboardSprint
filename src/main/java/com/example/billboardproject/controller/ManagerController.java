package com.example.billboardproject.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/admin")
public class ManagerController {
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    @GetMapping(value = "/main")
    public String adminPage() {
        return "manager";
    }


}
