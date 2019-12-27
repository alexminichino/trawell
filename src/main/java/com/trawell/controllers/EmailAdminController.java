package com.trawell.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.trawell.models.User;
import com.trawell.services.EmailSenderService;

import java.io.UnsupportedEncodingException;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

import java.util.Random;

import javax.servlet.http.HttpSession;


/**
 * AdminController: andranno mappate tutte le funzionalità relative ad i controller
 * 
 */
@EnableEmailTools
@Controller
@RequestMapping("admin")
public class EmailAdminController {

    @Autowired
    private EmailSenderService emailService; 




@GetMapping("/serialnumber")
public String sendSerialNumber(HttpSession session) throws UnsupportedEncodingException, InterruptedException{

    Random r = new Random( System.currentTimeMillis() );  

    String rnd = 100000 + r.nextInt(200000) + "";

    User user = (User) session.getAttribute("user"); 

    emailService.sendSerialNumberByEmail(rnd,  user.getMail() ,user.getName());

    session.setAttribute("serialNumber", rnd);

    if(!user.getIsAdmin())
    {
        return "pages/home/index";
    }
    else
    {
        return "pages/admin/admincheckout";
    }

   
}



@PostMapping("checkSerialNumber")
public String checkSerialNumber(@RequestParam(name="serialNumber", required=true) String serialNumber,HttpSession session) 
{
    String toVerify =  (String) session.getAttribute("serialNumber");

    String to;

    if( serialNumber.equalsIgnoreCase(toVerify))
    {
        to = "pages/admin/home";
    }
    else
    {
        to = "redirect:/admin/serialnumber";
    }
    
    return to;
}

}