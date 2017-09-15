package com.nilsonmassarenti.app.userservice.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nilsonmassarenti.app.userservice.dao.UserDAO;
import com.nilsonmassarenti.app.userservice.entity.User;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDAO;
	
	public User add(User user){
		
		if (userDAO.searchByEmail(user.getEmail()) != null) {
			return null;
		} else {
			user.setCreated(LocalDateTime.now().toString());
			user.setModified(LocalDateTime.now().toString());
			user.setLast_login(LocalDateTime.now().toString());
			user.setToken(UUID.randomUUID().toString().replace("-", ""));
			user = userDAO.add(user);			
		}
		return user;
		
	}
	
	public User login(User user) {
		if (userDAO.checkUserAndPass(user)) {
			user = userDAO.searchByEmail(user.getEmail());
			user.setLast_login(LocalDateTime.now().toString());
			user.setToken(UUID.randomUUID().toString().replace("-", ""));
			if (!userDAO.update(user)) {
				user.setToken(null);
			} 
			return user;
		} else if (userDAO.searchByEmail(user.getEmail()) != null){
			user.setId(null);
			return user;
		} else {
			return null;
		}
	}
	
	public User profile(String id, String token){
		User user = userDAO.checkIdAndToken(id, token);
		if (user != null) {
			LocalDateTime lastLogin = LocalDateTime.parse(user.getLast_login());
			LocalDateTime tempDateTime = LocalDateTime.now();
			if (tempDateTime.until(lastLogin, ChronoUnit.MINUTES) >=30) {
				user.setLast_login(null);
			}
		}
		return user;
	}
	
}
