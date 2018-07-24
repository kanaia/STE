package model;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.KeyGenerator;

/**
 * Die Klasse Secret, zum Generieren und Verwalten von nicht zugänglichen
 * Inhalten. Sekundäres Format zum Abspeichern einer Datei.
 * @author Stefan Böhling
 */
public class Secret {

	/** The key. */
	private Key key;

	/** The cipher. */
	private String cipher;

	/** The key length. */
	private int keyLength;

	/** The key bytes. */
	private byte[] keyBytes;

	/**
	 * Instantiates a new secret.
	 *
	 * @param cipher
	 *            the method of cipher to be used in generating the key
	 * @param keyLength
	 *            the key length
	 */
	public Secret(String cipher, int keyLength) {
		this.cipher = cipher;
		this.keyLength = keyLength;
		try {
			this.key = generateKey(cipher, keyLength);
		} catch (NoSuchAlgorithmException | NoSuchProviderException e) {
			System.out.println("Error generating Key");
		}
		this.keyBytes = this.key.getEncoded();
	}

	/**
	 * Instantiates a new secret.
	 */
	public Secret() {

	}

	/**
	 * Generate key.
	 *
	 * @param c
	 *            the method of cipher to be used in generating the key
	 * @param kL
	 *            the key length
	 * @return the key
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 */
	public Key generateKey(String c, int kL) throws NoSuchAlgorithmException, NoSuchProviderException {
		KeyGenerator keyGen = KeyGenerator.getInstance(c, "BC");
		keyGen.init(kL);
		key = keyGen.generateKey();
		return key;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * Gets the cipher.
	 *
	 * @return the cipher
	 */
	public String getCipher() {
		return cipher;
	}

	/**
	 * Sets the cipher.
	 *
	 * @param cipher
	 *            the new cipher
	 */
	public void setCipher(String cipher) {
		this.cipher = cipher;
	}

	/**
	 * Gets the key length.
	 *
	 * @return the key length
	 */
	public int getKeyLength() {
		return keyLength;
	}

	/**
	 * Sets the key length.
	 *
	 * @param keyLength
	 *            the new key length
	 */
	public void setKeyLength(int keyLength) {
		this.keyLength = keyLength;
	}

	/**
	 * Gets the key bytes.
	 *
	 * @return the key bytes
	 */
	public byte[] getKeyBytes() {
		return keyBytes;
	}

	/**
	 * Sets the key bytes.
	 *
	 * @param keyBytes
	 *            the new key bytes
	 */
	public void setKeyBytes(byte[] keyBytes) {
		this.keyBytes = keyBytes;
	}

}
