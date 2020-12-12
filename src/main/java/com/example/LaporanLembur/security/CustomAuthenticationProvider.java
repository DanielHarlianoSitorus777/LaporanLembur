/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.security;

import com.example.LaporanLembur.daoimpl.EmployeeDaoImpl;
import com.example.LaporanLembur.entities.*;
import com.example.LaporanLembur.repositories.TitleRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ardian
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    boolean shouldAuthenticateAgainstThirdPartySystem = true;

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    //LoginOutput loginOutput;
    Login loginInput = new Login();

    @Autowired
    TitleRepository titleRepository;

    @Autowired
    EmployeeDaoImpl loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // Get value dari post
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        loginInput.setEmail(name);
        loginInput.setPassword(password);

        loginService.login(loginInput);

        String role = null;
        int id = loginInput.getEmployee().getTitle().getId();
        int employeeId = loginInput.getEmployee().getId();
        int departmentId = loginInput.getEmployee().getDepartment().getId();
        Title title;

        System.out.println("Id : " + id);

        try {
            title = loginService.getTitle(id);
            role = title.getTitle();
            System.out.println("Title : " + role);
        } catch (Exception e) {
            System.out.println("Exception Auth : " + e);
        }

        System.out.println("Title : " + role);

        if (loginInput.getEmail().equals(loginService.getEmployeeByEmail(loginInput.getEmail()).getEmail()) && loginInput.getPassword().equals(loginService.getEmployeeByEmail(loginInput.getEmail()).getPassword())) {
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(role)); // Get Roles
            
            final CustomUser principal = new CustomUser(
                    name,
                    password,
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    grantedAuths,
                    loginInput.getEmployee().getId()
            );
            
            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
            TempValue.role = role;
            TempValue.id = employeeId;
            TempValue.deptId = departmentId;
            return auth;
        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
