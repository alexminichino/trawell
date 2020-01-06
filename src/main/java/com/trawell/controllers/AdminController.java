package com.trawell.controllers;

import java.sql.Date;


import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.trawell.models.BanData;
import com.trawell.models.User;
import com.trawell.services.BanDataService;
import com.trawell.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import com.trawell.utilities.email.EmailSenderService;
import it.ozimov.springboot.mail.configuration.EnableEmailTools;


/**
 * @author Mario Paone
 * AdminController: andranno mappate tutte le funzionalità relative all'amministratore ed i controller
 * 
 */
@EnableEmailTools
@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private BanDataService bandataDao;
    
    @Autowired
    private UserService userDao;

    @Autowired
    private EmailSenderService emailService;
    
    @GetMapping("/banusers")
    public String landing(HttpSession session) {
        return isAdmin(session) ? "pages/admin/banusers" : "pages/user/home";
    }

    /**
     * @author Mario Paone
	 * Method checks if the user is an Admin
	 * @param session
	 * @return true if he is an Admin, false otherwise
     * 
	 */
	private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
		return ((user != null) && (user.getIsAdmin())); 
	}

    /**
     * @author Mario Paone This method allows the admin to ban an user
     * @param username    the username of the user we want to ban
     * @param motivation  the motivation of the ban
     * @param bannedUntil the end date of the ban
     * @param session
     * @param model
     * @return send admin to home admin page
     * @throws UnsupportedEncodingException
     */
    @PostMapping("/banuser")
    public String banuser(@RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "motivation", required = true) String motivation,
            @RequestParam(name = "bannedUntil", required = true) Date bannedUntil, HttpSession session, Model model)
            throws UnsupportedEncodingException {
        
        
        if (!(isAdmin(session))){
            return "pages/user/home";
        }

        User adminUser = (User) session.getAttribute("user");
        
        User user = userDao.findByUsername(username);
        
        if (user != null){
            if (user.getBanned()){
                bannedUntil = Date.valueOf("2050-01-01");
            }else{
            user.setIsBanned(true);
            user.setBanned(true);
            userDao.update(user);
            BanData bandata = new BanData();
            bandata.setBanUntil(bannedUntil);
            bandata.setIdAdmin(adminUser.getId());
            bandata.setIdUser(user.getId());
            bandata.setMotivation(motivation);
            bandataDao.create(bandata);
            String email_text = "Caro " + user.getName() + " sei stato bannato da Trawell per questa motivazione:  " + motivation;
            emailService.sendEmail(email_text, "Ban", user.getMail(), user.getName());
            return "pages/admin/banusers";
        }
    }

    return "pages/admin/banusers";

        
    }
    
    

}
