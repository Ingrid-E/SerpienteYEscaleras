/*
 * Programación interactiva
 * Autor: Ingrid Echeverry Montoya - 
 * Autor: Jhan Alejandro Perez Umbarila - 1941003
 * Juego de escaleras y serpientes 
 */
package codigo;

// TODO: Auto-generated Javadoc
/**
 * The Class Controlador. Se le da un comportamiento artificial a los jugadores 2 y 3 (verde, azul) 
 */
public class Controlador implements Runnable{
	private Jugador jugador2, jugador3;
	protected int turno;
	private static Thread hilo;
	
	/**
	 * Instantiates a new controlador. Constructor donde se crean y asignan los valores por defecto (iniciales) de cada uno de los atributos.
	 * @param jugadores the jugadores
	 */
	public Controlador(Jugador[] jugadores) {
		this.turno = 1;
		jugador2 = jugadores[1];
		jugador3 = jugadores[2];
		
		hilo = new Thread(this);
		hilo.start();
	}
	

	/**
	 * Run. Inicializa a los jugadores 2 y 3 por orden, despues de que el jugador 1 (usuario: rojo), haya pulsado el botón lanzar
	 */
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		//Segun el turno que llame al jugador que es para que tire 
		switch(turno) {
		case 2:
			//Esperar que se tire el dado
			try {
				Tabla.lanzar.setVisible(false); 
				Tabla.jugadorActual.setLocation(426, 598);
				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador2.moverFicha(movimientos);
				hilo.sleep(movimientos*750);
				if(jugador2.serpiente || jugador2.escalera) {
					hilo.sleep(1500);
				}
				if(!jugador2.gano) {
					turno++;
					run();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			//Esperar que se tire el dado
			try {
				Tabla.jugadorActual.setLocation(518, 598);
				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador3.moverFicha(movimientos);
				hilo.sleep(movimientos*700);
				if(jugador3.serpiente || jugador3.escalera) {
					hilo.sleep(1500);
				}
				if(!jugador3.gano) {
					turno = 1;
					Tabla.lanzar.setVisible(true);
					Tabla.jugadorActual.setLocation(334, 598);
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}
	
}
