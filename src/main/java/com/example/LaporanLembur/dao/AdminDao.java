/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.dao;

import com.example.LaporanLembur.entities.Employee;
import com.example.LaporanLembur.entities.Policy;
import java.util.List;

/**
 *
 * @author Ardian
 */
public interface AdminDao {
    public List<Employee> getAllEmployee();
    public Employee saveEmployee(Employee employee);
    public void deleteEmployee(int id);
    public Policy savePolicy(Policy policy);
}
