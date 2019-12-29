package com.trawell.controllers;

import java.sql.Date;
import java.util.Calendar;

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



/**
 * AdminController: andranno mappate tutte le funzionalità relative all'amministratore ed i controller
 * 
 */

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private BanDataService bandataDao;
    
    @Autowired
    private UserService userDao;
    
    @GetMapping("/banusers")
    public String landing(HttpSession session) {
        return isAdmin(session) ? "pages/admin/banusers" : "pages/user/home";
    }

    /**
	 * Method checks if the user is an Admin
	 * @param session
	 * @return true if he is an Admin, false otherwise
     * @param session is the session
	 */
	private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
		return ((user != null) && (user.getIsAdmin())); 
	}

    /**
	 * @author Mario Paone
	 * 
	 */
	@PostMapping("/banuser") 
	public String banuser(@RequestParam(name="username", required=true) String username,@RequestParam(name="motivation", required=true) String motivation,@RequestParam(name="bannedUntil", required=true) Date bannedUntil, HttpSession session, Model model) {
        
        
        if (!(isAdmin(session))){
            return "pages/user/home";
        }

        User adminUser = (User) session.getAttribute("user");
        
        User user = userDao.findByUsername(username);
        if (user.getBanned()){
            bannedUntil = Date.valueOf("2050-01-01");
        }
        if (user != null){
            user.setIsBanned(true);
            user.setBanned(true);
            userDao.update(user);
            BanData bandata = new BanData();
            bandata.setBanUntil(bannedUntil);
            bandata.setIdAdmin(adminUser.getId());
            bandata.setIdUser(user.getId());
            bandata.setMotivation(motivation);
            bandataDao.create(bandata);
            return "pages/admin/banusers";
        }else{
            model.addAttribute("msg","L'username non è presente");
            return "pages/user/home";
        }
	}

}
