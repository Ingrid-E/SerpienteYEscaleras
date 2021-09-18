package codigo;

import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Dado {
	private int lado;
	private ImageIcon[] image;
	private JLabel dado;
	private Timer timer;
	private int numeroDado;
	
	//constructor
	public Dado() {
		
	}
	
	//random(0,6)
	private int lanzar() {
		Random random = new Random();
		numeroDado = random.nextInt(6) + 1;
		return numeroDado;
	}
	
	//Animación del dado girando
	private void girar() {
		
	}
}
