package codigo;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ficha extends JPanel {
	private String color;
	//private Jugador jugador
	private Timer moverse;
	private ImageIcon image;
	private JLabel ficha;
	
	public Ficha(String color) {
		this.color = color;
	}
	
	private void moverse(int posicion) {
		
	}
}
