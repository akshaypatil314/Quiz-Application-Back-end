package com.learn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

import com.learn.repository.UserRepository;
import com.learn.entity.Users;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public String createUser(Users user) {
		String str;
		if (userRepository.existsByEmail(user.getEmail())) {
			str = "User already exists";

		} else {
			userRepository.save(user);
			str = "User Added Successfully";
		}
		return str;
	}

	public Users getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

	public Users updateUser(Long id, Users updatedUser) {
		Users user = getUserById(id);
		user.setUsername(updatedUser.getUsername());
		user.setPassword(updatedUser.getPassword());
		user.setEmail(updatedUser.getEmail());
		user.setAdmin(updatedUser.isAdmin());
		return userRepository.save(user);
	}

	public void deleteUser(Long id) {
		Users user = getUserById(id);
		userRepository.delete(user);
	}

	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Users getUserByUsername(String username)
	{
		return userRepository.getUserByUsername(username);
	}

}
