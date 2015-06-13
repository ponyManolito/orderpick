package security.orderpick.util;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component(Encrypt.name)
public class Encrypt {

	public static final String name = "encrypt";

	SecretKey key = new SecretKeySpec(
	    Base64.decodeBase64("u/Gu5posvwDsXUnV5Zaq4g=="), "AES");
	AlgorithmParameterSpec iv = new IvParameterSpec(
	    Base64.decodeBase64("5D9r9ZVzEYYgha93/aUK2w=="));

	public Encrypt() {

	}

	public String encrypt(String plain) throws Exception {
		
		Cipher cipher;
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		return Base64.encodeBase64String(cipher.doFinal(
			    plain.getBytes("UTF-8")));
	}

	public String decrypt(String encrypted) throws Exception {
		Cipher cipher;
		cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decodedValue = new Base64().decode(encrypted);
        byte[] decValue = cipher.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
	}
}
