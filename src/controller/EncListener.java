package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import model.Cryptography;
import model.EncodedFile;
import model.IvGenerator;
import model.PBCryptography;
import model.Secret;

/**
 * Die ActionListener Klasse für Encrypt-Events. Wird beim Speichern von Dateien
 * verwendet, um den Plaintext in Ciphertext mit dem spezifizierten Algorithmus
 * zu übersetzen, und um zu gewährleisten dass die Datei alle benötigten
 * Parameter hat.
 *
 * @author Stefan Böhling
 */
public class EncListener implements ActionListener {

	/** The frame. */
	private JDialog dialog;

	/** The text area. */
	private JTextArea textArea;

	/** The file. */
	private EncodedFile file;

	/** The password. */
	private String pw;

	/**
	 * Instantiates a new enc listener.
	 *
	 * @param dialog
	 *            the frame
	 * @param textArea
	 *            the text area
	 * @param file
	 *            the file
	 * @param pw
	 *            the password
	 */
	public EncListener(JDialog dialog, JTextArea textArea, EncodedFile file, String pw) {
		this.textArea = textArea;
		this.dialog = dialog;
		this.file = file;
		this.pw = pw;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {

		Secret tempSecret = new Secret();
		IvGenerator ivGen = new IvGenerator();
		byte[] ivBytes = null;

		if (file.getCipher() == null) {
			file.setCipherText(textArea.getText());
		} else {
			try {
				switch (file.getCipher()) {
				case "AES":
					ivBytes = ivGen.generateIvBytes(16);
					file.setIvBytes(ivBytes);
					Cryptography aesC = new Cryptography();
					file.setCipherText(aesC.encryptAES(textArea.getText(), file));
					tempSecret = aesC.getSecret();
					break;
				case "DES":
					ivBytes = ivGen.generateIvBytes(8);
					file.setIvBytes(ivBytes);
					Cryptography desC = new Cryptography();
					file.setCipherText(desC.encryptDES(textArea.getText(), file));
					tempSecret = desC.getSecret();
					break;
				case "PBEWithSHAAnd128BitAES-CBC-BC":
					PBCryptography pbe1C = new PBCryptography();
					file.setCipherText(
							pbe1C.encodePBEWithSHAAnd128BitAES_CBC_BC(textArea.getText(), file, pw.toCharArray()));
					break;
				case "PBEWithMD5AndDES":
					PBCryptography pbe2C = new PBCryptography();
					file.setCipherText(pbe2C.encodePBEWithMD5AndDES(textArea.getText(), file, pw.toCharArray()));
					break;
				case "PBEWithSHAAnd40BitRC4":
					PBCryptography pbe3C = new PBCryptography();
					file.setCipherText(pbe3C.encodePBEWithSHAAnd40BitRC4(textArea.getText(), file, pw.toCharArray()));
					break;

				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		FileChooser f = new FileChooser();
		f.save(file, tempSecret);
		dialog.dispose();
	}
}