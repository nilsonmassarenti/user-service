package com.nilsonmassarenti.app.userservice.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

@Service
public class MD5 {
	public String convertStringToMD5(String key) {
		String md5Key = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(key.getBytes(), 0, key.length());
			md5Key = new BigInteger(1,md5.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {
			md5Key = null;
			e.printStackTrace();
		}
		return md5Key;
	}
}
