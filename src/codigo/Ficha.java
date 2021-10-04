/*
 * Programación interactiva
 * Autor: Ingrid Echeverry Montoya - 
 * Autor: Jhan Alejandro Perez Umbarila - 1941003
 * Juego de escaleras y serpientes 
 */
package codigo;

import java.awt.Color;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class Ficha. Contiene el metodo donde se crean las fichas.
 */
public class Ficha extends JPanel {
	private static final long serialVersionUID = 1L;
	protected static final int tama = 20;
	
	/**
	 * Instantiates a new ficha. Permite crear una ficha con su respectivo tamaño y color.
	 * @param color the color
	 */
	public Ficha(Color color) {
		this.setSize(tama,tama);
		this.setBackground(color);
	}
	
}
