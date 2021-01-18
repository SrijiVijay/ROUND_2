package com.project.paymentservice.interfaces;

import javax.crypto.Cipher;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

/**
 * The Class EncryptUtil.
 */
@Service
public class EncryptUtil {
	
//	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
//		fixKeyLength();
//		decryptData("U2FsdGVkX1/QrkV902wQ141Vhf8HhtQnzaxQ2UcWCxZasqefk08BKxXS/S+XJKk0wMfEY1xBlDsGNZd6ag2wKdsNY4gJHdFyrbVeMnEZUoMpxrosMNRQjY75cP+GWDpvyv1/xx5PWCIpQ/Q+oOwmSTqycQbL261zsxZpFQvjjQeJ6R7w+cghGECdnCiNrN7BKZm8xCXF1hDrr8rDvQp7ms2NHTMhjy/uMatfMH6l7K4tBhcm2755lPeRHlhxObEcWqGOp0HXyBbd5bfxwY5+xdF0n3LlpSann6sV8uYUuZOzvBHqS/W8b2P5OK//FyxIQlJ038hKh5jGKU0GbabW4uDgC72rZ8r7lYflYIaok8YZDc6fN7y4RW1HKLzctbNf");
//	}
	
	/**
 * Fix key length.
 */
public static void fixKeyLength() {
	    String errorString = "Failed manually overriding key-length permissions.";
	    int newMaxKeyLength;
	    try {
	        if ((newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES")) < 256) {
	            Class c = Class.forName("javax.crypto.CryptoAllPermissionCollection");
	            Constructor con = c.getDeclaredConstructor();
	            con.setAccessible(true);
	            Object allPermissionCollection = con.newInstance();
	            Field f = c.getDeclaredField("all_allowed");
	            f.setAccessible(true);
	            f.setBoolean(allPermissionCollection, true);

	            c = Class.forName("javax.crypto.CryptoPermissions");
	            con = c.getDeclaredConstructor();
	            con.setAccessible(true);
	            Object allPermissions = con.newInstance();
	            f = c.getDeclaredField("perms");
	            f.setAccessible(true);
	            ((Map) f.get(allPermissions)).put("*", allPermissionCollection);

	            c = Class.forName("javax.crypto.JceSecurityManager");
	            f = c.getDeclaredField("defaultPolicy");
	            f.setAccessible(true);
	            Field mf = Field.class.getDeclaredField("modifiers");
	            mf.setAccessible(true);
	            mf.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	            f.set(null, allPermissions);

	            newMaxKeyLength = Cipher.getMaxAllowedKeyLength("AES");
	        }
	    } catch (Exception e) {
	        throw new RuntimeException(errorString, e);
	    }
	    if (newMaxKeyLength < 256)
	        throw new RuntimeException(errorString); // hack failed
	}
	
	 /**
 	 * Decrypt data.
 	 *
 	 * @param cipherText the cipher text
 	 * @return the string
 	 * @throws NoSuchAlgorithmException the no such algorithm exception
 	 * @throws NoSuchPaddingException the no such padding exception
 	 * @throws InvalidKeyException the invalid key exception
 	 * @throws InvalidAlgorithmParameterException the invalid algorithm parameter exception
 	 * @throws IllegalBlockSizeException the illegal block size exception
 	 * @throws BadPaddingException the bad padding exception
 	 */
 	public static String decryptData(String cipherText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		fixKeyLength();
		String secret = "run4Fun";

		byte[] cipherData = Base64.getDecoder().decode(cipherText);
		byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
		SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
		IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

		byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
		Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
		aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decryptedData = aesCBC.doFinal(encrypted);
		String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);

		System.out.println("decryptedText :"+ decryptedText);
		return decryptedText;
	
	}
	
	/**
	 * Generate key and IV.
	 *
	 * @param keyLength the key length
	 * @param ivLength the iv length
	 * @param iterations the iterations
	 * @param salt the salt
	 * @param password the password
	 * @param md the md
	 * @return the byte[][]
	 */
	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

	    int digestLength = md.getDigestLength();
	    int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
	    byte[] generatedData = new byte[requiredLength];
	    int generatedLength = 0;

	    try {
	        md.reset();

	        // Repeat process until sufficient data has been generated
	        while (generatedLength < keyLength + ivLength) {

	            // Digest data (last digest if available, password data, salt if available)
	            if (generatedLength > 0)
	                md.update(generatedData, generatedLength - digestLength, digestLength);
	            md.update(password);
	            if (salt != null)
	                md.update(salt, 0, 8);
	            md.digest(generatedData, generatedLength, digestLength);

	            // additional rounds
	            for (int i = 1; i < iterations; i++) {
	                md.update(generatedData, generatedLength, digestLength);
	                md.digest(generatedData, generatedLength, digestLength);
	            }

	            generatedLength += digestLength;
	        }

	        // Copy key and IV into separate byte arrays
	        byte[][] result = new byte[2][];
	        result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	        if (ivLength > 0)
	            result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

	        return result;

	    } catch (DigestException e) {
	        throw new RuntimeException(e);

	    } finally {
	        // Clean out temporary data
	        Arrays.fill(generatedData, (byte)0);
	    }
	}
}
