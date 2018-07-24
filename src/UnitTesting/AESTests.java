package UnitTesting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.Cryptography;
import model.EncodedFile;
import model.IvGenerator;

/**
 * The Class AESTests.
 * @author Stefan Böhling
 */
@RunWith(Parameterized.class)
public class AESTests {

	/** The key length. */
	public int keyLength;
	
	/** The cipher mode. */
	public String cipherMode;
	
	/** The padding. */
	public String padding;
	
	/** The hashing. */
	public String hashing;

	/**
	 * Instantiates a new AES tests.
	 *
	 * @param keyLength the key length
	 * @param cipherMode the cipher mode
	 * @param padding the padding
	 * @param hashing the hashing
	 */
	public AESTests(int keyLength, String cipherMode, String padding, String hashing) {
		this.keyLength = keyLength;
		this.cipherMode = cipherMode;
		this.padding = padding;
		this.hashing = hashing;
	}

	/**
	 * Data.
	 *
	 * @return the collection
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { 128, "ECB", "PKCS7Padding", "SHA-1" },
				{ 128, "CBC", "PKCS7Padding", "SHA-1" }, { 128, "OFB", "PKCS7Padding", "SHA-1" },
				{ 128, "CTS", "PKCS7Padding", "SHA-1" }, { 128, "ECB", "ZeroBytePadding", "SHA-1" },
				{ 128, "CBC", "ZeroBytePadding", "SHA-1" }, { 128, "OFB", "ZeroBytePadding", "SHA-1" },
				{ 128, "CTS", "ZeroBytePadding", "SHA-1" }, { 128, "ECB", "NoPadding", "SHA-1" },
				{ 128, "CBC", "NoPadding", "SHA-1" }, { 128, "OFB", "NoPadding", "SHA-1" },
				{ 128, "CTS", "NoPadding", "SHA-1" }, { 128, "GCM", "NoPadding", "SHA-1" },

				{ 192, "ECB", "PKCS7Padding", "SHA-1" }, { 192, "CBC", "PKCS7Padding", "SHA-1" },
				{ 192, "OFB", "PKCS7Padding", "SHA-1" }, { 192, "CTS", "PKCS7Padding", "SHA-1" },
				{ 192, "ECB", "ZeroBytePadding", "SHA-1" }, { 192, "CBC", "ZeroBytePadding", "SHA-1" },
				{ 192, "OFB", "ZeroBytePadding", "SHA-1" }, { 192, "CTS", "ZeroBytePadding", "SHA-1" },
				{ 192, "ECB", "NoPadding", "SHA-1" }, { 192, "CBC", "NoPadding", "SHA-1" },
				{ 192, "OFB", "NoPadding", "SHA-1" }, { 192, "CTS", "NoPadding", "SHA-1" },
				{ 192, "GCM", "NoPadding", "SHA-1" },

				{ 256, "ECB", "PKCS7Padding", "SHA-1" }, { 256, "CBC", "PKCS7Padding", "SHA-1" },
				{ 256, "OFB", "PKCS7Padding", "SHA-1" }, { 256, "CTS", "PKCS7Padding", "SHA-1" },
				{ 256, "ECB", "ZeroBytePadding", "SHA-1" }, { 256, "CBC", "ZeroBytePadding", "SHA-1" },
				{ 256, "OFB", "ZeroBytePadding", "SHA-1" }, { 256, "CTS", "ZeroBytePadding", "SHA-1" },
				{ 256, "ECB", "NoPadding", "SHA-1" }, { 256, "CBC", "NoPadding", "SHA-1" },
				{ 256, "OFB", "NoPadding", "SHA-1" }, { 256, "CTS", "NoPadding", "SHA-1" },
				{ 256, "GCM", "NoPadding", "SHA-1" },

				{ 128, "ECB", "PKCS7Padding", "MD5" }, { 128, "CBC", "PKCS7Padding", "MD5" },
				{ 128, "OFB", "PKCS7Padding", "MD5" }, { 128, "CTS", "PKCS7Padding", "MD5" },
				{ 128, "ECB", "ZeroBytePadding", "MD5" }, { 128, "CBC", "ZeroBytePadding", "MD5" },
				{ 128, "OFB", "ZeroBytePadding", "MD5" }, { 128, "CTS", "ZeroBytePadding", "MD5" },
				{ 128, "ECB", "NoPadding", "MD5" }, { 128, "CBC", "NoPadding", "MD5" },
				{ 128, "OFB", "NoPadding", "MD5" }, { 128, "CTS", "NoPadding", "MD5" },
				{ 128, "GCM", "NoPadding", "MD5" },

				{ 192, "ECB", "PKCS7Padding", "MD5" }, { 192, "CBC", "PKCS7Padding", "MD5" },
				{ 192, "OFB", "PKCS7Padding", "MD5" }, { 192, "CTS", "PKCS7Padding", "MD5" },
				{ 192, "ECB", "ZeroBytePadding", "MD5" }, { 192, "CBC", "ZeroBytePadding", "MD5" },
				{ 192, "OFB", "ZeroBytePadding", "MD5" }, { 192, "CTS", "ZeroBytePadding", "MD5" },
				{ 192, "ECB", "NoPadding", "MD5" }, { 192, "CBC", "NoPadding", "MD5" },
				{ 192, "OFB", "NoPadding", "MD5" }, { 192, "CTS", "NoPadding", "MD5" },
				{ 192, "GCM", "NoPadding", "MD5" },

				{ 256, "ECB", "PKCS7Padding", "MD5" }, { 256, "CBC", "PKCS7Padding", "MD5" },
				{ 256, "OFB", "PKCS7Padding", "MD5" }, { 256, "CTS", "PKCS7Padding", "MD5" },
				{ 256, "ECB", "ZeroBytePadding", "MD5" }, { 256, "CBC", "ZeroBytePadding", "MD5" },
				{ 256, "OFB", "ZeroBytePadding", "MD5" }, { 256, "CTS", "ZeroBytePadding", "MD5" },
				{ 256, "ECB", "NoPadding", "MD5" }, { 256, "CBC", "NoPadding", "MD5" },
				{ 256, "OFB", "NoPadding", "MD5" }, { 256, "CTS", "NoPadding", "MD5" },
				{ 256, "GCM", "NoPadding", "MD5" },

				{ 128, "ECB", "PKCS7Padding", "SHA-224" }, { 128, "CBC", "PKCS7Padding", "SHA-224" },
				{ 128, "OFB", "PKCS7Padding", "SHA-224" }, { 128, "CTS", "PKCS7Padding", "SHA-224" },
				{ 128, "ECB", "ZeroBytePadding", "SHA-224" }, { 128, "CBC", "ZeroBytePadding", "SHA-224" },
				{ 128, "OFB", "ZeroBytePadding", "SHA-224" }, { 128, "CTS", "ZeroBytePadding", "SHA-224" },
				{ 128, "ECB", "NoPadding", "SHA-224" }, { 128, "CBC", "NoPadding", "SHA-224" },
				{ 128, "OFB", "NoPadding", "SHA-224" }, { 128, "CTS", "NoPadding", "SHA-224" },
				{ 128, "GCM", "NoPadding", "SHA-224" },

				{ 192, "ECB", "PKCS7Padding", "SHA-224" }, { 192, "CBC", "PKCS7Padding", "SHA-224" },
				{ 192, "OFB", "PKCS7Padding", "SHA-224" }, { 192, "CTS", "PKCS7Padding", "SHA-224" },
				{ 192, "ECB", "ZeroBytePadding", "SHA-224" }, { 192, "CBC", "ZeroBytePadding", "SHA-224" },
				{ 192, "OFB", "ZeroBytePadding", "SHA-224" }, { 192, "CTS", "ZeroBytePadding", "SHA-224" },
				{ 192, "ECB", "NoPadding", "SHA-224" }, { 192, "CBC", "NoPadding", "SHA-224" },
				{ 192, "OFB", "NoPadding", "SHA-224" }, { 192, "CTS", "NoPadding", "SHA-224" },
				{ 192, "GCM", "NoPadding", "SHA-224" },

				{ 256, "ECB", "PKCS7Padding", "SHA-224" }, { 256, "CBC", "PKCS7Padding", "SHA-224" },
				{ 256, "OFB", "PKCS7Padding", "SHA-224" }, { 256, "CTS", "PKCS7Padding", "SHA-224" },
				{ 256, "ECB", "ZeroBytePadding", "SHA-224" }, { 256, "CBC", "ZeroBytePadding", "SHA-224" },
				{ 256, "OFB", "ZeroBytePadding", "SHA-224" }, { 256, "CTS", "ZeroBytePadding", "SHA-224" },
				{ 256, "ECB", "NoPadding", "SHA-224" }, { 256, "CBC", "NoPadding", "SHA-224" },
				{ 256, "OFB", "NoPadding", "SHA-224" }, { 256, "CTS", "NoPadding", "SHA-224" },
				{ 256, "GCM", "NoPadding", "SHA-224" },

				{ 128, "ECB", "PKCS7Padding", "SHA-256" }, { 128, "CBC", "PKCS7Padding", "SHA-256" },
				{ 128, "OFB", "PKCS7Padding", "SHA-256" }, { 128, "CTS", "PKCS7Padding", "SHA-256" },
				{ 128, "ECB", "ZeroBytePadding", "SHA-256" }, { 128, "CBC", "ZeroBytePadding", "SHA-256" },
				{ 128, "OFB", "ZeroBytePadding", "SHA-256" }, { 128, "CTS", "ZeroBytePadding", "SHA-256" },
				{ 128, "ECB", "NoPadding", "SHA-256" }, { 128, "CBC", "NoPadding", "SHA-256" },
				{ 128, "OFB", "NoPadding", "SHA-256" }, { 128, "CTS", "NoPadding", "SHA-256" },
				{ 128, "GCM", "NoPadding", "SHA-256" },

				{ 192, "ECB", "PKCS7Padding", "SHA-256" }, { 192, "CBC", "PKCS7Padding", "SHA-256" },
				{ 192, "OFB", "PKCS7Padding", "SHA-256" }, { 192, "CTS", "PKCS7Padding", "SHA-256" },
				{ 192, "ECB", "ZeroBytePadding", "SHA-256" }, { 192, "CBC", "ZeroBytePadding", "SHA-256" },
				{ 192, "OFB", "ZeroBytePadding", "SHA-256" }, { 192, "CTS", "ZeroBytePadding", "SHA-256" },
				{ 192, "ECB", "NoPadding", "SHA-256" }, { 192, "CBC", "NoPadding", "SHA-256" },
				{ 192, "OFB", "NoPadding", "SHA-256" }, { 192, "CTS", "NoPadding", "SHA-256" },
				{ 192, "GCM", "NoPadding", "SHA-256" },

				{ 256, "ECB", "PKCS7Padding", "SHA-256" }, { 256, "CBC", "PKCS7Padding", "SHA-256" },
				{ 256, "OFB", "PKCS7Padding", "SHA-256" }, { 256, "CTS", "PKCS7Padding", "SHA-256" },
				{ 256, "ECB", "ZeroBytePadding", "SHA-256" }, { 256, "CBC", "ZeroBytePadding", "SHA-256" },
				{ 256, "OFB", "ZeroBytePadding", "SHA-256" }, { 256, "CTS", "ZeroBytePadding", "SHA-256" },
				{ 256, "ECB", "NoPadding", "SHA-256" }, { 256, "CBC", "NoPadding", "SHA-256" },
				{ 256, "OFB", "NoPadding", "SHA-256" }, { 256, "CTS", "NoPadding", "SHA-256" },
				{ 256, "GCM", "NoPadding", "SHA-256" },

				{ 128, "ECB", "PKCS7Padding", "SHA-384" }, { 128, "CBC", "PKCS7Padding", "SHA-384" },
				{ 128, "OFB", "PKCS7Padding", "SHA-384" }, { 128, "CTS", "PKCS7Padding", "SHA-384" },
				{ 128, "ECB", "ZeroBytePadding", "SHA-384" }, { 128, "CBC", "ZeroBytePadding", "SHA-384" },
				{ 128, "OFB", "ZeroBytePadding", "SHA-384" }, { 128, "CTS", "ZeroBytePadding", "SHA-384" },
				{ 128, "ECB", "NoPadding", "SHA-384" }, { 128, "CBC", "NoPadding", "SHA-384" },
				{ 128, "OFB", "NoPadding", "SHA-384" }, { 128, "CTS", "NoPadding", "SHA-384" },
				{ 128, "GCM", "NoPadding", "SHA-384" },

				{ 192, "ECB", "PKCS7Padding", "SHA-384" }, { 192, "CBC", "PKCS7Padding", "SHA-384" },
				{ 192, "OFB", "PKCS7Padding", "SHA-384" }, { 192, "CTS", "PKCS7Padding", "SHA-384" },
				{ 192, "ECB", "ZeroBytePadding", "SHA-384" }, { 192, "CBC", "ZeroBytePadding", "SHA-384" },
				{ 192, "OFB", "ZeroBytePadding", "SHA-384" }, { 192, "CTS", "ZeroBytePadding", "SHA-384" },
				{ 192, "ECB", "NoPadding", "SHA-384" }, { 192, "CBC", "NoPadding", "SHA-384" },
				{ 192, "OFB", "NoPadding", "SHA-384" }, { 192, "CTS", "NoPadding", "SHA-384" },
				{ 192, "GCM", "NoPadding", "SHA-384" },

				{ 256, "ECB", "PKCS7Padding", "SHA-384" }, { 256, "CBC", "PKCS7Padding", "SHA-384" },
				{ 256, "OFB", "PKCS7Padding", "SHA-384" }, { 256, "CTS", "PKCS7Padding", "SHA-384" },
				{ 256, "ECB", "ZeroBytePadding", "SHA-384" }, { 256, "CBC", "ZeroBytePadding", "SHA-384" },
				{ 256, "OFB", "ZeroBytePadding", "SHA-384" }, { 256, "CTS", "ZeroBytePadding", "SHA-384" },
				{ 256, "ECB", "NoPadding", "SHA-384" }, { 256, "CBC", "NoPadding", "SHA-384" },
				{ 256, "OFB", "NoPadding", "SHA-384" }, { 256, "CTS", "NoPadding", "SHA-384" },
				{ 256, "GCM", "NoPadding", "SHA-384" },

				{ 128, "ECB", "PKCS7Padding", "SHA-512" }, { 128, "CBC", "PKCS7Padding", "SHA-512" },
				{ 128, "OFB", "PKCS7Padding", "SHA-512" }, { 128, "CTS", "PKCS7Padding", "SHA-512" },
				{ 128, "ECB", "ZeroBytePadding", "SHA-512" }, { 128, "CBC", "ZeroBytePadding", "SHA-512" },
				{ 128, "OFB", "ZeroBytePadding", "SHA-512" }, { 128, "CTS", "ZeroBytePadding", "SHA-512" },
				{ 128, "ECB", "NoPadding", "SHA-512" }, { 128, "CBC", "NoPadding", "SHA-512" },
				{ 128, "OFB", "NoPadding", "SHA-512" }, { 128, "CTS", "NoPadding", "SHA-512" },
				{ 128, "GCM", "NoPadding", "SHA-512" },

				{ 192, "ECB", "PKCS7Padding", "SHA-512" }, { 192, "CBC", "PKCS7Padding", "SHA-512" },
				{ 192, "OFB", "PKCS7Padding", "SHA-512" }, { 192, "CTS", "PKCS7Padding", "SHA-512" },
				{ 192, "ECB", "ZeroBytePadding", "SHA-512" }, { 192, "CBC", "ZeroBytePadding", "SHA-512" },
				{ 192, "OFB", "ZeroBytePadding", "SHA-512" }, { 192, "CTS", "ZeroBytePadding", "SHA-512" },
				{ 192, "ECB", "NoPadding", "SHA-512" }, { 192, "CBC", "NoPadding", "SHA-512" },
				{ 192, "OFB", "NoPadding", "SHA-512" }, { 192, "CTS", "NoPadding", "SHA-512" },
				{ 192, "GCM", "NoPadding", "SHA-512" },

				{ 256, "ECB", "PKCS7Padding", "SHA-512" }, { 256, "CBC", "PKCS7Padding", "SHA-512" },
				{ 256, "OFB", "PKCS7Padding", "SHA-512" }, { 256, "CTS", "PKCS7Padding", "SHA-512" },
				{ 256, "ECB", "ZeroBytePadding", "SHA-512" }, { 256, "CBC", "ZeroBytePadding", "SHA-512" },
				{ 256, "OFB", "ZeroBytePadding", "SHA-512" }, { 256, "CTS", "ZeroBytePadding", "SHA-512" },
				{ 256, "ECB", "NoPadding", "SHA-512" }, { 256, "CBC", "NoPadding", "SHA-512" },
				{ 256, "OFB", "NoPadding", "SHA-512" }, { 256, "CTS", "NoPadding", "SHA-512" },
				{ 256, "GCM", "NoPadding", "SHA-512" }

		};
		return Arrays.asList(data);
	}

	/**
	 * Test AES.
	 */
	@Test
	public void testAES() {
		IvGenerator ivGen = new IvGenerator();
		byte[] ivBytesAES = ivGen.generateIvBytes(16);
		String plainText = "Alle meine Entchen schwimmen auf dem See, Koepfchen in das Wasser, Schwaenzchen in die Hoeh'";
		EncodedFile file = new EncodedFile();
		file.setKeyLength(keyLength);
		file.setBlockMode(cipherMode);
		file.setPadding(padding);
		file.setHashMode(hashing);
		file.setIvBytes(ivBytesAES);
		if(file.getPadding().equals("NoPadding")){
			plainText=plainText.substring(0, 16);
		}
		
		try {
			Cryptography c = new Cryptography();
			String encrypted = c.encryptAES(plainText, file);
			file.setCipherText(encrypted);
			System.out
			.println("AES/" + keyLength + "/" + cipherMode + "/" + padding + "/" + hashing + "\nCipherText: " + encrypted + "\n");
			assertEquals(plainText, c.decryptAES(file, c.getSecret()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
