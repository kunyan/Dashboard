package org.yankun.dashboard.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class KeyUtils {
	
	private static byte[] HmacSHA1Encrypt(String url, String privatekey)
			throws Exception {
		byte[] data = privatekey.getBytes("UTF-8");
		SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secretKey);
		byte[] text = url.getBytes("UTF-8");
		return mac.doFinal(text);
	}

	public static String getKey(String url, String privatekey){
		try {
			byte[] key_bytes = HmacSHA1Encrypt(url, privatekey);
			return Base64.encodeBase64String(key_bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}
