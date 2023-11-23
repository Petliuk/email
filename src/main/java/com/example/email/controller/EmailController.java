package com.example.email.controller;

import com.example.email.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/sendEmailToAllUsers")
    public ResponseEntity<String> sendEmailToAllUsers(@RequestBody String message) {
        try {
            emailService.sendEmailToAllUsers("Subject for email", message);
            return new ResponseEntity<>("Email has been sent to all users.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to send email to all users.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}