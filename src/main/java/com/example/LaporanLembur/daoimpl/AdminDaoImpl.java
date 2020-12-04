/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.daoimpl;

import com.example.LaporanLembur.dao.AdminDao;
import com.example.LaporanLembur.entities.Employee;
import com.example.LaporanLembur.entities.Policy;
import com.example.LaporanLembur.repositories.EmployeeRepository;
import com.example.LaporanLembur.repositories.PolicyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ardian
 */
@Service
public class AdminDaoImpl implements AdminDao {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PolicyRepository policyRepository;

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Policy savePolicy(Policy policy) {
        return policyRepository.save(policy);
    }

}
