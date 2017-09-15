package com.nilsonmassarenti.app.userservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.nilsonmassarenti.app.userservice.entity.User;

@Transactional
@Repository
public class UserDAO {
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public UserDAO(){
	}
	
	public UserDAO(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//Check user by email
	public User searchByEmail(String email){

		List<?> results = hibernateTemplate.find("FROM User u WHERE u.email = ?", email);
		User user = null;
		if (results.size() > 0) {
			user = (User) results.get(0);
		}
		return user;
	}
	
	public User add(User user){
		String id =  (String) hibernateTemplate.save("User", user);
		user = hibernateTemplate.get(User.class, id);
		return user;
	}
	
	public Boolean checkUserAndPass(User user){
		List<?> results = hibernateTemplate.find("FROM User u WHERE u.email = ? AND u.password = ?", user.getEmail(), user.getPassword());
		if (results.size() > 0) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Boolean update(User user){
		try {
			hibernateTemplate.merge(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public User checkIdAndToken(String id, String token){
		List<?> results = hibernateTemplate.find("FROM User u WHERE u.id = ? AND u.token = ?", id, token);
		User user = null;
		if (results.size() > 0) {
			user = (User) results.get(0);
		}
		return user;
	}
	
	
}
