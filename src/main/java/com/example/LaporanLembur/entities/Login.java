/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.entities;

import lombok.Data;

/**
 *
 * @author Ardian
 */
@Data
public class Login {
    private String email;
    private String password;
    private Employee employee;
}
