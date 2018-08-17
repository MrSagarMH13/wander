package com.wander.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wander.model.User;
import com.wander.service.UserService;

/**
 * 
 * @author mrsagar
 *
 */
@Controller
public class RegisterController {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	UserService userService;

	@RequestMapping("/ping")
	@ResponseBody
	public String ping() {
		return "I am ok";
	}

	// Return registration form template
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user,
			BindingResult bindingResult) {

		// Lookup user in database by e-mail
		User userExists = userService.findByEmail(user.getEmail());

		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage",
					"Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else {
			user.setEnabled(true);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userService.saveUser(user);
			// modelAndView.addObject("confirmationMessage", "Account created
			// successfully for user " + user.getEmail());
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(ModelAndView modelAndView) {
		modelAndView.setViewName("login");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView processLogin(ModelAndView modelAndView, @RequestParam Map requestParams,
			BindingResult bindingResult) {
		if (requestParams.get("email") != null) {
			User userExists = userService.findByEmail(requestParams.get("email").toString());
			if (userExists == null) {
				modelAndView.addObject("errorMessage", "Oops! email is not registered with the email provided.");
				modelAndView.setViewName("register");
				bindingResult.reject("email");
			}

			if (bindingResult.hasErrors()) {
				modelAndView.setViewName("login");
			} else {
				String password = requestParams.get("password").toString();
				if (bCryptPasswordEncoder.matches(password, userExists.getPassword())) {
					modelAndView.setViewName("redirect:/notes");
				}
			}
		} else {
			modelAndView.addObject("errorMessage", "Please enter emailid");
			modelAndView.setViewName("login");
			bindingResult.reject("email");
		}

		return modelAndView;
	}
}