package com.trawell.services;

import org.springframework.stereotype.Service;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;


import static com.google.common.collect.Lists.newArrayList;

@Service
public class EmailTestService {

    @Autowired
    private EmailService emailService;


    public void sendEmail() throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("u.russomando7@gmail.com",
                        "TraWell"))
                .to(newArrayList(
                        new InternetAddress("trawell@outlook.it",
                        "Hi")))
                .subject("Tesing the Email thing")
                .body("Hello World!")
                .encoding("UTF-8").build();

        emailService.send(email);
    }

}