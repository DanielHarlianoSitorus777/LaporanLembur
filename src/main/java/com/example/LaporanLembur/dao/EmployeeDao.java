/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.dao;

import com.example.LaporanLembur.entities.*;
import java.util.List;

/**
 *
 * @author Ardian
 */
public interface EmployeeDao {
    public List<Employee> getEmployeeByDepartment(Department department);
    public Department getDepartment(int id);
    public Title getTitle(int id);
    public Overtime getReport(int id);
    public List<Overtime> getAllReport();
    public List<Overtime> getReportByEmployee(Employee employee);
    public List<Overtime> getReportbyDepartment(Department department);
    public Overtime approveReport(Overtime approval);
    public Overtime createReport(Overtime overtime);
    public void login(Login login);
}
