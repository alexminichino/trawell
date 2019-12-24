package com.trawell.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;

import com.trawell.services.EmailSenderService;

import java.io.UnsupportedEncodingException;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

import java.util.Random;

/**
 * AdminController: andranno mappate tutte le funzionalità relative ad i controller
 * 
 */
@EnableEmailTools
@RestController
@RequestMapping("admin")
public class RestAdminController {

    @Autowired
    private EmailSenderService emailService; 


@PostMapping("/serialnumber")
public void sendSerialNumber() throws UnsupportedEncodingException, InterruptedException{

    Random r = new Random( System.currentTimeMillis() );  

    int rnd = 100000 + r.nextInt(200000);

    emailService.sendSerialNumberByEmail(rnd);
}


    
}