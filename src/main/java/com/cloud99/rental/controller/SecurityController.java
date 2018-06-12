package com.cloud99.rental.controller;

import com.cloud99.rental.dataCreation.DataCreator;
import com.cloud99.rental.domain.account.Account;
import com.cloud99.rental.domain.security.User;
import com.cloud99.rental.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class SecurityController {

	@Autowired
	private DataCreator dataCreator;

	@Autowired
	private UserService userService;

	@GetMapping("/loadTestData")
	@ResponseBody
	public Account test(@RequestParam String name) {
		Account acct = dataCreator.execute();
		return acct;
	}

	@GetMapping(value = "/user/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
		model.addAttribute("user", userService);
		return "registration";
	}
	
	@PostMapping("/user/registration")
	public User registerUserAccount(@ModelAttribute("user") @Valid User user, BindingResult result,
			HttpServletRequest request,
			Errors errors) {
		
		if (!result.hasErrors()) {
			return userService.create(user, request);
		} else {
			// TODO - parse validation error and return to the client
			return null;
		}
	
	}
}
