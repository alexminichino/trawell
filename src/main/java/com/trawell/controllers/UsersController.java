package com.trawell.controllers;

import static org.apache.tomcat.util.security.MD5Encoder.*;
import java.text.DateFormat;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import com.trawell.models.User;
import com.trawell.services.UserService;
import java.util.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UsersController qui andranno mappate tutte le funzionalità relative all'utente per la comunicazione MVC
 */
@Controller
@RequestMapping("users")
public class UsersController {

	private UserService dao;

	private String generateSalt (User user) {
		return user.getBirth().toString() + user.getUsername() + "superkalifragilistic";
	}

	private String addSalt (String salt, String encodify) {
		return encode (salt.getBytes()) + encode(encodify.getBytes());
	}

	private String encoding (String encodify, String salt, int nof) {
		
		int n = nof;

		for (int i = 0; i < n; i++)
			encodify = i % 2 == 0 ? encode(encodify.getBytes()) : encode(addSalt(salt, encodify).getBytes());

		return null;
	}

	@GetMapping("/login.html")
	public String loginPage () {
		return "pages/user/login";
	}

	@GetMapping("/sign-up.html")
	public String signUpPage () {
		return "pages/user/sign-Up";
	}

	@PostMapping("/login")
    public String login(@RequestParam(name="username", required=true) String username,@RequestParam(name="password", required=true) String password, HttpSession session, Model model) {
		//filtrare
		User user = dao.findByUsername(username);

		if (user == null) {
			model.addAttribute("notexist", true);
			return "pages/user/login";
		} else if (!password.equals(encoding(password, generateSalt(user), username.length()))) {
			model.addAttribute("passmiss", true);
			return "pages/user/login";
		} 

		session.setAttribute("username", username);
		session.setAttribute("isUser", true);
		return "pages/user/home";
	}

	@PostMapping("/signUp")
	public String signUp(@ModelAttribute User user, Model model) {
		user.setPassword(encoding(user.getPassword(), generateSalt(user), user.getUsername().length()));
		user.setName(encoding(user.getName(), generateSalt(user), user.getUsername().length()));
		user.setSurname(encoding(user.getName(), generateSalt(user), user.getUsername().length()));
		dao.create(user);

		return "pages/user/login";
	}

	@PostMapping("/modifyData")
	public String modify (@ModelAttribute User user, Model model) {
		return null;
	}
}