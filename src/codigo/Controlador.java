package codigo;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Controlador implements Runnable{
	private Jugador jugador1, jugador2, jugador3;
	protected int turno;
	private static Thread hilo;
	
	public Controlador(Jugador[] jugadores) {
		this.turno = 1;
		jugador1 = jugadores[0];
		jugador2 = jugadores[1];
		jugador3 = jugadores[2];
		
		hilo = new Thread(this);
		hilo.start();
	}
	
	public void start() {
		
	}
	
	public void turno(Jugador jugador) {
		
	}

	@Override
	public void run() {
		//Segun el turno que llame al jugador que es para que tire 
		switch(turno) {
		case 2:
			//Esperar que se tire el dado
			try {
				Tabla.lanzar.setVisible(false); 

				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador2.moverFicha(movimientos);
				hilo.sleep(movimientos*700);
				turno++;
				run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			//Esperar que se tire el dado
			try {
				Tabla.dado.lanzar();
				hilo.sleep(1000);
				int movimientos = Tabla.dado.lado();
				jugador3.moverFicha(movimientos);
				hilo.sleep(movimientos*500);
				turno = 1;
				Tabla.lanzar.setVisible(true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		
	}
	
}
