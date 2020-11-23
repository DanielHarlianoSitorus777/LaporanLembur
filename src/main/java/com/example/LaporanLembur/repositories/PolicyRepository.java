/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.repositories;

import com.example.LaporanLembur.entities.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ardian
 */
public interface PolicyRepository extends JpaRepository<Policy, Integer>{
    
}
