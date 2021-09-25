package codigo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	protected static final int tama = 60;
	private int segundos;
	private Escucha escucha;
	
	//constructor
	public Dado() {
		this.setLayout(null);
		this.dado.setIcon(cara[3]);
		this.dado.setBounds(0, 0, tama, tama);
		this.setSize(tama, tama);
		this.add(dado);
		segundos = 0;
		escucha = new Escucha();
		timer = new Timer(1000,escucha);
	}

	public void seg(int ini) {
		segundos = ini;
	}
	
	private ImageIcon[] asignarImagenes() {
		ImageIcon[] imagenes = new ImageIcon[6];
		InputStream input;
		BufferedImage caraDados = null;
		try {
			input = Tabla.class.getResourceAsStream("/img/dado.jpg");
			caraDados = ImageIO.read(input);
			for(int i=0; i < 6; i++) {
				imagenes[i] = new ImageIcon(caraDados.getSubimage(i*tama, 0, tama, tama));
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
		int numeroDado = random.nextInt(6); //0->1; 1->2; 2->3; 3->4; 4->5;
		girar(numeroDado);
		
		lado = numeroDado;
	}
	
	//Animaci�n del dado girando
	private void girar(int valor) {
		dado.setIcon(cara[valor]);
	}
	
	public Timer timer() {
		return timer;
	}
	
	public int lado() {
		return lado;
	}
	
	private class Escucha implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			segundos += 1;
			
			Random random = new Random();
			int numTemp = random.nextInt(6); //0->1; 1->2; 2->3; 3->4; 4->5;
			
			dado.setIcon(cara[numTemp]);
			
			if(segundos==4) {
				timer.stop();
				lanzar();
				Tabla.moverFicha();
			}
		}
		
	}
	
}
