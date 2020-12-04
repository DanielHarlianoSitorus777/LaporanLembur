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
import org.springframework.stereotype.Service;

/**
 *
 * @author Ardian
 */
@Service
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    OvertimeRepository overtimeRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public List<Employee> getEmployeeByDepartment(Department department) {
        return employeeRepository.findByDepartment(department);
    }

    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public Title getTitle(int id) {
        return titleRepository.findById(id).get();
    }

    @Override
    public Overtime getReport(int id) {
        return overtimeRepository.findById(id).get();
    }

    @Override
    public List<Overtime> getAllReport() {
        return overtimeRepository.findAll();
    }

    @Override
    public List<Overtime> getReportByEmployee(Employee employee) {
        return overtimeRepository.findByEmployee(employee);
    }

    @Override
    public List<Overtime> getReportbyDepartment(Department department) {
        return overtimeRepository.findByDepartment(department);
    }

    @Override
    public Overtime getLatestReport() {
        return overtimeRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Overtime> getEmployeeLatestReport(Employee employee) {
        return overtimeRepository.findTopByEmployeeAndOrderByIdDesc(employee);
    }

    @Override
    public List<Overtime> getDepartmentLatestReport(Department department) {
        return overtimeRepository.findTopByDepartmentAndOrderByIdDesc(department);
    }

    @Override
    public void confirmReport(int id, String status) {
        overtimeRepository.confirmReport(id, status);
    }

    @Override
    public void addNote(int id, String managerNotes) {
        overtimeRepository.addNote(id, managerNotes);
    }

    @Override
    public List<String> getCurrentMonthTotalOvertime(Employee employee) {
        return overtimeRepository.getCurrentMonthValue(employee);
    }

    @Override
    public void login(Login login) {

        try {
            String email = login.getEmail();
            String password = login.getPassword();

            System.out.println("email : " + email);
            System.out.println("pass : " + password);
            System.out.println("emailrepo : " + employeeRepository.findByEmail(email).getEmail());

            if (email.equals(employeeRepository.findByEmail(email).getEmail()) || password.equals(employeeRepository.findByEmail(email).getPassword())) {
                login.setEmployee(employeeRepository.findByEmail(email));
            } else {
                System.out.println("No employee");
            }
        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }

    }

}
