package main;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;

public class Hashing {

	private Hashing() {}
	
	public static String hashPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[]saltBytes = Base64.getDecoder().decode(salt);		
		PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),saltBytes,65556,256);
		String algorithm = "PBKDF2WithHmacSHA256";
		SecretKeyFactory factory = SecretKeyFactory.getInstance(algorithm);
		byte[] hash = factory.generateSecret(spec).getEncoded();		
		return Base64.getEncoder().encodeToString(hash);
	    
	}
	
	public static String generateSalt() {
		SecureRandom ran = new SecureRandom();
		byte[] salt = new byte[16];
		ran.nextBytes(salt);		
		return Base64.getEncoder().encodeToString(salt);
		
	}
	
	
	
	
	
		
}
