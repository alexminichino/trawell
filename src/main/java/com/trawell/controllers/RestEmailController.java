package com.trawell.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sendemail")
public class RestEmailController {

    public String sendEmail()
    {
        return "email sent successfully";
    }




}