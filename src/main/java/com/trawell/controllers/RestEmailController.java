package com.trawell.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;

import com.trawell.services.EmailSenderService;

@EnableEmailTools
@RestController
@RequestMapping(value="/email")
public class RestEmailController {

    @Autowired
    private EmailSenderService emailService;

    @PostMapping("/testingemail")
    public void sendEmail() throws UnsupportedEncodingException, InterruptedException {
        emailService.sendSerialNumberByEmail(5135113);
    }
}