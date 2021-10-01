package codigo;

import java.util.Timer;
import java.util.TimerTask;

public class Jugador{
	private String nombre;
	private int posicion;
	private boolean esReal;
	private Ficha ficha;
	private Controlador controlador;
	protected int turno;
	private boolean direccion;
	
	public Jugador(int turno, Ficha ficha){
		this.turno = turno;
		this.ficha = ficha;
		this.direccion = true;
		this.posicion = 1;
	}
	
	
	public void moverFicha(int n) {
		int dir = 55;
		
		//true es derecha, false es izquierda
		
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			//Atributo
			int movimientos = n;
			int x = ficha.getX();
			int y = ficha.getY();
			@Override
			public void run() {
				if(movimientos == 0) {
					timer.cancel();
				}else {
					if(posicion%10 == 0 ) {
						y -= 55;
						ficha.setLocation(x, y);
					}
					
					if(direccion) {
						if((x+dir) > 520) {
							direccion = false;
						}else {
							x += dir;
							ficha.setLocation(x, y);
						}
					}else if(!direccion) {
						if((x-dir) < 0) {
							direccion = true;
						}else {
							x -= dir;
							ficha.setLocation(x, y);
						}
					}
					posicion++;
					movimientos--;
				}
				
				
				
			}
			
		}, 0, 500);
		


	}
	
	private Ficha ficha() {
		return this.ficha();
	}
	
	protected void lanzarDado() {
		
	}
	
	private int getPosicion() {
		return posicion;
	}

}
