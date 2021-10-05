/*
 * Programaci�n interactiva
 * Autor: Ingrid Echeverry Montoya - 1943542
 * Autor: Jhan Alejandro Perez Umbarila - 1941003
 * Juego de escaleras y serpientes 
 */
package codigo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class Jugador. Contiene todos los datos y caracteristicas de un jugador.
 */
public class Jugador{
	protected int posicion;
	protected boolean serpiente, escalera, gano;
	private Ficha ficha;
	protected int turno;
	private boolean direccion;
	private int opcionFinalizar;
	private static ArrayList<Object[]> posEscaleras = new ArrayList<>();
	private static ArrayList<Object[]> posSerpientes = new ArrayList<>();
	
	/**
	 * Instantiates a new jugador. Constructor donde se crean y asignan los valores por defecto (iniciales) de cada uno de los atributos.
	 * @param turno the turno
	 * @param ficha the ficha
	 */
	public Jugador(int turno, Ficha ficha){
		this.turno = turno;
		this.ficha = ficha;
		this.direccion = true;
		this.posicion = 1;
		this.gano = false;
		posEscaleras.add(new Object[] {2,1,-3,false,38});
		posEscaleras.add(new Object[] {28,-4,-6,true,84});
		posEscaleras.add(new Object[] {43,1,-2,true,64});
		posEscaleras.add(new Object[] {59,-1,-3,true,81});
		posEscaleras.add(new Object[] {69,-3,-3,false,95});
		
		posSerpientes.add(new Object[] {52,1,2,false,31});
		posSerpientes.add(new Object[] {53,0,5,true,8});
		posSerpientes.add(new Object[] {56,1,4,false,15});
		posSerpientes.add(new Object[] {61,1,5,false,19});
		posSerpientes.add(new Object[] {98,-1,3,true,61});
		
	}
	
	
	/**
	 * Mover ficha. Permite que el jugador (ficha) se desplaze de acuerdo a la cantidad que aparece en el dado al lanzarlo
	 * @param n the n
	 */
	public void moverFicha(int n) {
		int dir = 55;
		this.serpiente = false;
		this.escalera = false;
		//true es derecha, false es izquierda
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			//Atributo
			int movimientos = n;
			int x = ficha.getX();
			int y = ficha.getY();
			int terminar = 0;
			@Override
			public void run() {
				if((posicion + movimientos) > 100) {
					timer.cancel();
				}
				if(Tabla.reiniciar) {
					Tabla.cambiarVentana("Juego");
					timer.cancel();
				}
				if(posicion==100) {
					Object opciones[] = {"Otra", "Terminar"};
					String ganador;
					if(turno == 1) ganador = "Ganaste!";
					else ganador = "Jugador " + turno + " gano!";
					opcionFinalizar = JOptionPane.showOptionDialog(Tabla.ventana, ganador, "Quieres volver a jugar?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]);
					gano = true;
					Tabla.lanzar.setVisible(false);
					Tabla.finalJuego(opcionFinalizar);
					timer.cancel();
				}
				
				if(movimientos <= 0) {
			
					
					Object[] subirBajar = null;
					
					for(Object[] fila: posEscaleras) {
						if((int)fila[0] == posicion) {
							subirBajar = fila;
							escalera = true;
							break;
						}
					}
					
					for(Object[] fila: posSerpientes) {
						if((int)fila[0] == posicion) {
							subirBajar = fila;
							serpiente = true;
							break;
						}
					}
					
					if(serpiente || escalera) {
						if(serpiente) Tabla.serpiente.setVisible(true);
						if(escalera) Tabla.escalera.setVisible(true);
						terminar = -4;
					}
					
					if(movimientos == terminar) {
						
						if(serpiente) Tabla.serpiente.setVisible(false);
						if(escalera) Tabla.escalera.setVisible(false);
						if(subirBajar != null) {
							//posicion,x,y,direcion, posfinal
							y += dir*(int)subirBajar[2];
							x += dir*(int)subirBajar[1];
							direccion = (boolean)subirBajar[3];
							posicion = (int)subirBajar[4];
							ficha.setLocation(x, y);
						}
						serpiente = false;
						escalera = false;
						timer.cancel();
					}
					
					
				}
				else {
					//Cambiar direccion de la ficha
					if((x+dir) > 525) {
						direccion = false;
					}
					if((x-dir) < 0) {
						direccion = true;
					}
					//Mover la ficha
					if(posicion%10 == 0) {
						y -= 55;
						ficha.setLocation(x, y);
					}else {
						
						if(direccion) {
								x += dir;
								ficha.setLocation(x, y);
						}else if(!direccion) {
								x -= dir;
								ficha.setLocation(x, y);
							
						}
					}
					
					posicion++;
				}
				movimientos--;
				
				
			}
			
		}, 0, 500);
		


	}
	
	
	/**
	 * Gets the posicion. Retorna la posici�n m�s actual en la que esta el jugador.
	 * @return the posicion
	 */
	public int getPosicion() {
		return posicion;
	}

}
