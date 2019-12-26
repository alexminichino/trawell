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
public class EmailSenderService {

    @Autowired
    private EmailService emailService;


    public void sendSerialNumberByEmail(String serialNumber, String emailTo, String name) throws UnsupportedEncodingException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress("TraWell.customerservice@gmail.com",
                        "TraWell"))
                .to(newArrayList(
                        new InternetAddress(emailTo,name)))
                .subject("Serial Number")
                .body("Hi, " + name +" this is you serial number: "+ serialNumber)
                .encoding("UTF-8").build();

        emailService.send(email);
    }

}