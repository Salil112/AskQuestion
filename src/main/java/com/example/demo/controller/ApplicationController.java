package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class ApplicationController {
	@Autowired
	UserService userservice;

	
	@RequestMapping("/welcome")
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER");
		return "welcomepage";
	}
		
	@PostMapping("/savemyuser")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
		userservice.savemyuser(user);
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}
	@GetMapping("/showuser")
	public String showAllUSer(HttpServletRequest request) {
		request.setAttribute("users", userservice.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		
		return "welcomepage";
	}

	
@RequestMapping("/delete-user")
public String deleteUser(@RequestParam int id, HttpServletRequest request) {
	userservice.deleteMyUser(id);
	request.setAttribute("users", userservice.showAllUsers());
	request.setAttribute("mode", "ALL_USERS");
	return "welcomepage";
}
@RequestMapping("/edit-user")
public String editUser(@RequestParam int id,HttpServletRequest request) {
	request.setAttribute("users", userservice.editUser(id));
	request.setAttribute("mode", "MODE_UPDATE");
	return "welcomepage";
}
@RequestMapping("/login")
public String login(HttpServletRequest request) {
	request.setAttribute("mode", "MODE_LOGIN");
	return "welcomepage";
}

@RequestMapping ("/login-user")
public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
	if(userservice.findByUsernameAndPassword(user.getUsername(), user.getPassword())!=null) {
		return "homepage";
	}
	else {
		request.setAttribute("error", "Invalid Username or Password");
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
		
	}
}
}

