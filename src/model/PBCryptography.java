package model;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Die Klasse PBCryptography, ein Kryptographie-Element welches die Passwort
 * basierte Kryptographie benutzt um Texte zu ver- und entschlüsseln.
 * @author Stefan Böhling
 */
public class PBCryptography {

	/**
	 * Encode a String with Password-based Encryption with SHA and 128bit AES
	 * and CBC.
	 *
	 * @param s
	 *            the String to encode
	 * @param file
	 *            the file
	 * @param password
	 *            the password
	 * @return the encoded String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String encodePBEWithSHAAnd128BitAES_CBC_BC(String s, EncodedFile file, char[] password) throws Exception {
		byte[] input = s.getBytes(StandardCharsets.US_ASCII);
		Hashing hash = new Hashing();

		byte[] salt = generateSalt(8);
		file.setSalt(salt);
		int iterationCount = 2048;

		PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationCount);
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
		SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

		Cipher encryptionCipher = Cipher.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
		encryptionCipher.init(Cipher.ENCRYPT_MODE, pbeKey);

		byte[] cipherText = encryptionCipher.doFinal(input);

		String cipherTextString = Base64.getEncoder().encodeToString(cipherText);
		if (file.getHashMode() != null) {
			file.setHashValue(hash.generateHash(file.getHashMode(), cipherTextString));
		}

		return cipherTextString;
	}

	/**
	 * Decode password based encryption with SHA and 128bit AES CBC.
	 *
	 * @param file
	 *            the file
	 * @param password
	 *            the password
	 * @return the decoded string
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String decodePBEWithSHAAnd128BitAES_CBC_BC(EncodedFile file, char[] password) throws Exception {
		Hashing hash = new Hashing();
		if (file.getHashMode() != null
				&& hash.validateHash(file.getHashMode(), file.getCipherText(), file.getHashValue())) {
			byte[] cipherText = Base64.getDecoder().decode(file.getCipherText());

			byte[] salt = file.getSalt();
			int iterationCount = 2048;

			PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationCount);
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			Cipher cDec = Cipher.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
			cDec.init(Cipher.DECRYPT_MODE, pbeKey);
			byte[] plainText = new byte[cDec.getOutputSize(cipherText.length)];

			int ctLength = cDec.update(cipherText, 0, cipherText.length, plainText, 0);
			ctLength += cDec.doFinal(plainText, ctLength);

			return new String(plainText, StandardCharsets.US_ASCII).trim();
		} else {
			System.out.println("File has been tampered with.");
			return "TAMPERED_FILE";
		}
	}

	/**
	 * Encode a String with Password-based Encryption with MD5 and DES.
	 *
	 * @param s
	 *            the String to encode
	 * @param file
	 *            the file
	 * @param password
	 *            the password
	 * @return the encoded String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String encodePBEWithMD5AndDES(String s, EncodedFile file, char[] password) throws Exception {
		byte[] input = s.getBytes(StandardCharsets.US_ASCII);
		Hashing hash = new Hashing();

		byte[] salt = generateSalt(8);
		file.setSalt(salt);
		int iterationCount = 2048;

		PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationCount);
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES", "BC");
		SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

		Cipher encryptionCipher = Cipher.getInstance("PBEWithMD5AndDES", "BC");
		encryptionCipher.init(Cipher.ENCRYPT_MODE, pbeKey);

		byte[] cipherText = encryptionCipher.doFinal(input);

		String cipherTextString = Base64.getEncoder().encodeToString(cipherText);
		if (file.getHashMode() != null) {
			file.setHashValue(hash.generateHash(file.getHashMode(), cipherTextString));
		}
		return cipherTextString;
	}

	/**
	 * Decode password based encryption with MD5 and DES.
	 *
	 * @param file
	 *            the file
	 * @param password
	 *            the password
	 * @return the decoded string
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String decodePBEWithMD5AndDES(EncodedFile file, char[] password) throws Exception {
		Hashing hash = new Hashing();
		if (file.getHashMode() != null
				&& hash.validateHash(file.getHashMode(), file.getCipherText(), file.getHashValue())) {
			byte[] cipherText = Base64.getDecoder().decode(file.getCipherText());

			byte[] salt = file.getSalt();
			int iterationCount = 2048;

			PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationCount);
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES", "BC");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			Cipher cDec = Cipher.getInstance("PBEWithMD5AndDES", "BC");
			cDec.init(Cipher.DECRYPT_MODE, pbeKey);
			byte[] plainText = new byte[cDec.getOutputSize(cipherText.length)];

			int ctLength = cDec.update(cipherText, 0, cipherText.length, plainText, 0);
			ctLength += cDec.doFinal(plainText, ctLength);

			return new String(plainText, StandardCharsets.US_ASCII).trim();
		} else {
			System.out.println("File has been tampered with.");
			return "TAMPERED_FILE";
		}
	}

	/**
	 * Encode a String with Password-based Encryption with SHA and 40Bit RC4
	 *
	 * @param s
	 *            the String to encode
	 * @param file
	 *            the file
	 * @param password
	 *            the password
	 * @return the encoded String
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String encodePBEWithSHAAnd40BitRC4(String s, EncodedFile file, char[] password) throws Exception {
		byte[] input = s.getBytes(StandardCharsets.US_ASCII);
		Hashing hash = new Hashing();

		byte[] salt = generateSalt(8);
		file.setSalt(salt);
		int iterationCount = 2048;

		PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationCount);
		SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
		SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

		Cipher encryptionCipher = Cipher.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
		encryptionCipher.init(Cipher.ENCRYPT_MODE, pbeKey);

		byte[] cipherText = encryptionCipher.doFinal(input);

		String cipherTextString = Base64.getEncoder().encodeToString(cipherText);
		if (file.getHashMode() != null) {
			file.setHashValue(hash.generateHash(file.getHashMode(), cipherTextString));
		}
		return cipherTextString;
	}

	/**
	 * Decode password based encryption with SHA and 40bit RC4.
	 *
	 * @param file
	 *            the file
	 * @param password
	 *            the password
	 * @return the decoded string
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchProviderException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public String decodePBEWithSHAAnd40BitRC4(EncodedFile file, char[] password) throws Exception {
		Hashing hash = new Hashing();
		if (file.getHashMode() != null
				&& hash.validateHash(file.getHashMode(), file.getCipherText(), file.getHashValue())) {
			byte[] cipherText = Base64.getDecoder().decode(file.getCipherText());

			byte[] salt = file.getSalt();
			int iterationCount = 2048;

			PBEKeySpec pbeKeySpec = new PBEKeySpec(password, salt, iterationCount);
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
			SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

			Cipher cDec = Cipher.getInstance("PBEWithSHAAnd128BitAES-CBC-BC", "BC");
			cDec.init(Cipher.DECRYPT_MODE, pbeKey);
			byte[] plainText = new byte[cDec.getOutputSize(cipherText.length)];

			int ctLength = cDec.update(cipherText, 0, cipherText.length, plainText, 0);
			ctLength += cDec.doFinal(plainText, ctLength);

			return new String(plainText, StandardCharsets.US_ASCII).trim();
		} else {
			System.out.println("File has been tampered with.");
			return "TAMPERED_FILE";
		}
	}

	/**
	 * Generate a random byte-Array.
	 *
	 * @param length
	 *            the desired length of the byte-Array
	 * @return the byte[]
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] generateSalt(int length) throws NoSuchAlgorithmException {
		byte salt[] = new byte[length];
		SecureRandom saltGen = new SecureRandom();
		saltGen.nextBytes(salt);
		return salt;
	}

}
