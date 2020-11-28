/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.daoimpl;

import com.example.LaporanLembur.dao.EmployeeDao;
import com.example.LaporanLembur.entities.*;
import com.example.LaporanLembur.repositories.*;
import com.example.LaporanLembur.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ardian
 */
@Service
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
    public List<Overtime> getAllReport() {
        return overtimeRepository.findAll();
    }

    @Override
    public List<Overtime> getReportbyDepartment(Employee employee) {
        return overtimeRepository.findByEmployee(employee);
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
    EmployeeService employeeService;
    
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void login(Login login) {

        try {
            String email = login.getEmail();
            String password = login.getPassword();

            System.out.println("email : " + email);
            System.out.println("pass : " + password);
            System.out.println("emailrepo : " + employeeService.findByEmail(email).getEmail());

            if (email.equals(employeeService.findByEmail(email).getEmail()) || password.equals(employeeService.findByEmail(email).getPassword())) {
                login.setEmployee(employeeService.findByEmail(email));
            } else {
                System.out.println("No employee");
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }

    }

}
