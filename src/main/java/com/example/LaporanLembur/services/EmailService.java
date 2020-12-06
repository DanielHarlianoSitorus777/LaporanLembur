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
    
    public void sendManagerNotif(String destination, String employee) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo(destination);

        helper.setSubject(employee + " Create a New Report!");

        helper.setText("<p>Hei! One of your employee in the department has create a new report, confirm now! <a href='http://localhost:8084/department'>Check Report</a></p>", true);

        javaMailSender.send(msg);
    }
    
    public void sendEmployeeConfirmationNotif(String destination, String status) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(destination); 
        message.setSubject("Your Manager Has " + status + " Your Report!"); 
        message.setText("If your report rejected, you should contact your manager ASAP.");
        javaMailSender.send(message);
    }
    
    public void sendEmployeeNoteNotif(String destination, String note) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        
        helper.setTo(destination);

        helper.setSubject("Your Manager Has Give You Some Note!");

        helper.setText("<h4><em>" + note + "</em></h4>"
                + "<p>"
                + "If you want to response, you should contact your manager ASAP."
                + "</p>", 
                true);

        javaMailSender.send(msg);
    }
    
}
