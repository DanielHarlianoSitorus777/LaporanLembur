/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.repositories;

import com.example.LaporanLembur.entities.Department;
import com.example.LaporanLembur.entities.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ardian
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
    Employee findByEmail(@Param("email") String email);
    List<Employee> findByDepartment(@Param("department") Department department);
    
}
