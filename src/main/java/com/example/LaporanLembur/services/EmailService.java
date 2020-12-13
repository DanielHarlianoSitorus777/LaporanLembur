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
        helper.setText(
                emailMessage(
                        "Welcome Our New Employee!",
                        "Congratulations!, you are part of us now. Now, you can login to our overtime report system management. By using this web application, you can easily create and submit your overtime report! Log in now and try it out!",
                        "http://localhost:8084/login",
                        "LOGIN"),
                true);

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

        helper.setText(
                emailMessage(
                        "New Report Received!",
                        "Hei! One of your employee in the department has create a new report. Don't make he/she wait! Confirm now!",
                        "http://localhost:8084/department",
                        "CHECK REPORT"),
                true);

        javaMailSender.send(msg);
    }

    public void sendEmployeeConfirmationNotif(String destination, String status) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(destination);
        helper.setSubject("Your Manager Has " + status + " Your Report!");
        helper.setText(
                emailMessage(
                        "Your Manager Has Response!",
                        "Congratulations! You get your response! But,if your report rejected, you should contact your manager ASAP.",
                        "http://localhost:8084/personal",
                        "CHECK REPORT"),
                true);
        javaMailSender.send(msg);
    }

    public void sendEmployeeNoteNotif(String destination, String note) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(destination);

        helper.setSubject("Your Manager Has Give You Some Note!");

        helper.setText(
                emailMessageNote(
                        "Your Manager Has Response!",
                        note,
                        "Congratulations! You get your response! But,if your report rejected, you should contact your manager ASAP.",
                        "http://localhost:8084/personal",
                        "CHECK REPORT"),
                true);

        javaMailSender.send(msg);
    }

    public void sendForgotPassNotif(String destination, String pass) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo(destination);

        helper.setSubject("This is your Password! Don't Tell Anyone!");

        helper.setText(
                emailMessageForgotPass(
                        "Top Secret Message!",
                        "Here is your password. Don't forgot your password again! It's your responsibility to remember it.",
                        destination,
                        pass,
                        "http://localhost:8084/login",
                        "LOGIN"),
                true);

        javaMailSender.send(msg);
    }

    public String emailMessage(String title, String body, String link, String buttonTitle) {
        String message = "<div style=\"padding: 25px 15px 25px 15px; margin-left: 50px; margin-right: 50px;";
        message += "font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-align: center;";
        message += "background-color: white; box-shadow: 0px 5px 15px #bababa;border-radius: 5px;\">";
        message += "<h1 style=\"background-color: #4e73df; color: white; padding: 10px; border-radius: 5px;\">";
        message += title;
        message += "</h1>";
        message += "<p style=\"margin-top: 50px; margin-bottom: 50px;\">";
        message += body;
        message += "</p>";
        message += "<div style=\"text-align: center\">";
        message += "<a href=" + link + " style=\"background-color: #4e73df; color: white;";
        message += "text-decoration: none; padding: 10px 50px 10px 50px; border-radius: 5px; font-weight: 700;\">";
        message += buttonTitle;
        message += "</a></div></div>";
        return message;
    }

    public String emailMessageNote(String title, String note, String body, String link, String buttonTitle) {
        String message = "<div style=\"padding: 25px 15px 25px 15px; margin-left: 50px; margin-right: 50px;";
        message += "font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-align: center;";
        message += "background-color: white; box-shadow: 0px 5px 15px #bababa;border-radius: 5px;\">";
        message += "<h1 style=\"background-color: #4e73df; color: white; padding: 10px; border-radius: 5px;\">";
        message += title;
        message += "</h1>";
        message += "<p style=\"margin-top: 50px; margin-bottom: 50px;\">";
        message += "<span style=\"font-weight: 700;\">" + note + "</span><br>";
        message += body;
        message += "</p>";
        message += "<div style=\"text-align: center\">";
        message += "<a href=" + link + " style=\"background-color: #4e73df; color: white;";
        message += "text-decoration: none; padding: 10px 50px 10px 50px; border-radius: 5px; font-weight: 700;\">";
        message += buttonTitle;
        message += "</a></div></div>";
        return message;
    }

    public String emailMessageForgotPass(String title, String body, String email, String pass, String link, String buttonTitle) {
        String message = "<div style=\"padding: 25px 15px 25px 15px; margin-left: 50px; margin-right: 50px;";
        message += "font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; text-align: center;";
        message += "background-color: white; box-shadow: 0px 5px 15px #bababa;border-radius: 5px;\">";
        message += "<h1 style=\"background-color: #4e73df; color: white; padding: 10px; border-radius: 5px;\">";
        message += title;
        message += "</h1>";
        message += "<p style=\"margin-top: 50px; margin-bottom: 50px;\">";
        message += body;
        message += "<br><br><span style=\"font-weight: 700; margin-top: 15px;\">Email : " + email + "</span>";
        message += "<br><span style=\"font-weight: 700;\">Password : " + pass + "</span>";
        message += "</p>";
        message += "<div style=\"text-align: center\">";
        message += "<a href=" + link + " style=\"background-color: #4e73df; color: white;";
        message += "text-decoration: none; padding: 10px 50px 10px 50px; border-radius: 5px; font-weight: 700;\">";
        message += buttonTitle;
        message += "</a></div></div>";
        return message;
    }

}
