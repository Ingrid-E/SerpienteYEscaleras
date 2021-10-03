package codigo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import complementos.ImageResize;

public class Tabla extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static JFrame ventana;
	private static ImageIcon imgFondo = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/fondo.jpg")), 630, 700).resize());
	private static ImageIcon imgBotones = new ImageIcon(Tabla.class.getResource("/img/botones.png"));	
	private static ImageIcon imgTabla = new ImageIcon(Tabla.class.getResource("/img/tabla.png"));
	private static ImageIcon imgJugador1 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador1.png")), 70, 70).resize());
	private static ImageIcon imgJugador2 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador2.png")), 70, 70).resize());
	private static ImageIcon imgJugador3 = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/jugador3.png")), 70, 70).resize());
	private static ImageIcon turnoActual = new ImageIcon(Tabla.class.getResource("/img/selectionCircle.png"));
	private static ImageIcon imgSnake = new ImageIcon(Tabla.class.getResource("/img/snake.gif"));
	private static ImageIcon imgEscalera = new ImageIcon(Tabla.class.getResource("/img/escalera.gif"));
	private static JLabel iconoJugador1,iconoJugador2, iconoJugador3;
	protected static JLabel jugadorActual,serpiente,escalera;
	public static Font fuente = Tabla.loadFont();
	private static Color amarillo = new Color(246,184,81);
	private static Color azul = new Color(37,61,95);
	private static JLabel fondo = new JLabel();
	private static JPanel jugar = boton("Jugar");
	private static JPanel reglas = boton("Reglas");
	private static JLabel salir = new JLabel("x");
	protected static JPanel lanzar = boton("Lanzar", 93,30,24);
	private Escucha escucha = new Escucha();
	protected static Dado dado = new Dado();
	private static Ficha ficha1 = new Ficha(Color.RED);
	private static Ficha ficha2 = new Ficha(Color.GREEN);
	private static Ficha ficha3 = new Ficha(Color.BLUE);
	private static Jugador jugador1,jugador2, jugador3;
	private static Controlador controlador;
	
	
	public Tabla() {
		ventana = this;
		
		fondo.setIcon(imgFondo);
		fondo.setBounds(0,0,630,700);
		
		jugar.setBounds(182, 254, 260, 80);
		reglas.setBounds(182, 399, 260, 80);
		lanzar.setBounds(142, 620, 93, 30);
	
		
		jugadorActual = new JLabel();
		jugadorActual.setIcon(turnoActual);
		
		salir.setForeground(Color.WHITE);
		salir.setFont(fuente.deriveFont(36f));
		salir.setBounds(602, 0, 21, 44);
		salir.addMouseListener(escucha);
		
		serpiente = new JLabel();
		serpiente.setIcon(imgSnake);
		serpiente.setBounds(-25, -25, 600, 600);
		serpiente.setVisible(false);
		
		escalera = new JLabel();
		escalera.setIcon(imgEscalera);
		escalera.setBounds(100, 25, 350, 500);
		escalera.setVisible(false);

		
		iconoJugador1 = new JLabel(); iconoJugador2 = new JLabel(); iconoJugador3 = new JLabel();
		iconoJugador1.setIcon(imgJugador1);iconoJugador2.setIcon(imgJugador2);iconoJugador3.setIcon(imgJugador3);
		
		iconoJugador1.setBounds(336, 600, 70, 70);
		iconoJugador2.setBounds(428, 600, 70, 70);
		iconoJugador3.setBounds(520, 600, 70, 70);
		
		dado.setBounds(47, 605, Dado.tama, Dado.tama);
	
		
		ventana.setUndecorated(true);
		ventana.setLayout(null);
		ventana.setResizable(false);
		ventana.setVisible(true);
		ventana.setSize(630, 700);
		ventana.getContentPane().setBackground(azul);
		ventana.setLocationRelativeTo(null);
		ventana.addMouseListener(escucha);
		ventana.addMouseMotionListener(escucha);

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
			break;
		case "Reglas":
			reglas();
			break;
		}
		ventana.revalidate();
		ventana.repaint();
	}
	
	private static void menu() {
		JLabel titulo = new JLabel("Serpientes y Escaleras");
		titulo.setFont(fuente.deriveFont(52f));
		titulo.setForeground(amarillo);
		titulo.setBounds(0,90,630,58);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		ventana.add(titulo);
		ventana.add(jugar);
		ventana.add(reglas);
		ventana.add(fondo);
	}
	
	private static void reglas() {
		//Agreagar elementos de interfaz aqui como en menu() o juego()
		//Agregar un boton para devolver al menu principal
		ventana.add(fondo);
	}
	

	
	private static void juego() {
		JPanel juegoDeMesa = new JPanel();
		juegoDeMesa.setLayout(null);
		juegoDeMesa.setBounds(40, 35, 550, 550);
		
		JLabel tabla = new JLabel();
		tabla.setIcon((new ImageResize(imgTabla, 550,550)).resize());
		tabla.setBounds(0, 0, 550, 550);
		//------------------------------>>>>>>>>Fichas<<<<<<<<--------------------------------//
		ficha1.setBounds(7, 500, Ficha.tama, Ficha.tama);
		ficha2.setBounds(30, 515, Ficha.tama, Ficha.tama);
		ficha3.setBounds(7, 525, Ficha.tama, Ficha.tama);
		
		jugador1 = new Jugador(1, ficha1);
		jugador2 = new Jugador(2, ficha2);
		jugador3 = new Jugador(3, ficha3);
		
		jugadorActual.setBounds(334, 598, 74, 74);
		
		juegoDeMesa.add(escalera);
		juegoDeMesa.add(serpiente);
		juegoDeMesa.add(ficha1);
		juegoDeMesa.add(ficha2);
		juegoDeMesa.add(ficha3);
		juegoDeMesa.add(tabla);
		
		Jugador[] jugadores = {jugador1, jugador2,jugador3};
		controlador = new Controlador(jugadores);
		
		Tabla.lanzar.setVisible(true);
		
		ventana.add(dado);
		ventana.add(iconoJugador1);
		ventana.add(iconoJugador2);
		ventana.add(iconoJugador3);
		ventana.add(jugadorActual);
		ventana.add(lanzar);
		ventana.add(juegoDeMesa);
	}
	
	public static void finalJuego(int n) {
		if(n == 0) {
			cambiarVentana("Juego");
		}else {
			System.exit(0);
		}
	}
	
	
	public class Escucha extends MouseAdapter {
		private int x,y;
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource().equals(salir)) {
				System.exit(0);
			}else if(e.getSource().equals(jugar)) {
				cambiarVentana("Juego");
			}
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x = e.getX();
			y = e.getY();
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			ventana.setLocation(ventana.getLocation().x+e.getX()-x, ventana.getLocation().y +e.getY()-y);
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
		text.setFont(fuente.deriveFont(45f));
		text.setForeground(azul);
		text.setBounds(0, 14, 260, 53);
		text.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton();
		ImageIcon imagen = (new ImageResize(imgBotones, 260,80)).resize();
		ImageIcon imagenMouse = (new ImageResize(new ImageIcon(Tabla.class.getResource("/img/botones_hover.png")), 260,80)).resize();
		button.setIcon(imagen);
		button.setFocusable(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setSize(260,80);
		
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(text.getText() == "Jugar") {
					cambiarVentana("Juego");
				}else if(text.getText() == "Reglas") {
					cambiarVentana("Reglas");
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
					lanzar.setVisible(false);
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						int counter = 0;
						int movimientos = 0;
						int terminar = movimientos;
						@Override
						public void run() {
							// TODO Auto-generated method stub
							if(counter == 1) {
								movimientos = dado.lado();
								terminar = movimientos+1;
								jugador1.moverFicha(movimientos);
							}

							if(terminar != 0) {
								if(jugador1.serpiente || jugador1.escalera) {
									terminar = counter + 2;
								}else if(counter == terminar){
									if(!jugador1.gano) {
										controlador.turno = 2;
										controlador.run();
									}
						
									timer.cancel();
								}
								
							}
							counter++;
							
						}
						
					}, 0,900);
					
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
