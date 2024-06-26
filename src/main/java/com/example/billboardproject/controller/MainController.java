package com.example.billboardproject.controller;

import com.example.billboardproject.model.Billboard;
import com.example.billboardproject.model.Order;
import com.example.billboardproject.model.Role;
import com.example.billboardproject.model.User;
import com.example.billboardproject.service.OrderService;
import com.example.billboardproject.service.impl.BillboardServiceImpl;
import com.example.billboardproject.service.impl.CityServiceImpl;
import com.example.billboardproject.service.impl.LocationServiceImpl;
import com.example.billboardproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private BillboardServiceImpl billboardService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CityServiceImpl cityService;
    @Autowired
    private LocationServiceImpl locationService;

    @GetMapping(value = "/")
    public String authPage() {
        return "redirect:/auth/";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/mainPage")
    public String profilePage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("billboards", billboardService.getAllActiveBillboards());
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("locations", locationService.getAllLocations());
        if (user.getRole() == Role.MANAGER) return "redirect:/admin/main";
        return "main2";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/basket")
    public String basketPage(Model model) {
        User currentUser = userService.getUserData();
        model.addAttribute("orders", orderService.getAllOrdersByUserId(currentUser.getId()));
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("locations", locationService.getAllLocations());
        return "basket";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/detailBillboard/{billboard_id}")
    public String detailBillboardPage(Model model,
                                      @PathVariable(name = "billboard_id") Long id) {
        DateFormat dateFormat = new SimpleDateFormat("MM");
        String dateString = dateFormat.format(new Date());

        int currentMonth = Integer.parseInt(dateString);
        Billboard billboard = billboardService.getBillboardById(id);
        List<Order> billboardOrders = orderService.getAllOrdersByBillboardId(billboard.getId());

        List<String> dates = new ArrayList<>();
        List<String> anotherDates = new ArrayList<>();

        for (Order order : billboardOrders) {
            dates.add(order.getStartDate());
            anotherDates.add(order.getEndDate());
        }

        List<Integer> months = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
        for (int i = 0; i < dates.size() || i < anotherDates.size(); i++) {
            if (i < dates.size()) {
                LocalDate localDate = LocalDate.parse(dates.get(i), formatter);
                int month = localDate.getMonthValue();
                months.add(month);
            }
            if (i < anotherDates.size()) {
                LocalDate localDate = LocalDate.parse(anotherDates.get(i), formatter);
                int month = localDate.getMonthValue();
                months.add(month);
            }
        }

        List<Integer> months1 = new ArrayList<>(months);
        Set<Integer> uniqueMonths = new HashSet<>(months1);

        List<Integer> orderedMonths = new ArrayList<>();

        for (int i : uniqueMonths) {
            if (i >= currentMonth) {
                orderedMonths.add(i);
            }
        }

        model.addAttribute("billboardOrders", billboardOrders);
        model.addAttribute("billboard", billboard);
        model.addAttribute("orderedMonths", orderedMonths);
        model.addAttribute("currentMonth", currentMonth);
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("locations", locationService.getAllLocations());
        return "details";
    }




}