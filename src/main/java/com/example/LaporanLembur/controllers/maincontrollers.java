/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.controllers;

import com.example.LaporanLembur.dao.EmployeeDao;
import com.example.LaporanLembur.daoimpl.AdminDaoImpl;
import com.example.LaporanLembur.daoimpl.EmployeeDaoImpl;
import com.example.LaporanLembur.entities.TempRole;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import com.example.LaporanLembur.repositories.TitleRepository;
import com.example.LaporanLembur.services.EmployeeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Ardian
 */
@Controller
public class maincontrollers {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    TitleRepository titleRepository;
    
    // LOGIN
    
    @GetMapping("/login_error")
    public String loginError() {
        return "login";
    }
    
    @GetMapping("/login")
    public String login() {
//        System.out.println("Title : " + titleRepository.getOne(1));
        return "login";
    }
    
    // HOME
    
    @GetMapping("")
    public String index() {
        String view = null;
        
        if (null != TempRole.role) switch (TempRole.role) {
            case "Manager":
                return view = "dashboardmanager";
            case "Karyawan":
                return view = "dashboardemployee";
            case "Admin":
                return view = "dashboardadmin";
            default:
                break;
        }
        return view;
    }
    
    @GetMapping("/home")
    public String home() {
        String view = null;
        
        if (null != TempRole.role) switch (TempRole.role) {
            case "Manager":
                return view = "dashboardmanager";
            case "Karyawan":
                return view = "dashboardemployee";
            case "Admin":
                return view = "dashboardadmin";
            default:
                break;
        }
        return view;
    }
    
    @Autowired
    EmployeeDaoImpl employeeDaoImpl;
    
    @GetMapping("/personal")
    public String personalReport(Model model) {
//        model.addAttribute("history", employeeDaoImpl.getReportbyDepartment(employeeRepository.findById(5).get()));
        return "reportpribadi";
    }
    
    @GetMapping("/department")
    public String departmentReport() {
        return "reportdivisi";
    }
    
    @Autowired
    AdminDaoImpl adminDaoImpl;
    
    @GetMapping("/employee")
    public String employeeList(Model model) {
        model.addAttribute("employee", adminDaoImpl.getAllEmployee());
        return "employeelist";
    }
    
    @GetMapping("/policy")
    public String policy() {
        return "kebijakan";
    }
    
    // LOGOUT
    
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
