package com.trawell.controllers;


import java.text.DateFormat;
import java.util.Locale;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UsersController qui andranno mappate tutte le funzionalità relative all'utente per la comunicazione MVC
 */
@Controller
public class UsersController {

    @GetMapping("/prova")
	public String home(@RequestParam(name="name", required=false, defaultValue="World") String name, Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        model.addAttribute("name", name);
		return "pages/user/home";
    }
    
}