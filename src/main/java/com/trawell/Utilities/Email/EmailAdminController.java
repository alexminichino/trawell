package com.trawell.utilities.email;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.trawell.models.User;
import com.trawell.utilities.email.EmailSenderService;

import java.io.UnsupportedEncodingException;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;

import java.util.Random;

import javax.servlet.http.HttpSession;


/**
 * @author Umberto Russomando
 * EmailAdminController: qui andranno mappate tutte le funzionalità
 * relative all'autenticazione dell'admin tramite codice inviato per email
 */
@EnableEmailTools
@Controller
@RequestMapping("admin")
public class EmailAdminController {

    @Autowired
    private EmailSenderService emailService; 


/**
 * This method generates a serial number and sends it to the admin by email
 * @author Umberto Russomando
 * @param session
 * @return the URL of the page where the admin has to enter the serial number if the user is an admin, otherwise it returns the URL of the landing page 
 * @throws UnsupportedEncodingException
 * @throws InterruptedException
 */
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


/**
 * This method checks if the serial number entered by the admin is equal to the one that has been generated before 
 * @author Umberto Russomando
 * @param serialNumber
 * @param session
 * @return the URL of the personal page of the admin if the serial number is correct, otherwise, a new serial number is being generated and sent to the admin
 */
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