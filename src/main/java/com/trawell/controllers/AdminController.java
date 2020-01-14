package com.trawell.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;

import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.BanData;
import com.trawell.models.User;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
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
 * @author Mario Paone AdminController: andranno mappate tutte le funzionalità
 *         relative all'amministratore ed i controller
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

    @Autowired
    private AdService adDao;

    @Autowired
    private AgencyService agencyDao;

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        return isAdmin(session) ? "pages/admin/home" : "pages/user/login";

    }

    @GetMapping("/banUsersLand")
    public String landing(HttpSession session, Model model) {

        if (isAdmin(session)) {
            Collection<User> listaUserTot = userDao.findAll();
            ArrayList<User> listaUser = new ArrayList<User>();
            for (User u : listaUserTot){
                if(!u.getIsAdmin())
                    listaUser.add(u);
            }
            model.addAttribute("listaUser", listaUser);
            return "pages/admin/banusers";
        }

        return "pages/user/home";

    }

    @GetMapping("/deleteAdsLand")
    public String deleteads(HttpSession session, Model model) {

        if (isAdmin(session)) {
            Collection<Ad> listaAd = adDao.findAll();
            Collection<Agency> listaAgency = agencyDao.findAll();
            if(listaAd.size() > 0)
                model.addAttribute("listaAds", listaAd);
            model.addAttribute("listaAgency", listaAgency);
            return "pages/admin/deletead";
        }

        return "pages/user/home";

    }

    /**
     * @author Mario Paone Method checks if the user is an Admin
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
    @PostMapping("/banUser")
    public String banuser(@RequestParam(name = "username", required = true) String username,
            @RequestParam(name = "motivation", required = true) String motivation,
            @RequestParam(name = "bannedUntil", required = true) Date bannedUntil, HttpSession session, Model model)
            throws UnsupportedEncodingException {

        if (!(isAdmin(session))) {
            return "pages/user/home";
        }

        User adminUser = (User) session.getAttribute("user");

        User user = userDao.findByUsername(username);
        String msg = "Ban failed";
        if (user != null) {
            if (user.getBanned()) {
                bannedUntil = Date.valueOf("2050-01-01");
            }
            user.setIsBanned(true);
            user.setBanned(true);
            userDao.update(user);
            BanData bandata = new BanData();
            bandata.setBanUntil(bannedUntil);
            bandata.setIdAdmin(adminUser.getId());
            bandata.setIdUser(user.getId());
            bandata.setMotivation(motivation);
            bandataDao.create(bandata);
            String email_text = "Caro " + user.getName() + " sei stato bannato da Trawell per questa motivazione:  "
                    + motivation;
            emailService.sendEmail(email_text, "Ban", user.getMail(), user.getName());
            msg = "Ban successfully executed";
            model.addAttribute("msg", msg);
            return "pages/admin/home";

        }
        model.addAttribute("msg", msg);
        return "pages/admin/home";

    }

}
