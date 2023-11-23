package com.example.email.service.impl;

import com.example.email.entity.Users;
import com.example.email.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final UserRepository userRepository; // Підставте власний репозиторій користувачів

    @Autowired
    public EmailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine, UserRepository userRepository) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
        this.userRepository = userRepository;
    }

    public void sendEmailToAllUsers(String subject, String msgBody) throws MessagingException {
        List<Users> users = userRepository.findAll();
        // Отримання всіх користувачів з бази

        for (Users user : users) {
            sendEmail(user.getEmail(), subject, msgBody);
            // Відправка листа кожному користувачеві
        }
    }

    private void sendEmail(String recipientEmail, String subject, String msgBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(msgBody, true);
        emailSender.send(message);
    }
}
