package codigo;

import java.awt.Color;

import javax.swing.JPanel;

public class Ficha extends JPanel {
	private static final long serialVersionUID = 1L;
	//private Jugador jugador
	protected static final int tama = 20;
	public Ficha(Color color) {
		this.setSize(tama,tama);
		this.setBackground(color);
	}
	
}
