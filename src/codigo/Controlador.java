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
	private int turno;
	private static Thread hilo;
	
	public Controlador() {
		this.turno = 2;
		hilo = new Thread(this);
		jugador1 = new Jugador(1, true);
		jugador2 = new Jugador(2, true);
		jugador3 = new Jugador(3,true);
		hilo.start();
	}
	
	public void start() {
		
	}
	
	public void turno(Jugador jugador) {
		
	}

	@Override
	public void run() {
		//Segun el turno que llame al jugador que es para que tire 
		System.out.println("Iniciando Hilo");
		switch(turno) {
		case 1:
			try {
				System.out.println("Esperando que lance jugador 1");
				hilo.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 2:
			//Esperar que se tire el dado
			try {
				System.out.println("Jugador 2: lanzo el dado");
				Tabla.dado.lanzar();
				hilo.sleep(3000);
				System.out.println("Obteniendo resultado...");
				int movimientos = Tabla.dado.lado();
				System.out.println("Jugador 2 mueve su ficha " + movimientos + " pasos");
				jugador2.moverFicha(movimientos);
				hilo.sleep(movimientos*800);
				System.out.println("Jugador 2 termino de mover" );
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
				System.out.println("Jugador 3: lanzo el dado");
				Tabla.dado.lanzar();
				hilo.sleep(3000);
				System.out.println("Obteniendo resultado...");
				int movimientos = Tabla.dado.lado();
				System.out.println("Jugador 3 mueve su ficha " + movimientos + " pasos");
				jugador2.moverFicha(movimientos);
				hilo.sleep(movimientos*800);
				System.out.println("Jugador 3 termino de mover" );
				turno = 1;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

		//hilo.interrupt();
	}
	
	
}
