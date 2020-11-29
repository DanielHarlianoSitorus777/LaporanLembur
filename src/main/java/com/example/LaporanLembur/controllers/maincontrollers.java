/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.controllers;

import com.example.LaporanLembur.daoimpl.AdminDaoImpl;
import com.example.LaporanLembur.daoimpl.EmployeeDaoImpl;
import com.example.LaporanLembur.entities.TempValue;
import com.example.LaporanLembur.repositories.DepartmentRepository;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import com.example.LaporanLembur.repositories.OvertimeRepository;
import com.example.LaporanLembur.repositories.PolicyRepository;
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
        return "login";
    }
    
    // HOME
    
    @GetMapping("")
    public String index() {
        String view = null;
        
        if (null != TempValue.role) switch (TempValue.role) {
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
        
        if (null != TempValue.role) switch (TempValue.role) {
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
    
    @Autowired
    OvertimeRepository overtimeRepository;
    
    @Autowired
    DepartmentRepository departmentRepository;
    
    @Autowired
    PolicyRepository policyRepository;
    
    @GetMapping("/personal")
    public String personalReport(Model model) {
        model.addAttribute("history", employeeDaoImpl.getReportByEmployee(employeeRepository.getOne(TempValue.id)));
        return "reportpribadi";
    }
    
    @GetMapping("/department")
    public String departmentReport(Model model) {
        model.addAttribute("history", employeeDaoImpl.getReportbyDepartment(departmentRepository.getOne(TempValue.deptId)));
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
