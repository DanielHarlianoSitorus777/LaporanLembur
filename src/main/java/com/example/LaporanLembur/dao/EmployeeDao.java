/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.dao;

import com.example.LaporanLembur.entities.Department;
import com.example.LaporanLembur.entities.Overtime;
import com.example.LaporanLembur.entities.Title;
import java.util.List;

/**
 *
 * @author Ardian
 */
public interface EmployeeDao {
    public Department getDepartment(int id);
    public Title getTitle(int id);
    public Overtime getReport(int id);
    public List<Overtime> getReportbyDepartment(int deptId);
    public Overtime approveReport(Overtime approval);
    public Overtime createReport(Overtime overtime);
}
