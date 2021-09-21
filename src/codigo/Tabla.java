package codigo;

import java.awt.Color;
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

public class Tabla extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JFrame ventana;
	private static ImageIcon imgFondo = new ImageIcon(Tabla.class.getResource("/img/fondo.jpg"));
	private static ImageIcon imgBotones = new ImageIcon(Tabla.class.getResource("/img/botones.png"));
	private static ImageIcon imgTabla = new ImageIcon(Tabla.class.getResource("/img/tabla.png"));
	public static Font fuente = Tabla.loadFont();
	private static Color amarillo = new Color(246,184,81);
	private static Color azul = new Color(37,61,95);
	private static JLabel fondo = new JLabel();
	private static JPanel jugar = boton("Jugar");
	private static JPanel reglas = boton("Reglas");
	private static JLabel salir = new JLabel("x");
	@SuppressWarnings("unused")
	private static JPanel lanzar = boton("Lanzar");
	private Escucha escucha = new Escucha();
	
	
	
	public Tabla() {
		ventana = this;
		
		fondo.setIcon(imgFondo);
		fondo.setBounds(0,0,900,1000);
		
		jugar.setBounds(278, 353, 345, 106);
		reglas.setBounds(278, 541, 345, 106);
		
		salir.setForeground(Color.WHITE);
		salir.setFont(fuente.deriveFont(48f));
		salir.setBounds(857, 0, 28, 58);
		salir.addMouseListener(escucha);
		
		
		
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
		JLabel tabla = new JLabel();
		tabla.setIcon(imgTabla);
		tabla.setBounds(50, 58, 800, 800);
		
		ventana.add(tabla);
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
	
}
