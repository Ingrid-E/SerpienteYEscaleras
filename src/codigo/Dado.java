package codigo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dado extends JPanel{
	private static final long serialVersionUID = 1L;
	//atributos
	private int lado;
	private ImageIcon[] cara = asignarImagenes();
	private JLabel dado = new JLabel();
	private Timer timer;
	protected static final int tama = 60;

	//constructor
	public Dado() {
		this.setLayout(null);
		this.dado.setIcon(cara[3]);
		this.dado.setBounds(0, 0, tama, tama);
		this.setSize(tama, tama);
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
				imagenes[i] = new ImageIcon(caraDados.getSubimage(i*tama, 0, tama, tama));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imagenes;
		
	}
	
	
	//Animaci�n del dado girando
	public void lanzar() {
		int fps = 80;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			//Atributo
			Random random = new Random();
			int caraDado;
			int milisegundos = fps;
			//Metodo
			@Override
			public void run() {
				caraDado = random.nextInt(6); //0->1; 1->2; 2->3; 3->4; 4->5;
				dado.setIcon(cara[caraDado]);

				if(milisegundos == fps*10) {
					lado = caraDado+1;
					timer.cancel();
					Tabla.moverFicha();
				}
				milisegundos += fps;
			}
			
		}, 0, fps);
	}
	
	public Timer timer() {
		return timer;
	}
	
	public int lado() {
		return lado;
	}
	
	
}
