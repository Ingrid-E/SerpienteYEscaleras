package codigo;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ficha extends JPanel {
	//private Jugador jugador
	private ImageIcon image;
	private JLabel ficha;
	protected static final int tama = 20;
	public Ficha(Color color) {
		this.setSize(tama,tama);
		this.setBackground(color);
	}
	
}
