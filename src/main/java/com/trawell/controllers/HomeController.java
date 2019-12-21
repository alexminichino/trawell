package com.trawell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Homecontroller: andranno mappate tutte le funzionalità relative ad i controller
 * 
 */

@Controller
public class HomeController {
    @GetMapping("/")
    public String landing() {
        return "pages/home/index";
    }
}
