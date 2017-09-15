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
		System.out.println();
		FileInputStream file = new FileInputStream(
				classLoader.getResource("properties/messages.properties").getFile());
		props.load(file);
		return props;

	}
}
