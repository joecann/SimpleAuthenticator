package main;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Authenticate {
	
	public static boolean validateEmail(String email) {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(matcher.find()) return true;		
		return false;
	}
	
	public static boolean password(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {		
		String salt = MySQL.getSalt(email);
		String input = Hashing.hashPassword(password,salt);	
		return MySQL.getPassword(email).equals(input);
	}
	
	public static boolean email(String email) {
		return MySQL.checkEmail(email);
	}
		

}
