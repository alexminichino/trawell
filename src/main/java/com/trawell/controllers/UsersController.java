package com.trawell.controllers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.trawell.models.Agency;
import com.trawell.models.BanData;
import com.trawell.models.User;
import com.trawell.services.BanDataService;
import com.trawell.services.UserService;
import com.trawell.utilities.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Milione Vincent
 * UsersController qui andranno mappate tutte le funzionalità relative all'utente per la comunicazione MVC
 */
@Controller
@RequestMapping("users")
public class UsersController {
	@Autowired
	private UserService dao;

	@Autowired
    private BanDataService bandataDao;
	/**
	 * Method checks if the user is already logged
	 * @param session
	 * @return true if he is already logged, false otherwise
	 */
	private boolean isLogged (HttpSession session) {
		return session.getAttribute("user") != null;
	}

	/**
	 * @author Milione Vincent
	 * The method maps user's get request to the login page
	 * @param session
	 * @return if the user isn't already logged in return the URL of the login page or else send him to home page 
	 */
	@GetMapping("/login-page")
	public String loginPage (HttpSession session) {
		return isLogged(session) ? "pages/user/home" : "pages/user/login";
	}

	/**
	 * @author Milione Vincent
	 * The method allows users to log out the platform
	 * @param session
	 * @return sends user to home page
	 */
	@GetMapping("/logout")
	public String logout (HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}

	/**
	 * @author Milione Vincent
	 * The method maps user's get request to sign-up page
	 * @param session
	 * @return URL of the sign-up page if the user isn't already logged in
	 */
	@GetMapping("/sign-up-page")
	public String signUpPage (HttpSession session) {
		return isLogged(session) ? "pages/user/home" : "pages/user/sign-Up";
	}

	/**
	 * @author Milione Vincent
	 * The method allows the user to perform the login functionality
	 * @param username user's username of the account he's trying to log in
	 * @param password user's password of the account he's trying to log in
	 * @param session
	 * @param model
	 * @return sends user to its home page if user credetials are correct. If user is banned are credetials are wrong
	 * user is sent back to login page.
	 */
	@PostMapping("/login") 
	public String login(@RequestParam(name="username", required=true) String username,@RequestParam(name="password", required=true) String password, HttpSession session, Model model) {
	
		if (isLogged(session)) return "redirect:/"; 

		User user = dao.findByUsername(username);
		password = new Encoder(username).encoding(password, username.length());

		if (user == null) {
			model.addAttribute("notexist", true);
			return "pages/user/login";
		} else if (checkBan(user)) {
			model.addAttribute("banned", true);
			return "pages/user/login";
		} else if (!password.equals(user.getPassword())) {
			model.addAttribute("passmiss", true);
			return "pages/user/login";
		}
		
		session.setAttribute("user", user);
		return user.getIsAdmin() ? "redirect:/admin/serialnumber" : "redirect:/";
	}

	/**
	 * @author Milione Vincent
	 * The method allows user to create an account
	 * @param user all user'data on the account
	 * @param session
	 * @param model
	 * @return sends user to login
	 */
	@PostMapping("/signUp")
	public String signUp(@Valid @ModelAttribute User user, HttpSession session, Model model) {
		if (isLogged(session)) 
			return "redirect:/";

		boolean flagUsername = dao.doesUsernameExist(user.getUsername());
		boolean flagEmail = dao.doesEmailExist(user.getMail());

		if (!flagEmail && !flagUsername) {
			//encript password
			user.setPassword(new Encoder(user.getUsername()).encoding(user.getPassword(), user.getUsername().length()));
			session.setAttribute("user", dao.create(user));

			return "redirect:/";
		} else {
			model.addAttribute("flagUsername", flagUsername);
			model.addAttribute("flagEmail", flagEmail);
			model.addAttribute("hasAttempted", true);
			model.addAttribute("user", user);

			return "pages/user/sign-Up";
		}
	}

	/**
	 * @author Milione Vincent
	 * The method allows agency to create an account
	 * @param user all agency's data on the account
	 * @param session
	 * @param model
	 * @return sends user to login
	 */
	@PostMapping("/signUpAgency")
	public String signUp(@Valid @ModelAttribute Agency user, HttpSession session, Model model) {
		if (isLogged(session)) 
			return "pages/user/home";

		boolean flagUsername = dao.doesUsernameExist(user.getUsername());
		boolean flagEmail = dao.doesEmailExist(user.getMail());

		if (!flagEmail && !flagUsername) {
			//encript password
			user.setPassword(new Encoder(user.getUsername()).encoding(user.getPassword(), user.getUsername().length()));
			session.setAttribute("user", dao.create(user));

			return "redirect:/";
		} else {
			model.addAttribute("flagUsername", flagUsername);
			model.addAttribute("flagEmail", flagEmail);

			return "redirect:/";
		}
	}
	
	/**
	 * @author Lamberti Vincenzo
	 * The method prepares the modify data html page, which allows users to change 
	 * the user's data
	 * @param session
	 * @param model
	 * @return sends user to the modify HTML page if user is logged, otherwise user is sent to login
	 */
	@GetMapping("/change-user-data")
	public String changeData (HttpSession session, Model model) {
		if (!isLogged(session))
			return "pages/user/login";
		
		Object obj = session.getAttribute("user");
		if (obj instanceof Agency) {
			Agency user = (Agency) obj;

			model.addAttribute("path", "/api/users/agency/"+user.getId());
			model.addAttribute("user", user);
			model.addAttribute("isAgency", true);
		} else if (obj instanceof User) {
			User user = (User) obj;
			
			model.addAttribute("path", "/api/users/"+user.getId());
			model.addAttribute("user", user);
			model.addAttribute("isAgency", false);
		}
		
		return "pages/user/modify-data";
	}


	/**
	 * @author Mario Paone
	 * Checks if the ban period is over
	 * @param user the user being checked
	 * 
	 * 
	 * */
	public boolean checkBan(User user){
        ArrayList<BanData> data = (ArrayList<BanData>) bandataDao.findAllByIdUser(user.getId());
        if (data.isEmpty())
            return false;
        Boolean isFinished = false;
        Date now = new Date(Calendar.getInstance().getTimeInMillis());
            if (data.get(data.size() - 1).getBanUntil().before(now)){
                isFinished = true;
            }


        if (isFinished){
            user.setIsBanned(false);
            dao.update(user);
		}
		
		return user.getIsBanned();
    }
}