package controller;

import view.Editor;

/**
 * Die Klasse Manager, zum Starten des Programms.
 * @author Stefan Böhling
 */
public class Manager {
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]){
		Editor editor = new Editor();
		editor.initFrame();
	}
	

}
