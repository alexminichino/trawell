package com.trawell.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Alfieri Davide
 * 
 * carsharing:andranno mappate tutte le funzionalità relative al carsharing
 */

@Controller
@RequestMapping("carsharing")
public class carsharingController {
    @GetMapping("/create.html")
    public String ad() {
        return "pages/user/publish_carsharing";
    }

    @GetMapping("/change.html")
    public String change(HttpSession session){
        if (!isLogged(session))
                return "pages/user/login";
                return "pages/user/modify-carsharing";

    }

    private boolean isLogged(HttpSession session) {
        return false;
    }
}