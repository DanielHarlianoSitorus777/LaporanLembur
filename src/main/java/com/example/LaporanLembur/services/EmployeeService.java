/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.services;

import com.example.LaporanLembur.entities.*;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import com.example.LaporanLembur.repositories.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ardian
 */
@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    
    @Autowired
    TitleRepository titleRepository;
    
    public Title findTitleById(int id) {
        return titleRepository.findById(id).get();
    }
}
