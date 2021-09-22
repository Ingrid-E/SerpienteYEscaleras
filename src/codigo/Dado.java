package codigo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Dado extends JPanel{
	private static final long serialVersionUID = 1L;
	//atributos
	private int lado;
	private ImageIcon[] cara = asignarImagenes();
	private JLabel dado = new JLabel();
	private Timer timer;
	
	//constructor
	public Dado() {
		this.setLayout(null);
		this.dado.setIcon(cara[3]);
		this.dado.setBounds(0, 0, 80, 80);
		this.setSize(80, 80);
		this.add(dado);
	}

	
	private ImageIcon[] asignarImagenes() {
		ImageIcon[] imagenes = new ImageIcon[6];
		InputStream input;
		BufferedImage caraDados = null;
		try {
			input = Tabla.class.getResourceAsStream("/img/dado.jpg");
			caraDados = ImageIO.read(input);
			for(int i=0; i < 6; i++) {
				imagenes[i] = new ImageIcon(caraDados.getSubimage(i*80, 0, 80, 80));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagenes;
		
	}
	
	//random(0,5)
	public void lanzar() {
		Random random = new Random();
		int numeroDado = random.nextInt(6);//0->1;1->2;2->3;3->4;4->5;
		girar(numeroDado);
	}
	
	//Animaci�n del dado girando
	private void girar(int valor) {
		dado.setIcon(cara[valor]);
	}
}
