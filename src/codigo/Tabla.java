package codigo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import complementos.ImageResize;

public class Tabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame ventana;
	private static ImageIcon imgFondo = new ImageIcon(Tabla.class.getResource("/img/fondo.jpg"));
	private static ImageIcon imgBotones = new ImageIcon(Tabla.class.getResource("/img/botones.png"));
	private static ImageIcon imgTabla = new ImageIcon(Tabla.class.getResource("/img/tabla.png"));
	private static ImageIcon imgJugador1 = new ImageIcon(Tabla.class.getResource("/img/jugador1.png"));
	private static ImageIcon imgJugador2 = new ImageIcon(Tabla.class.getResource("/img/jugador2.png"));
	private static ImageIcon imgJugador3 = new ImageIcon(Tabla.class.getResource("/img/jugador3.png"));
	private static JLabel iconoJugador1,iconoJugador2, iconoJugador3;
	public static Font fuente = Tabla.loadFont();
	private static Color amarillo = new Color(246,184,81);
	private static Color azul = new Color(37,61,95);
	private static JLabel fondo = new JLabel();
	private static JPanel jugar = boton("Jugar");
	private static JPanel reglas = boton("Reglas");
	private static JLabel salir = new JLabel("x");
	private static JPanel lanzar = boton("Lanzar", 136,44,36);
	private Escucha escucha = new Escucha();
	private static Dado dado = new Dado();
	private static Ficha ficha1 = new Ficha(Color.RED);
	private static Ficha ficha2 = new Ficha(Color.GREEN);
	private static Ficha ficha3 = new Ficha(Color.BLUE);
	
	
	
	public Tabla() {
		ventana = this;
		
		fondo.setIcon(imgFondo);
		fondo.setBounds(0,0,900,1000);
		
		jugar.setBounds(278, 353, 345, 106);
		reglas.setBounds(278, 541, 345, 106);
		lanzar.setBounds(156, 908, 136, 44);
		
		
		salir.setForeground(Color.WHITE);
		salir.setFont(fuente.deriveFont(48f));
		salir.setBounds(857, 0, 28, 58);
		salir.addMouseListener(escucha);
		
		iconoJugador1 = new JLabel(); iconoJugador2 = new JLabel(); iconoJugador3 = new JLabel();
		iconoJugador1.setIcon(imgJugador1);iconoJugador2.setIcon(imgJugador2);iconoJugador3.setIcon(imgJugador3);
		
		iconoJugador1.setBounds(505, 879, 100, 100);
		iconoJugador2.setBounds(627, 879, 100, 100);
		iconoJugador3.setBounds(750, 879, 100, 100);
		
		dado.setBounds(50, 890, 80, 80);
	
		
		ventana.setUndecorated(true);
		ventana.setLayout(null);
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.setSize(900, 1000);
		ventana.getContentPane().setBackground(azul);
		ventana.setLocationRelativeTo(null);
		cambiarVentana("Menu");
	}
	
	private static void cambiarVentana(String nombre) {
		ventana.getContentPane().removeAll();
		ventana.add(salir);
		switch(nombre) {
		case "Menu":
			menu();
			break;
		case "Juego":
			juego();
		}
		ventana.revalidate();
		ventana.repaint();
	}
	
	private static void menu() {
		JLabel titulo = new JLabel("Serpientes y Escaleras");
		titulo.setFont(fuente.deriveFont(72f));
		titulo.setForeground(amarillo);
		titulo.setBounds(68,138,790,90);
		
		
		
		ventana.add(titulo);
		ventana.add(jugar);
		ventana.add(reglas);
		ventana.add(fondo);
	}
	
	private static void juego() {
		JPanel juegoDeMesa = new JPanel();
		juegoDeMesa.setLayout(null);
		juegoDeMesa.setBounds(50, 58, 800, 800);
		
		JLabel tabla = new JLabel();
		tabla.setIcon(imgTabla);
		tabla.setBounds(0, 0, 800, 800);
		
		ficha1.setBounds(10, 726, 30, 30);
		ficha2.setBounds(45, 746, 30, 30);
		ficha3.setBounds(10, 761, 30, 30);
		
		juegoDeMesa.add(ficha1);
		juegoDeMesa.add(ficha2);
		juegoDeMesa.add(ficha3);
		juegoDeMesa.add(tabla);
		
		
		ventana.add(dado);
		ventana.add(iconoJugador1);
		ventana.add(iconoJugador2);
		ventana.add(iconoJugador3);
		ventana.add(lanzar);
		ventana.add(juegoDeMesa);
	}
	
	
	
	public class Escucha extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(salir)) {
				System.exit(0);
			}else if(e.getSource().equals(jugar)) {
				cambiarVentana("Juego");
			}
		}
	}
	
	
	private static Font loadFont() {
		try {
			fuente = Font.createFont(Font.TRUETYPE_FONT, new File(Tabla.class.getResource("/font/FredokaOne-Regular.ttf").getFile()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(fuente);
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fuente;
	}
	
	private static JPanel boton(String texto) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(345,106);
		panel.setOpaque(false);
		
		JLabel text = new JLabel(texto);
		text.setFont(fuente.deriveFont(64f));
		text.setForeground(azul);
		text.setBounds(0, 14, 345, 77);
		text.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton();
		button.setIcon(imgBotones);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setSize(345,106);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(text.getText() == "Jugar") {
					cambiarVentana("Juego");
				}
			}
		    public void mouseEntered(MouseEvent e) {
		        button.setIcon(new ImageIcon(Tabla.class.getResource("/img/botones_hover.png")));
		    }

		    public void mouseExited(MouseEvent e) {
		        button.setIcon(imgBotones);

		    }
		});
		
		
		panel.add(text);
		panel.add(button);
		return panel;
	}
	
	private static JPanel boton(String texto, int ancho, int altura, float letra) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setOpaque(false);
		
		JLabel text = new JLabel(texto);
		text.setFont(fuente.deriveFont(letra));
		text.setForeground(azul);
		int posicion = (int)(altura-(altura*1.8)/2);
		text.setBounds(0, posicion, ancho, (int)letra);
		text.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton();
		ImageIcon imagen = (new ImageResize(imgBotones, ancho, altura)).resize();
		ImageIcon imagenMouse = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/botones_hover.png")), ancho, altura)).resize();
		button.setIcon(imagen);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setSize(ancho,altura);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(text.getText() == "Lanzar") {
					dado.lanzar();
				}
			}
		    public void mouseEntered(MouseEvent e) {
		        button.setIcon(imagenMouse);
		    }

		    public void mouseExited(MouseEvent e) {
		        button.setIcon(imagen);

		    }
		});
		
		
		panel.add(text);
		panel.add(button);
		return panel;
	}
	
	
}
