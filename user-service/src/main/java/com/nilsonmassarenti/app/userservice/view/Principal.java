package com.nilsonmassarenti.app.userservice.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = {
		"com.nilsonmassarenti.app.userservice.config",
		"com.nilsonmassarenti.app.userservice.controller",
		"com.nilsonmassarenti.app.userservice.dao",
		"com.nilsonmassarenti.app.userservice.entity",
		"com.nilsonmassarenti.app.userservice.rest",
		"com.nilsonmassarenti.app.userservice.utils"
		
})
public class Principal {
	public static void main(String[] args) {
		SpringApplication.run(Principal.class, args);

	}
}
