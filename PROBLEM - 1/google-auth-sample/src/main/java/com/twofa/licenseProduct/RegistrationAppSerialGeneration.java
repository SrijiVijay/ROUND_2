package com.twofa.licenseProduct;


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
public class RegistrationAppSerialGeneration {
	public static void main(String args[]) throws Exception {
		RegistrationAppSerialGeneration registrationAppSerialGeneration = new RegistrationAppSerialGeneration();
		java.io.InputStreamReader inputStreamReader = new java.io.InputStreamReader(System.in);
		java.io.BufferedReader bufferedReader = new java.io.BufferedReader(inputStreamReader);
		System.out.print("Enter Full Name: ");
		String fullNameString = bufferedReader.readLine().trim();
		String serialNumberEncoded = registrationAppSerialGeneration.calculateSecurityHash(fullNameString, "MD2")
				+ registrationAppSerialGeneration.calculateSecurityHash(fullNameString, "MD5")
				+ registrationAppSerialGeneration.calculateSecurityHash(fullNameString, "SHA1");

		String serialNumber = "" + serialNumberEncoded.charAt(32) + serialNumberEncoded.charAt(76)
				+ serialNumberEncoded.charAt(100) + serialNumberEncoded.charAt(50) + "-" + serialNumberEncoded.charAt(2)
				+ serialNumberEncoded.charAt(91) + serialNumberEncoded.charAt(73) + serialNumberEncoded.charAt(72)
				+ serialNumberEncoded.charAt(98) + "-" + serialNumberEncoded.charAt(47) + serialNumberEncoded.charAt(65)
				+ serialNumberEncoded.charAt(18) + serialNumberEncoded.charAt(85) + "-" + serialNumberEncoded.charAt(27)
				+ serialNumberEncoded.charAt(53) + serialNumberEncoded.charAt(102) + serialNumberEncoded.charAt(15)
				+ serialNumberEncoded.charAt(99);
		System.out.println("Serial Number: " + serialNumber);
		
		System.out.println("Key Number: " + generateKey().toString());
	}
	
	 private static KeyPair generateKey () throws Exception {
		    KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
		    SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

		    keyPairGen.initialize(1024,secureRandom);
		    KeyPair keyPair = keyPairGen.generateKeyPair();

		    return keyPair;
		  }

	private String calculateSecurityHash(String stringInput, String algorithmName)
			throws java.security.NoSuchAlgorithmException {
		String hexMessageEncode = "";
		byte[] buffer = stringInput.getBytes();
		java.security.MessageDigest messageDigest = java.security.MessageDigest.getInstance(algorithmName);
		messageDigest.update(buffer);
		byte[] messageDigestBytes = messageDigest.digest();
		for (int index = 0; index < messageDigestBytes.length; index++) {
			int countEncode = messageDigestBytes[index] & 0xff;
			if (Integer.toHexString(countEncode).length() == 1)
				hexMessageEncode = hexMessageEncode + "0";
			hexMessageEncode = hexMessageEncode + Integer.toHexString(countEncode);
		}
		return hexMessageEncode;
	}
}
