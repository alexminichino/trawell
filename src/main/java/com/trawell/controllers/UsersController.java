package com.trawell.controllers;

import javax.servlet.http.HttpSession;

import com.trawell.models.Encoder;
import com.trawell.models.User;
import com.trawell.services.UserService;

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
	@GetMapping("/login.html")
	public String loginPage (HttpSession session) {
		return isLogged(session) ? "pages/user/home" : "pages/user/login";
	}

	/**
	 * @author Milione Vincent
	 * The method maps user's get request to sign-up page
	 * @param session
	 * @return URL of the sign-up page if the user isn't already logged in
	 */
	@GetMapping("/sign-up.html")
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
	 * @return sends user to its home page
	 */
	@PostMapping("/login")
    public String login(@RequestParam(name="username", required=true) String username,@RequestParam(name="password", required=true) String password, HttpSession session, Model model) {
		
		if (isLogged(session)) return "pages/user/home"; 

		User user = dao.findByUsername(username);
		password = new Encoder(username).encoding(password, username.length());

		if (user == null) {
			model.addAttribute("notexist", true);
			return "pages/user/login";
		} else if (!password.equals(user.getPassword())) {
			model.addAttribute("passmiss", true);
			return "pages/user/login";
		} else 

		session.setAttribute("user", user);
		return "pages/user/home";
	}

	/**
	 * @author Milione Vincent
	 * The method allows user to create an account
	 * @param user all user'data on the account
	 * @param model
	 * @return sends useer to login
	 */
	@PostMapping("/signUp")
	public String signUp(@ModelAttribute User user, HttpSession session, Model model) {
		if (isLogged(session)) return "pages/user/home"; 
		
		dao.create(user);
		return "pages/user/login";
	}
}