package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;



@Service
@Transactional
public class UserService {
	private final UserRepository userrepository;
	@Autowired
	UserService userservice;
	public UserService (UserRepository userrepository) {
		this.userrepository=userrepository;
	}
	public void savemyuser(User user) {
		userrepository.save(user);
	}
	public List<User> showAllUsers(){
		List<User> users = new ArrayList<User>();
		for(User user : userrepository.findAll()) {
			users.add(user);
		}
		
		return users;
	}
	public void deleteMyUser(int id) {
		userrepository.deleteById(id);
		
	}

	public void editUser(int id) {
		userrepository.findById(id);
		
	}
	public User findByUsernameAndPassword(String username, String password) {
		return userrepository.findByUsernameAndPassword(username, password);
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
