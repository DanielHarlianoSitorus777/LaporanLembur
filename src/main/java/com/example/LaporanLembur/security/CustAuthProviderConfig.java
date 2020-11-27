/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class CustAuthProviderConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider); // mendapatkan user dari AuthProvider
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                            
                .antMatchers("/login").anonymous()              // mengatur route berdasarkan role
                .antMatchers("/forgotpassword/**").permitAll()  // anon & permitAll bisa diakses semua role
                .antMatchers("/register/**").permitAll()
                .anyRequest().authenticated()                   // user yang sudah diautentikasi
                .and()
                .formLogin()                                    // mengaktifkan form login
                .loginPage("/login")                            // path login
                .loginProcessingUrl("/execute_login")           // path action login
                .defaultSuccessUrl("/")                         // path login success
                .failureUrl("/login_error")                     // path login error
                .and()
                .httpBasic() 
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }
}
