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
 * The Class DESTests.
 * @author Stefan Böhling
 */
@RunWith(Parameterized.class)
public class DESTests {
	
	/** The cipher mode. */
	public String cipherMode;
	
	/** The padding. */
	public String padding;
	
	/** The hashing. */
	public String hashing;

	/**
	 * Instantiates a new DES tests.
	 *
	 * @param cipherMode the cipher mode
	 * @param padding the padding
	 * @param hashing the hashing
	 */
	public DESTests(String cipherMode, String padding, String hashing) {
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
		Object[][] data = new Object[][] { { "ECB", "PKCS7Padding", "SHA-1" }, { "CBC", "PKCS7Padding", "SHA-1" },
				{ "OFB", "PKCS7Padding", "SHA-1" }, { "CTS", "PKCS7Padding", "SHA-1" },
				{ "ECB", "ZeroBytePadding", "SHA-1" }, { "CBC", "ZeroBytePadding", "SHA-1" },
				{ "OFB", "ZeroBytePadding", "SHA-1" }, { "CTS", "ZeroBytePadding", "SHA-1" },
				{ "ECB", "NoPadding", "SHA-1" }, { "CBC", "NoPadding", "SHA-1" }, { "OFB", "NoPadding", "SHA-1" },
				{ "CTS", "NoPadding", "SHA-1" },

				{ "ECB", "PKCS7Padding", "MD5" }, { "CBC", "PKCS7Padding", "MD5" }, { "OFB", "PKCS7Padding", "MD5" },
				{ "CTS", "PKCS7Padding", "MD5" }, { "ECB", "ZeroBytePadding", "MD5" },
				{ "CBC", "ZeroBytePadding", "MD5" }, { "OFB", "ZeroBytePadding", "MD5" },
				{ "CTS", "ZeroBytePadding", "MD5" }, { "ECB", "NoPadding", "MD5" }, { "CBC", "NoPadding", "MD5" },
				{ "OFB", "NoPadding", "MD5" }, { "CTS", "NoPadding", "MD5" },

				{ "ECB", "PKCS7Padding", "SHA-224" }, { "CBC", "PKCS7Padding", "SHA-224" },
				{ "OFB", "PKCS7Padding", "SHA-224" }, { "CTS", "PKCS7Padding", "SHA-224" },
				{ "ECB", "ZeroBytePadding", "SHA-224" }, { "CBC", "ZeroBytePadding", "SHA-224" },
				{ "OFB", "ZeroBytePadding", "SHA-224" }, { "CTS", "ZeroBytePadding", "SHA-224" },
				{ "ECB", "NoPadding", "SHA-224" }, { "CBC", "NoPadding", "SHA-224" }, { "OFB", "NoPadding", "SHA-224" },
				{ "CTS", "NoPadding", "SHA-224" },

				{ "ECB", "PKCS7Padding", "SHA-256" }, { "CBC", "PKCS7Padding", "SHA-256" },
				{ "OFB", "PKCS7Padding", "SHA-256" }, { "CTS", "PKCS7Padding", "SHA-256" },
				{ "ECB", "ZeroBytePadding", "SHA-256" }, { "CBC", "ZeroBytePadding", "SHA-256" },
				{ "OFB", "ZeroBytePadding", "SHA-256" }, { "CTS", "ZeroBytePadding", "SHA-256" },
				{ "ECB", "NoPadding", "SHA-256" }, { "CBC", "NoPadding", "SHA-256" }, { "OFB", "NoPadding", "SHA-256" },
				{ "CTS", "NoPadding", "SHA-256" },

				{ "ECB", "PKCS7Padding", "SHA-384" }, { "CBC", "PKCS7Padding", "SHA-384" },
				{ "OFB", "PKCS7Padding", "SHA-384" }, { "CTS", "PKCS7Padding", "SHA-384" },
				{ "ECB", "ZeroBytePadding", "SHA-384" }, { "CBC", "ZeroBytePadding", "SHA-384" },
				{ "OFB", "ZeroBytePadding", "SHA-384" }, { "CTS", "ZeroBytePadding", "SHA-384" },
				{ "ECB", "NoPadding", "SHA-384" }, { "CBC", "NoPadding", "SHA-384" }, { "OFB", "NoPadding", "SHA-384" },
				{ "CTS", "NoPadding", "SHA-384" },

				{ "ECB", "PKCS7Padding", "SHA-512" }, { "CBC", "PKCS7Padding", "SHA-512" },
				{ "OFB", "PKCS7Padding", "SHA-512" }, { "CTS", "PKCS7Padding", "SHA-512" },
				{ "ECB", "ZeroBytePadding", "SHA-512" }, { "CBC", "ZeroBytePadding", "SHA-512" },
				{ "OFB", "ZeroBytePadding", "SHA-512" }, { "CTS", "ZeroBytePadding", "SHA-512" },
				{ "ECB", "NoPadding", "SHA-512" }, { "CBC", "NoPadding", "SHA-512" }, { "OFB", "NoPadding", "SHA-512" },
				{ "CTS", "NoPadding", "SHA-512" }

		};
		return Arrays.asList(data);
	}

	/**
	 * Test DES.
	 */
	@Test
	public void testDES() {
		IvGenerator ivGen = new IvGenerator();
		byte[] ivBytesAES = ivGen.generateIvBytes(8);
		String plainText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		EncodedFile file = new EncodedFile();
		file.setBlockMode(cipherMode);
		file.setPadding(padding);
		file.setHashMode(hashing);
		file.setIvBytes(ivBytesAES);
		if (file.getPadding().equals("NoPadding")) {
			plainText = plainText.substring(0, 16);
		}
		try {
			Cryptography c = new Cryptography();
			String encrypted = c.encryptDES(plainText, file);
			file.setCipherText(encrypted);
			System.out
					.println("DES/" + cipherMode + "/" + padding + "/" + hashing + "\nCipherText: " + encrypted + "\n");
			assertEquals(plainText, c.decryptDES(file, c.getSecret()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
