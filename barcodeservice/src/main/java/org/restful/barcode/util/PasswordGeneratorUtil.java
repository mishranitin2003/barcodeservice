package org.restful.barcode.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGeneratorUtil {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String pass = encoder.encode("yourpassword");
		System.out.println(pass);
	}
}
