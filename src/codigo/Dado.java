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
		image = new ImageIcon[6];
	}
	
	private void initGui() {
		image[0] = new ImageIcon("src/imagenes/dado1.png");
		image[1] = new ImageIcon("src/imagenes/dado2.png");
		image[2] = new ImageIcon("src/imagenes/dado3.png");
		image[3] = new ImageIcon("src/imagenes/dado4.png");
		image[4] = new ImageIcon("src/imagenes/dado5.png");
		image[5] = new ImageIcon("src/imagenes/dado6.png");
	}
	
	//random(0,6)
	private int lanzar() {
		Random random = new Random();
		numeroDado = random.nextInt(5) + 1;//0->1;1->2;2->3;3->4;4->5;5->6
		return numeroDado;
	}
	
	//Animación del dado girando
	private void girar() {
		dado.setIcon(image[lanzar()]);
	}
}
