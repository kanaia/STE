package view;

import javax.swing.*;

import controller.DecListener;
import controller.EncListener;
import model.EncodedFile;

import java.awt.GridLayout;
import java.awt.event.*;

/**
 * Die Klasse Editor, Präsentationsschicht und User-Schnittstelle.
 * 
 * @author Stefan Böhling
 */
public class Editor {

	/** The text area. */
	private JTextArea textArea = new JTextArea();

	/** The ste. */
	private JFrame ste = new JFrame("Secure Text Editor");

	/** The dialog. */
	private JDialog dialog;

	/**
	 * Inits the frame.
	 */
	public void initFrame() {
		ste.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ste.setResizable(true);
		ste.setVisible(true);
		ste.setLayout(null);
		ste.setSize(600, 400);

		ste.add(textArea);
		textArea.setLocation(0, 30);
		textArea.setSize(600, 370);

		addButtons();

	}

	/**
	 * Adds the buttons.
	 */
	public void addButtons() {
		JButton open = new JButton("Open");
		open.addActionListener(new OpenL());
		ste.add(open);
		open.setLocation(0, 0);
		open.setSize(70, 30);

		JButton save = new JButton("Save");
		save.addActionListener(new SaveL());
		ste.add(save);
		save.setLocation(70, 0);
		save.setSize(70, 30);
	}

	/**
	 * The Class OpenL.
	 */
	class OpenL implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			dialog = new JDialog();
			dialog.setTitle("Encryption");
			dialog.setSize(600, 50);
			dialog.setModal(true);
			dialog.setResizable(false);
			dialog.setLayout(new GridLayout(1, 5));

			JTextField pw = new JTextField("");
			dialog.add(pw);

			JButton go = new JButton("Go!");
			dialog.add(go);

			go.addActionListener(new DecListener(dialog, textArea, pw.getText()));

			dialog.setVisible(true);

		}
	}

	/**
	 * The Class SaveL.
	 */
	class SaveL implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.
		 * ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			dialog = new JDialog();
			dialog.setTitle("Encryption");
			dialog.setSize(800, 70);
			dialog.setModal(true);
			dialog.setResizable(false);
			dialog.setLayout(new GridLayout(1, 6));
			// encrypt
			JPanel encryptPanel = new JPanel();
			JLabel encrypt = new JLabel("Encrypt with:");
			encryptPanel.add(encrypt);

			EncodedFile file = new EncodedFile();

			String[] cipherList = { "", "AES", "DES", "PBEWithSHAAnd128BitAES-CBC-BC", "PBEWithMD5AndDES",
					"PBEWithSHAAnd40BitRC4" };
			String[] keyLengthList = { "" };
			String[] cipherModeList = { "" };
			String[] paddingList = { "" };
			String[] hashingList = { "", "MD5", "SHA-1", "SHA-224", "SHA-256", "SHA-384", "SHA-512" };

			JComboBox<String> cipher = new JComboBox<String>(cipherList);
			JComboBox<String> keyLength = new JComboBox<String>(keyLengthList);
			JComboBox<String> cipherMode = new JComboBox<String>(cipherModeList);
			JComboBox<String> padding = new JComboBox<String>(paddingList);
			JComboBox<String> hashing = new JComboBox<String>(hashingList);
			JTextField pw = new JTextField("");
			JButton go = new JButton("Go!");

			class cipherListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					String choice = cipher.getSelectedItem().toString();
					file.setCipher(choice);
					if (choice.equals("AES")) {
						pw.setVisible(false);
						keyLength.addItem("128");
						keyLength.addItem("192");
						keyLength.addItem("256");

						cipherMode.addItem("ECB");
						cipherMode.addItem("CBC");
						cipherMode.addItem("CTS");
						cipherMode.addItem("CTR");
						cipherMode.addItem("OFB");
						cipherMode.addItem("CFB");
						cipherMode.addItem("GCM");
					} else if (choice.equals("DES")) {
						pw.setVisible(false);
						cipherMode.addItem("ECB");
						cipherMode.addItem("CBC");
						cipherMode.addItem("CTS");
						cipherMode.addItem("CTR");
						cipherMode.addItem("OFB");
						cipherMode.addItem("CFB");
						cipherMode.addItem("GCM");
						keyLength.removeItem("128");
						keyLength.removeItem("192");
						keyLength.removeItem("256");
					} else {
						pw.setVisible(true);
						keyLength.removeItem("128");
						keyLength.removeItem("192");
						keyLength.removeItem("256");
						cipherMode.removeItem("ECB");
						cipherMode.removeItem("CBC");
						cipherMode.removeItem("CTS");
						cipherMode.removeItem("CTR");
						cipherMode.removeItem("OFB");
						cipherMode.removeItem("CFB");
						cipherMode.removeItem("GCM");
						padding.removeItem("PKCS7Padding");
						padding.removeItem("NoPadding");
						padding.removeItem("ZeroBytePadding");
						go.addActionListener(new EncListener(dialog, textArea, file, pw.getText()));
					}
				}
			}
			class keyLengthListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					String keyLString = keyLength.getSelectedItem().toString();
					if (!keyLString.equals("")) {
						file.setKeyLength(Integer.parseInt(keyLString));
					}
				}
			}
			class cipherModeListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					String choice = cipherMode.getSelectedItem().toString();
					file.setBlockMode(cipherMode.getSelectedItem().toString());
					if (choice.equals("GCM")) {
						padding.removeItem("PKCS7Padding");
						padding.removeItem("NoPadding");
						padding.removeItem("ZeroBytePadding");

					} else if (!choice.isEmpty()) {
						padding.addItem("PKCS7Padding");
						padding.addItem("NoPadding");
						padding.addItem("ZeroBytePadding");
					} else {
						padding.removeItem("PKCS7Padding");
						padding.removeItem("NoPadding");
						padding.removeItem("ZeroBytePadding");
					}
				}
			}
			class paddingListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					file.setPadding(padding.getSelectedItem().toString());
					go.addActionListener(new EncListener(dialog, textArea, file, pw.getText()));
				}
			}
			class hashingListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					file.setHashMode(hashing.getSelectedItem().toString());
					go.addActionListener(new EncListener(dialog, textArea, file, pw.getText()));
				}
			}

			cipher.addActionListener(new cipherListener());
			keyLength.addActionListener(new keyLengthListener());
			cipherMode.addActionListener(new cipherModeListener());
			padding.addActionListener(new paddingListener());
			hashing.addActionListener(new hashingListener());

			dialog.add(cipher, 0);
			dialog.add(keyLength, 1);
			dialog.add(cipherMode, 2);
			dialog.add(padding, 3);
			dialog.add(hashing, 4);
			dialog.add(pw, 5);
			pw.setVisible(false);
			dialog.add(go);



			dialog.setVisible(true);

		}
	}

}
