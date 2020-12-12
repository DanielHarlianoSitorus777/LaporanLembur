/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Ardian
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private CustomAuthenticationProvider authProvider;
     
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
//        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//            .dataSource(dataSource)
//            .usersByUsernameQuery("select email, password, title from employee where email=?")
//            .authoritiesByUsernameQuery("select email, title from employee where email=?")
//        ;
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()                            
                .antMatchers("/login").permitAll()
                .antMatchers("/forgotpassword/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/vendor/**").permitAll()
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
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling(); 
    }
}
