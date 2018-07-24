package model;

/**
 * Die Klasse EncodedFile, eine Java Bean in der alle benötigten Einstellungen
 * wie z.B. Blockmode oder Padding hinterlegt werden können. Primäres Format zum
 * Abspeichern einer Datei.
 * @author Stefan Böhling
 */
public class EncodedFile {

	/** The cipher text. */
	private String cipherText;

	/** The cipher. */
	private String cipher;

	/** The hash value. */
	private String hashValue;

	/** The hash mode. */
	private String hashMode;

	/** The key length. */
	private int keyLength;

	/** The padding. */
	private String padding;

	/** The block mode. */
	private String blockMode;

	/** The iv bytes. */
	private byte[] ivBytes;

	/** The salt. */
	private byte[] salt;

	/**
	 * Instantiates a new encoded file.
	 *
	 * @param cipherText
	 *            the cipher text
	 * @param cipher
	 *            the cipher
	 * @param hashValue
	 *            the hash value
	 * @param hashMode
	 *            the hash mode
	 * @param keyLength
	 *            the key length
	 * @param padding
	 *            the padding
	 * @param blockMode
	 *            the block mode
	 * @param ivBytes
	 *            the iv bytes
	 * @param salt
	 *            the salt
	 */
	public EncodedFile(String cipherText, String cipher, String hashValue, String hashMode, int keyLength,
			String padding, String blockMode, byte[] ivBytes, byte[] salt) {
		this.cipherText = cipherText;
		this.cipher = cipher;
		this.hashValue = hashValue;
		this.hashMode = hashMode;
		this.keyLength = keyLength;
		this.padding = padding;
		this.blockMode = blockMode;
		this.setIvBytes(ivBytes);
		this.setSalt(salt);
	}

	/**
	 * Instantiates a new encoded file.
	 */
	public EncodedFile() {

	}

	/**
	 * Gets the cipher text.
	 *
	 * @return the cipher text
	 */
	public String getCipherText() {
		return cipherText;
	}

	/**
	 * Sets the cipher text.
	 *
	 * @param cipherText
	 *            the new cipher text
	 */
	public void setCipherText(String cipherText) {
		this.cipherText = cipherText;
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
	 * Gets the hash value.
	 *
	 * @return the hash value
	 */
	public String getHashValue() {
		return hashValue;
	}

	/**
	 * Sets the hash value.
	 *
	 * @param hashValue
	 *            the new hash value
	 */
	public void setHashValue(String hashValue) {
		this.hashValue = hashValue;
	}

	/**
	 * Gets the hash mode.
	 *
	 * @return the hash mode
	 */
	public String getHashMode() {
		return hashMode;
	}

	/**
	 * Sets the hash mode.
	 *
	 * @param hashMode
	 *            the new hash mode
	 */
	public void setHashMode(String hashMode) {
		this.hashMode = hashMode;
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
	 * Gets the padding.
	 *
	 * @return the padding
	 */
	public String getPadding() {
		return padding;
	}

	/**
	 * Sets the padding.
	 *
	 * @param padding
	 *            the new padding
	 */
	public void setPadding(String padding) {
		this.padding = padding;
	}

	/**
	 * Gets the block mode.
	 *
	 * @return the block mode
	 */
	public String getBlockMode() {
		return blockMode;
	}

	/**
	 * Sets the block mode.
	 *
	 * @param blockMode
	 *            the new block mode
	 */
	public void setBlockMode(String blockMode) {
		this.blockMode = blockMode;
	}

	/**
	 * Gets the iv bytes.
	 *
	 * @return the iv bytes
	 */
	public byte[] getIvBytes() {
		return ivBytes;
	}

	/**
	 * Sets the iv bytes.
	 *
	 * @param ivBytes
	 *            the new iv bytes
	 */
	public void setIvBytes(byte[] ivBytes) {
		this.ivBytes = ivBytes;
	}

	/**
	 * Gets the salt.
	 *
	 * @return the salt
	 */
	public byte[] getSalt() {
		return salt;
	}

	/**
	 * Sets the salt.
	 *
	 * @param salt
	 *            the new salt
	 */
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}

}
