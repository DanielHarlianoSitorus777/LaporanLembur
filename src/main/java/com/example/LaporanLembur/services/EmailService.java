/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.LaporanLembur.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ardian
 */
@Service
public class EmailService {
    
    @Autowired
    JavaMailSender javaMailSender;
    
    public void sendEmailNotif(String destination) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo(destination);

        helper.setSubject("KapitaApp Registration Success");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>You're now part of us! Join here <a href='http://localhost:8084/login'>LOGIN</a></h1>", true);

        javaMailSender.send(msg);
        
    }
    
    public void sendEmailBasic(String destination, String sender) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom(sender);
        message.setTo(destination); 
        message.setSubject("KapitaApp Registration Success"); 
        message.setText("<h1>You're now part of us! Join here <a href='http://localhost:8084/login'></a></h1>");
        javaMailSender.send(message);
    }
    
}
