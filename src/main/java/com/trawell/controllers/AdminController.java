package com.trawell.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * AdminController: andranno mappate tutte le funzionalità relative ad i controller
 * 
 */

@Controller
public class AdminController {
    @GetMapping("/checkAdmin")
    public String redirectToAdminCheck() {
        return "pages/admin/CheckAdmin";
    }

    
}
