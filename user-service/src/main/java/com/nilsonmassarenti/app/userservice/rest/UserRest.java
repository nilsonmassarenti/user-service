package com.nilsonmassarenti.app.userservice.rest;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nilsonmassarenti.app.userservice.controller.UserController;
import com.nilsonmassarenti.app.userservice.entity.Mensagem;
import com.nilsonmassarenti.app.userservice.entity.User;
import com.nilsonmassarenti.app.userservice.utils.General;

@RestController
@RequestMapping("/user")
public class UserRest {
	
	@Autowired
	private UserController userController;
	
	private Properties prop;
	
	public UserRest() throws IOException{
		this.prop = General.getProp();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody User user){
		User newUser = userController.add(user);
		if (newUser == null) {
			Mensagem msg = new Mensagem();
			msg.setMensagem(prop.getProperty("msg.erro.0001"));	
			return new ResponseEntity<Mensagem>(msg ,HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(newUser ,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> getLogin(@RequestBody User user){
		User newUser = userController.login(user);
		if (newUser == null) {
			Mensagem msg = new Mensagem();
			msg.setMensagem(prop.getProperty("msg.erro.0002"));	
			return new ResponseEntity<Mensagem>(msg ,HttpStatus.FORBIDDEN);
		} else if (newUser.getId() == null) {
			Mensagem msg = new Mensagem();
			msg.setMensagem(prop.getProperty("msg.erro.0002"));	
			return new ResponseEntity<Mensagem>(msg ,HttpStatus.UNAUTHORIZED);
		} else {
			return new ResponseEntity<User>(newUser ,HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> checkUser(HttpServletRequest request, 
			@PathVariable("id") String id){
		Mensagem msg = new Mensagem();
		if (request.getHeader("Token") == null) {
			
			msg.setMensagem(prop.getProperty("msg.erro.0003"));
			return new ResponseEntity<Mensagem>(msg ,HttpStatus.UNAUTHORIZED);
		} else {
			User newUser = userController.profile(id, request.getHeader("Token"));
			if (newUser == null) {
				msg.setMensagem(prop.getProperty("msg.erro.0003"));
				return new ResponseEntity<Mensagem>(msg ,HttpStatus.UNAUTHORIZED);
			} else if (newUser.getLast_login() == null) {
				msg.setMensagem(prop.getProperty("msg.erro.0004"));
				return new ResponseEntity<Mensagem>(msg ,HttpStatus.FORBIDDEN);
			} else {
				return new ResponseEntity<User>(newUser ,HttpStatus.OK);
			}
		}
		
	}
}
