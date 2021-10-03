package codigo;

public class Controlador implements Runnable{
	private Jugador jugador2, jugador3;
	protected int turno;
	private static Thread hilo;
	
	public Controlador(Jugador[] jugadores) {
		this.turno = 1;
		jugador2 = jugadores[1];
		jugador3 = jugadores[2];
		
		hilo = new Thread(this);
		hilo.start();
	}
	
	public void start() {
		
	}
	
	public void turno(Jugador jugador) {
		
	}

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
