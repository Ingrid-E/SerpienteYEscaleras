/*
 * Programaci�n interactiva
 * Autor: Ingrid Echeverry Montoya - 1943542
 * Autor: Jhan Alejandro Perez Umbarila - 1941003
 * Juego de escaleras y serpientes 
 */
package codigo;

import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class Main. Permite ejecutar el juego
 */
public class Main {

	/**
	 * The main method. Metodo principal, contiene las instrucciones que permiten iniciar el juego.
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						@SuppressWarnings("unused")
						Tabla ventana = new Tabla();
						//GameGUI gameWindow = new GameGUI();
					}
				});
			}
			
		});
	}

}
