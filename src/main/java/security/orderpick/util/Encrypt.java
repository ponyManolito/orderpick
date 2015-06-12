package security.orderpick.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;

@Component(Encrypt.name)
public class Encrypt {

	public static final String name = "encrypt";

	private final byte[] keyValue;

	public Encrypt() {

		this.keyValue = "OrderIGE83".getBytes();
	}

	public String encrypt(String plain) throws Exception {
		if (plain == null) {
			throw new IllegalArgumentException("String to encode cannot be null");
		}
		byte[] iv = new byte[16];
		new Random().nextBytes(iv);

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
		cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));

		byte[] padPlain = padString(plain);

		byte[] resultInBytes = cipher.doFinal(padPlain);

		resultInBytes = ArrayUtils.addAll(iv, resultInBytes);

		return Hex.encodeHexString(resultInBytes);
	}

	public String decrypt(String encoded) throws Exception {
		if (encoded == null) {
			throw new IllegalArgumentException("String to decode cannot be null");
		}
		byte[] decodeHex = Hex.decodeHex(encoded.toCharArray());
		byte[] cipherText = ArrayUtils.subarray(decodeHex, 16, decodeHex.length);
		byte[] iv = ArrayUtils.subarray(decodeHex, 0, 16);

		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
		SecretKeySpec key = new SecretKeySpec(keyValue, "AES");

		cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
		byte[] doFinal = cipher.doFinal(cipherText);

		return new String(doFinal, "UTF-8").trim();
	}

	private byte[] padString(String source) throws UnsupportedEncodingException {
		int blockSize = 16;
		int padLength = blockSize - source.getBytes().length % blockSize;

		byte[] bytes = new byte[padLength + source.getBytes().length];
		byte[] original = source.getBytes("UTF-8");

		for (int i = 0; i < original.length; i++) {
			bytes[i] = original[i];
		}
		for (int i = original.length; i < bytes.length; i++) {
			bytes[i] = 0;
		}

		return bytes;
	}

	public static void main(String[] args) {
		Encrypt encrypt = new Encrypt();
		try {
			System.out.println(encrypt.encrypt("Ivan2"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
