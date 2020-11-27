/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.controllers;

import com.example.LaporanLembur.dao.EmployeeDao;
import com.example.LaporanLembur.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Ardian
 */
@Controller
public class maincontrollers {
    
    EmployeeDao employeeDao;
    
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("employee", new Login());
        return "Login";
    }
    
    @PostMapping("/login")
    public String login() {
        
        return "kebijakan";
    }
}
