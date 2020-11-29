/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ardian
 */
@Entity
@Table(name = "overtime")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Overtime.findAll", query = "SELECT o FROM Overtime o")
    , @NamedQuery(name = "Overtime.findById", query = "SELECT o FROM Overtime o WHERE o.id = :id")
    , @NamedQuery(name = "Overtime.findBySubmitDate", query = "SELECT o FROM Overtime o WHERE o.submitDate = :submitDate")
    , @NamedQuery(name = "Overtime.findByStartTime", query = "SELECT o FROM Overtime o WHERE o.startTime = :startTime")
    , @NamedQuery(name = "Overtime.findByEndTime", query = "SELECT o FROM Overtime o WHERE o.endTime = :endTime")
    , @NamedQuery(name = "Overtime.findByTotalTime", query = "SELECT o FROM Overtime o WHERE o.totalTime = :totalTime")
    , @NamedQuery(name = "Overtime.findByDescription", query = "SELECT o FROM Overtime o WHERE o.description = :description")
    , @NamedQuery(name = "Overtime.findByManagerNotes", query = "SELECT o FROM Overtime o WHERE o.managerNotes = :managerNotes")
    , @NamedQuery(name = "Overtime.findByStatus", query = "SELECT o FROM Overtime o WHERE o.status = :status")})
public class Overtime implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Submit_Date")
    @Temporal(TemporalType.DATE)
    private Date submitDate;
    @Column(name = "Start_Time")
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Column(name = "End_Time")
    @Temporal(TemporalType.TIME)
    private Date endTime;
    @Column(name = "Total_Time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date totalTime;
    @Basic(optional = false)
    @Column(name = "Description")
    private String description;
    @Column(name = "Manager_Notes")
    private String managerNotes;
    @Column(name = "Status")
    private String status;
    @JoinColumn(name = "Department", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Department department;
    @JoinColumn(name = "Employee", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;

    public Overtime() {
    }

    public Overtime(Integer id) {
        this.id = id;
    }

    public Overtime(Integer id, Date submitDate, String description) {
        this.id = id;
        this.submitDate = submitDate;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Date totalTime) {
        this.totalTime = totalTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerNotes() {
        return managerNotes;
    }

    public void setManagerNotes(String managerNotes) {
        this.managerNotes = managerNotes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Overtime)) {
            return false;
        }
        Overtime other = (Overtime) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.LaporanLembur.entities.Overtime[ id=" + id + " ]";
    }
    
}
