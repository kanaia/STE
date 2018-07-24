package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

/**
 * Die Klasse Hashing, zum Generieren und Validieren von Hashwerten.
 * @author Stefan Böhling
 */
public class Hashing {

	/**
	 * Instantiates a new hashing generator.
	 */
	public Hashing() {

	}

	/**
	 * Generate hash.
	 *
	 * @param hashMode
	 *            the hash mode
	 * @param input
	 *            the input
	 * @return the hashed string
	 */
	public String generateHash(String hashMode, String input) {
		if (!hashMode.equals("")) {
			try {
				MessageDigest hash = MessageDigest.getInstance(hashMode, "BC");
				hash.update(Base64.getDecoder().decode(input));
				return new String(hash.digest(), StandardCharsets.US_ASCII).trim();
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	/**
	 * Validate hash.
	 *
	 * @param hashMode
	 *            the hash mode
	 * @param input
	 *            the input
	 * @param hashToValidate
	 *            the hash to validate
	 * @return true, if the hashed input is equal to the hash to validate
	 */
	public boolean validateHash(String hashMode, String input, String hashToValidate) {
		if (!hashMode.equals("")) {
			try {
				MessageDigest hash = MessageDigest.getInstance(hashMode, "BC");
				hash.update(Base64.getDecoder().decode(input));
				String newHash = new String(hash.digest(), StandardCharsets.US_ASCII).trim();
				return newHash.equals(hashToValidate);
			} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
