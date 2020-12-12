/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.controllers;

import com.example.LaporanLembur.entities.CustomUser;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Ardian
 */
@Controller
public class ErrorControllers implements ErrorController {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @RequestMapping("/error")
    public String errorHandling(HttpServletRequest request, Model model) {
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int id = user.getId();
        model.addAttribute("report", employeeRepository.getOne(id));
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());
            
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Error404";
            }
        }
        
        return "Error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
    
}
