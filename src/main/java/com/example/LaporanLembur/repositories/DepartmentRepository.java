/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.repositories;

import com.example.LaporanLembur.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ardian
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
