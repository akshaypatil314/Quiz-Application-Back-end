package com.learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.learn.entity.Users;
import java.util.Map;

import com.learn.repository.UserRepository;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
	@Autowired
	private UserRepository userRepo;
	
	@PostMapping("/login")
	public String Login(@RequestBody Map<String, String> credential ) {
		String email = credential.get("email");
		String password = credential.get("password");
		List<Users> userByEmail = userRepo.findByEmail(email);
		for(Users user: userByEmail) {
			if(password.equals(user.getPassword())) {
				if(user.isAdmin() == true) {
					return "admin";
				}else {
					return "user";
				}
			}
		}
		
		return "invalid";
	}
}
