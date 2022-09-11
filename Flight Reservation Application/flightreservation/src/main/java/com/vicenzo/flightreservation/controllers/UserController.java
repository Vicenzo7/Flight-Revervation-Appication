package com.vicenzo.flightreservation.controllers;

import com.vicenzo.flightreservation.entities.User;
import com.vicenzo.flightreservation.repos.UserRepository;
import com.vicenzo.flightreservation.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SecurityService securityService;

	@RequestMapping("/showReg")
	public String showRegisterationPage() {
		LOGGER.info("Inside showRegisterationPage()");
		return "login/registerUser";
	}

	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}


	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user){
		LOGGER.info("Inside register()"+user);

		user.setPassword(encoder.encode(user.getPassword()));

		userRepository.save(user);
		return "login/login";
	}


	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap){

		LOGGER.info("Inside login() and the email is: "+email);


		boolean loginResponse = securityService.login(email, password);

		if(loginResponse){
			return "findFlights";
		}else{

			modelMap.addAttribute("msg","Invalid username or password. Please try again");
		}

		return "login/login";


	}

}
