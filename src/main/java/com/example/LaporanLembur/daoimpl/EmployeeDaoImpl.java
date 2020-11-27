/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.daoimpl;

import com.example.LaporanLembur.dao.EmployeeDao;
import com.example.LaporanLembur.entities.*;
import com.example.LaporanLembur.repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ardian
 */
public class EmployeeDaoImpl implements EmployeeDao {
    
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findById(id).get();
    }
    
    @Autowired
    TitleRepository titleRepository; 

    @Override
    public Title getTitle(int id) {
        return titleRepository.findById(id).get();
    }
    
    @Autowired
    OvertimeRepository overtimeRepository;

    @Override
    public Overtime getReport(int id) {
        return overtimeRepository.findById(id).get();
    }

    @Override
    public List<Overtime> getReportbyDepartment(int deptId) {
        return overtimeRepository.findByEmployee(deptId);
    }

    @Override
    public Overtime approveReport(Overtime approval) {
        return overtimeRepository.save(approval);
    }

    @Override
    public Overtime createReport(Overtime overtime) {
        return overtimeRepository.save(overtime);
    }
    
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean login(Login login) {
        String email = login.getEmail();
        String password = login.getPassword();
        
        if (email == employeeRepository.findByEmail(email).getEmail() && password == employeeRepository.findByEmail(email).getPassword()) {
            return true;
        } else {
            return false;
        }
        
    }
    
}
