package UnitTesting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import model.EncodedFile;
import model.PBCryptography;

/**
 * The Class PBETests.
 * @author Stefan Böhling
 */
@RunWith(Parameterized.class)
public class PBETests {

	/** The cipher. */
	public String cipher;
	
	/** The hashing. */
	public String hashing;

	/**
	 * Instantiates a new PBE tests.
	 *
	 * @param cipher the cipher
	 * @param hashing the hashing
	 */
	public PBETests(String cipher, String hashing) {
		this.cipher = cipher;
		this.hashing = hashing;
	}
	
	/**
	 * Data.
	 *
	 * @return the collection
	 */
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { {"PBEWithSHA256And128BitAES-CBC-BC", "SHA-1"}, {"PBEWithMD5AndDES", "SHA-1"}, {"PBEWithSHAAnd40BitRC4", "SHA-1"},
			{"PBEWithSHA256And128BitAES-CBC-BC", "MD5"}, {"PBEWithMD5AndDES", "MD5"}, {"PBEWithSHAAnd40BitRC4", "MD5"},
			{"PBEWithSHA256And128BitAES-CBC-BC", "SHA-224"}, {"PBEWithMD5AndDES", "SHA-224"}, {"PBEWithSHAAnd40BitRC4", "SHA-224"},
			{"PBEWithSHA256And128BitAES-CBC-BC", "SHA-256"}, {"PBEWithMD5AndDES", "SHA-256"}, {"PBEWithSHAAnd40BitRC4", "SHA-256"},
			{"PBEWithSHA256And128BitAES-CBC-BC", "SHA-384"}, {"PBEWithMD5AndDES", "SHA-384"}, {"PBEWithSHAAnd40BitRC4", "SHA-384"},
			{"PBEWithSHA256And128BitAES-CBC-BC", "SHA-512"}, {"PBEWithMD5AndDES", "SHA-512"}, {"PBEWithSHAAnd40BitRC4", "SHA-512"},};
		return Arrays.asList(data);
	}

	/**
	 * PBE Test.
	 */
	@Test
	public void PBEtest() {
		String plainText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
		EncodedFile file = new EncodedFile();
		file.setHashMode(hashing);
		String pw = "Diesisteinpasswort";
		char[] password = pw.toCharArray();
		PBCryptography pbc = new PBCryptography();
		if(cipher.equals("PBEWithSHA256And128BitAES-CBC-BC")){
			try {
				file.setCipherText(pbc.encodePBEWithSHAAnd128BitAES_CBC_BC(plainText, file, password));
				assertEquals(plainText, pbc.decodePBEWithSHAAnd128BitAES_CBC_BC(file, password));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(cipher.equals("PBEWithMD5AndDES")){
			try {
				file.setCipherText(pbc.encodePBEWithMD5AndDES(plainText, file, password));
				assertEquals(plainText, pbc.decodePBEWithMD5AndDES(file, password));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(cipher.equals("PBEWithSHAAnd40BitRC4")){
			try {
				file.setCipherText(pbc.encodePBEWithSHAAnd40BitRC4(plainText, file, password));
				assertEquals(plainText, pbc.decodePBEWithSHAAnd40BitRC4(file, password));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
