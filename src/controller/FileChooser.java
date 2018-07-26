package controller;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import javax.swing.*;

import model.EncodedFile;
import model.Secret;

/**
 * Die Klasse FileChooser, auf dem JFileChooser aufbauend. Speichert Dateien
 * (EncodedFile) und Keys (Secret) mithilfe von XMLEncoder und öffnet diese
 * wieder als Objekte mithilfe von XMLDecoder.
 * @author Stefan Böhling
 */
public class FileChooser {

	/** The chooser. */
	private JFileChooser chooser = new JFileChooser();

	/**
	 * Save.
	 *
	 * @param file
	 *            the file
	 * @param secret
	 *            the secret
	 */
	public void save(EncodedFile file, Secret secret) {
		XMLEncoder encoder = null;
		int returnValue = chooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(chooser.getSelectedFile())));
			} catch (FileNotFoundException e) {
				System.out.println("No File Selected");
			}
			encoder.writeObject(file);
			encoder.close();
		}
		if (returnValue == JFileChooser.APPROVE_OPTION && !file.getCipher().contains("PBE")) {
			try {
				encoder = new XMLEncoder(
						new BufferedOutputStream(new FileOutputStream(chooser.getSelectedFile() + "key")));
			} catch (FileNotFoundException e) {
				System.out.println("No File Selected");
			}
			encoder.writeObject(secret);
			encoder.close();
		}
	}

	/**
	 * Open file.
	 *
	 * @return the encoded file
	 */
	public EncodedFile openFile() {

		int returnValue = chooser.showOpenDialog(null);
		XMLDecoder decoder = null;
		EncodedFile file = null;

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(chooser.getSelectedFile())));
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
			}
			file = (EncodedFile) decoder.readObject();
		}

		return file;
	}

	/**
	 * Open secret.
	 *
	 * @return the secret
	 */
	public Secret openSecret() {
		int returnValue = chooser.showOpenDialog(null);
		XMLDecoder decoder = null;
		Secret secret = null;

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(chooser.getSelectedFile())));
			} catch (FileNotFoundException e) {
				System.out.println("file not found");
			}
			secret = (Secret) decoder.readObject();
		}

		return secret;
	}

}
