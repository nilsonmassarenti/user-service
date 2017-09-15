package com.nilsonmassarenti.app.userservice.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class General {
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		FileInputStream file = new FileInputStream(
				classLoader.getResource("properties/messages.properties").getFile());
		System.out.println(classLoader.getResource("db/db.sql").getPath());	
		props.load(file);
		return props;

	}
}
