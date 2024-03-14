package sv.bandesal.app.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

final public class SecurityUtils {

	private SecurityUtils() {
	}

	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();

		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < digest.length; i++) {
			buffer.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
		}

		return buffer.toString();
	}

	public static boolean validateMd5(String password, String md5String) {
		try {
			return md5String.equalsIgnoreCase(md5(password));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static PasswordEncoder bCryptencoder() {
		return new BCryptPasswordEncoder();
	}

}
