/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.security;

import com.example.LaporanLembur.entities.*;
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
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    boolean shouldAuthenticateAgainstThirdPartySystem = true;
//    
//    LoginOutput loginOutput;
//    Login loginInput = new Login();
//    
//    @Autowired
//    LoginService loginService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        
//        // Get value dari post
//        String name = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        
//        loginInput.setEmail(name);
//        loginInput.setPassword(password);
//        
//        loginOutput = loginService.login(loginInput);
//
//        if (loginOutput.getStatus().equalsIgnoreCase("verified")) {
//            final List<GrantedAuthority> grantedAuths = new ArrayList<>();
//            grantedAuths.add(new SimpleGrantedAuthority(loginOutput.getUser().getRoles().get(0))); // Get Roles
//            final UserDetails principal = new User(name, password, grantedAuths); // Membuat user
//            final Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
//            TempId.id = loginOutput.getUser().getId();
//            System.out.println(TempId.id);
//            return auth;
//        } else {
//            return null;
//        }
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
