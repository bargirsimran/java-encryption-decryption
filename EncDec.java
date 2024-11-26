package org.dnyanyog.encryption;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class EncDec {

	public static SecretKey generateAESKey() throws NoSuchAlgorithmException { //Key Generate
		
		KeyGenerator keyGenerator=KeyGenerator.getInstance("AES"); //Create instance of keyGenerator using static method
		
		keyGenerator.init(256); //use 256-bit key size
		
		return keyGenerator.generateKey();
	}
	public static String encrypt(String plaintext, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(cipher.ENCRYPT_MODE, key);
		
		byte[] plainTextByte = plaintext.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedBytes = cipher.doFinal(plainTextByte);
		
		String encryptedDataInString = Base64.getEncoder().encodeToString(encryptedBytes);
		return encryptedDataInString;
		
	}
	
	public static String decrypt(String encryptedData, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		
		byte[] encryptedByteArrayData = Base64.getDecoder().decode(encryptedData);
		byte[] decryptedBytes = cipher.doFinal(encryptedByteArrayData);
		
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		
		//Step 1: generate key and store in some secure  place
		SecretKey key=generateAESKey();
		
		String plaintext = "Simran@123";
		
		//Step 2: Encrypt data using generated key
		String encryptedText = encrypt(plaintext, key);
		System.out.println("Encrypted: " +encryptedText);
		
		//Step 3: Decrypt data using generated key
		String decryptedText = decrypt(encryptedText, key);
		System.out.println("Decrypted: " +decryptedText);
		
	}
	
}
