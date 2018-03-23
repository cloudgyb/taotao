package com.taotao.common.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * Base64 加解密工具类
 * @author Administrator
 *
 */
@SuppressWarnings("restriction")
public class DESUtil {
	private static Key key;
	private static String KEY_STR = "mykey";

	static {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("DES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(KEY_STR.getBytes());
			generator.init(secureRandom);
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对字符串进行加密，返回BASE64的加密字符串 
	 * 
	 * @param str
	 * @return String
	 */
	public static String getEncryptString(String str) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		//System.out.println(key);
		try {
			byte[] strBytes = str.getBytes("UTF-8");
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return base64Encoder.encode(encryptStrBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * 对BASE64加密字符串进行解密 <功能详细描述>
	 * 
	 * @param str
	 * @return string
	 */
	public static String getDecryptString(String str) {
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			byte[] strBytes = base64Decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] encryptStrBytes = cipher.doFinal(strBytes);
			return new String(encryptStrBytes, "UTF-8");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		String name = "root";
		String password = "123456";
		String encryname = getEncryptString(name);
		String encrypassword = getEncryptString(password);
		System.out.println(encryname);
		System.out.println(encrypassword);

		System.out.println(getDecryptString(encryname));
		System.out.println(getDecryptString(encrypassword));
	}
}