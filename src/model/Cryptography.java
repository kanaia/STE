package model;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Die Klasse Cryptography, ein Kryptographie-Element welches in AES und DES
 * ver- und entschlüsseln kann.
 * 
 * @author Stefan Böhling
 */
public class Cryptography {

	/** The secret. */
	private Secret secret;

	/**
	 * Encrypt a String with AES.
	 *
	 * @param s
	 *            the String to encrypt
	 * @param file
	 *            the file
	 * @return the encrypted String
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws ShortBufferException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String encryptAES(String s, EncodedFile file) throws Exception {
		String ciphermethod = "";
		if (file.getPadding() != null) {
			ciphermethod = "AES/" + file.getBlockMode() + "/" + file.getPadding();
		}else{
			ciphermethod = "AES/" + file.getBlockMode() + "/NoPadding";
		}
		Hashing hash = new Hashing();
		secret = new Secret("AES", file.getKeyLength());
		byte[] input = s.getBytes(StandardCharsets.US_ASCII);

		Cipher cipher = Cipher.getInstance(ciphermethod, "BC");

		IvParameterSpec ivSpec = new IvParameterSpec(file.getIvBytes());

		if (file.getBlockMode().equals("ECB")) {
			cipher.init(Cipher.ENCRYPT_MODE, secret.getKey());
		} else {
			cipher.init(Cipher.ENCRYPT_MODE, secret.getKey(), ivSpec);
		}
		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];

		int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
		ctLength += cipher.doFinal(cipherText, ctLength);

		String cipherTextString = Base64.getEncoder().encodeToString(cipherText);
		if (file.getHashMode() != null) {
			file.setHashValue(hash.generateHash(file.getHashMode(), cipherTextString));
		}

		return cipherTextString;
	}

	/**
	 * Decrypt AES.
	 *
	 * @param file
	 *            the file
	 * @param decSecret
	 *            the secret holding the key to decrypt
	 * @return the decrypted string
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws ShortBufferException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String decryptAES(EncodedFile file, Secret decSecret) throws Exception {
		String ciphermethod = "";
		if (file.getPadding() != null) {
			ciphermethod = "AES/" + file.getBlockMode() + "/" + file.getPadding();
		}else{
			ciphermethod = "AES/" + file.getBlockMode() + "/NoPadding";
		}

		Hashing hash = new Hashing();
		if (file.getHashMode() != null
				&& hash.validateHash(file.getHashMode(), file.getCipherText(), file.getHashValue())) {
			byte[] input = Base64.getDecoder().decode(file.getCipherText());
			Key decryptionKey = new SecretKeySpec(decSecret.getKeyBytes(), "AES");

			IvParameterSpec ivSpec = new IvParameterSpec(file.getIvBytes());

			Cipher cipher = Cipher.getInstance(ciphermethod, "BC");

			if (file.getBlockMode().equals("ECB")) {
				cipher.init(Cipher.DECRYPT_MODE, decryptionKey);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, decryptionKey, ivSpec);
			}
			byte[] plainText = new byte[cipher.getOutputSize(input.length)];

			int ctLength = cipher.update(input, 0, input.length, plainText, 0);
			ctLength += cipher.doFinal(plainText, ctLength);

			return new String(plainText, StandardCharsets.US_ASCII).trim();
		} else {
			System.out.println("File has been tampered with.");
			return "TAMPERED_FILE";
		}
	}

	/**
	 * Encrypt DES.
	 *
	 * @param s
	 *            the String to encrypt
	 * @param file
	 *            the file
	 * @return the encrypted String
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws ShortBufferException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String encryptDES(String s, EncodedFile file) throws Exception {
		String ciphermethod = "";
		if (file.getPadding() != null) {
			ciphermethod = "AES/" + file.getBlockMode() + "/" + file.getPadding();
		}else{
			ciphermethod = "AES/" + file.getBlockMode() + "/NoPadding";
		}
		Hashing hash = new Hashing();
		byte[] input = s.getBytes(StandardCharsets.US_ASCII);
		secret = new Secret("DES", 64);

		IvParameterSpec ivSpec = new IvParameterSpec(file.getIvBytes());

		Cipher cipher = Cipher.getInstance(ciphermethod, "BC");

		if (file.getBlockMode().equals("ECB")) {
			cipher.init(Cipher.ENCRYPT_MODE, secret.getKey());
		} else {
			cipher.init(Cipher.ENCRYPT_MODE, secret.getKey(), ivSpec);
		}
		byte[] cipherText = new byte[cipher.getOutputSize(input.length)];

		int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
		ctLength += cipher.doFinal(cipherText, ctLength);

		String cipherTextString = Base64.getEncoder().encodeToString(cipherText);
		if (file.getHashMode() != null) {
			file.setHashValue(hash.generateHash(file.getHashMode(), cipherTextString));
		}

		return cipherTextString;
	}

	/**
	 * Decrypt DES.
	 *
	 * @param file
	 *            the file
	 * @param decSecret
	 *            the secret holding the key to decrypt
	 * @return the decrypted string
	 * @throws NoSuchPaddingException
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws ShortBufferException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String decryptDES(EncodedFile file, Secret decSecret) throws Exception {
		String ciphermethod = "";
		if (file.getPadding() != null) {
			ciphermethod = "AES/" + file.getBlockMode() + "/" + file.getPadding();
		}else{
			ciphermethod = "AES/" + file.getBlockMode() + "/NoPadding";
		}
		Hashing hash = new Hashing();
		if (file.getHashMode() != null
				&& hash.validateHash(file.getHashMode(), file.getCipherText(), file.getHashValue())) {
			byte[] input = Base64.getDecoder().decode(file.getCipherText());
			Key decryptionKey = new SecretKeySpec(decSecret.getKeyBytes(), "DES");

			IvParameterSpec ivSpec = new IvParameterSpec(file.getIvBytes());

			Cipher cipher = Cipher.getInstance(ciphermethod, "BC");

			if (file.getBlockMode().equals("ECB")) {
				cipher.init(Cipher.DECRYPT_MODE, decryptionKey);
			} else {
				cipher.init(Cipher.DECRYPT_MODE, decryptionKey, ivSpec);
			}
			byte[] plainText = new byte[cipher.getOutputSize(input.length)];

			int ctLength = cipher.update(input, 0, input.length, plainText, 0);
			ctLength += cipher.doFinal(plainText, ctLength);

			return new String(plainText, StandardCharsets.US_ASCII).trim();
		} else {
			System.out.println("File has been tampered with.");
			return "TAMPERED_FILE";
		}
	}

	/**
	 * Gets the secret.
	 *
	 * @return the secret
	 */
	public Secret getSecret() {
		return secret;
	}

	/**
	 * Sets the secret.
	 *
	 * @param secret
	 *            the new secret
	 */
	public void setSecret(Secret secret) {
		this.secret = secret;
	}

}
