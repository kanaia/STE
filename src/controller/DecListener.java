package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JTextArea;

import model.Cryptography;
import model.EncodedFile;
import model.PBCryptography;
import model.Secret;

/**
 * Die ActionListener Klasse für Decrypt-Events. Wird beim Öffnen von Dateien
 * verwendet, um den enthaltenen Ciphertext zurück in Plaintext zu übersetzen
 * @author Stefan Böhling
 *
 */
public class DecListener implements ActionListener {

	/** The frame. */
	private JDialog dialog;

	/** The textarea. */
	private JTextArea textArea;

	/** The password. */
	private String pw;

	/**
	 * Instantiates a new dec listener.
	 *
	 * @param dialog
	 *            the frame
	 * @param textArea
	 *            the text area
	 * @param pw
	 *            the password
	 */
	public DecListener(JDialog dialog, JTextArea textArea, String pw) {
		this.dialog = dialog;
		this.textArea = textArea;
		this.pw = pw;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		FileChooser f = new FileChooser();
		dialog.dispose();

		EncodedFile file = f.openFile();
		Secret secret = null;
		if (!file.getCipher().contains("PBE")) {
			secret = f.openSecret();
		}

		if (file.getCipher().equals("AES")) {
			Cryptography c = new Cryptography();
			try {
				textArea.setText(c.decryptAES(file, secret));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (file.getCipher().equals("DES")) {
			Cryptography c = new Cryptography();
			try {
				textArea.setText(c.decryptDES(file, secret));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (file.getCipher().contains("PBE")) {
			PBCryptography c = new PBCryptography();
			if (file.getCipher().equals("PBEWithSHAAnd128BitAES-CBC-BC")) {
				try {
					textArea.setText(c.decodePBEWithSHAAnd128BitAES_CBC_BC(file, pw.toCharArray()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (file.getCipher().equals("PBEWithMD5AndDES")) {
				try {
					textArea.setText(c.decodePBEWithMD5AndDES(file, pw.toCharArray()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} else if (file.getCipher().equals("PBEWithSHAAnd40BitRC4")) {
				try {
					textArea.setText(c.decodePBEWithSHAAnd40BitRC4(file, pw.toCharArray()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}

	}

}
