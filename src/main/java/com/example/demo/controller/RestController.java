package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	private UserService userservice;
	@GetMapping("/")
	public String Hello() {
		return "This is the hello page";
	}
	@GetMapping("/saveuser")
	public String saveUser(@RequestParam String username,@RequestParam String firstname,@RequestParam String lastname,@RequestParam int age,@RequestParam String password) {
		User user=new User(username,firstname,lastname,age,password);
		
		userservice.savemyuser(user);
		return "User is saved";
		
	}
	
}
