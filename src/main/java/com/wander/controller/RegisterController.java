package com.wander.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wander.model.Role;
import com.wander.model.User;
import com.wander.service.RoleService;
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

	@Autowired
	RoleService roleService;

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
			Role userRole = roleService.findByRole("ADMIN");
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			userService.saveUser(user);
			modelAndView.setViewName("redirect:/login");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "login";
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

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}
}