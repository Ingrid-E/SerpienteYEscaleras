package codigo;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ficha extends JPanel {
	//private Jugador jugador
	private Timer moverse;
	private ImageIcon image;
	private JLabel ficha;
	
	public Ficha(Color color) {
		this.setSize(30, 30);
		this.setBackground(color);
	}
	
	private void moverse(int posicion) {
		
	}
}
