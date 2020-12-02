/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.repositories;

import com.example.LaporanLembur.entities.Department;
import com.example.LaporanLembur.entities.Employee;
import com.example.LaporanLembur.entities.Overtime;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Ardian
 */
public interface OvertimeRepository extends JpaRepository<Overtime, Integer> {

    List<Overtime> findByEmployee(@Param("employee") Employee employee);

    List<Overtime> findByDepartment(@Param("department") Department department);

    Overtime findTopByOrderByIdDesc();

    List<Overtime> findTopByEmployeeAndOrderByIdDesc(@Param("employee") Employee employee);

    List<Overtime> findTopByDepartmentAndOrderByIdDesc(@Param("department") Department department);

    @Transactional
    @Modifying
    @Query("UPDATE Overtime o SET o.status = :status WHERE o.id = :id")
    void confirmReport(@Param("id") int id, @Param("status") String status);
    
    @Transactional
    @Modifying
    @Query("UPDATE Overtime o SET o.managerNotes = :managerNotes WHERE o.id = :id")
    void addNote(@Param("id") int id, @Param("managerNotes") String managerNotes);
    
    @Query("SELECT SUM(o.totalTime) FROM Overtime o WHERE o.employee = :employee AND MONTH(o.submitDate) = MONTH(CURDATE()) GROUP BY MONTH(o.submitDate)")
    List<String> getCurrentMonthValue(@Param("employee") Employee employee);
}
