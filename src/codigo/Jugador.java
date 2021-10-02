package codigo;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Jugador{
	private String nombre;
	private int posicion;
	private boolean esReal;
	private Ficha ficha;
	private Controlador controlador;
	protected int turno, escalera1, escalera2, escalera3, escalera4;
	private boolean direccion;
	
	public Jugador(int turno, Ficha ficha){
		this.turno = turno;
		this.ficha = ficha;
		this.direccion = true;
		this.posicion = 1;
		escalera1 = 27;
		escalera2 = 42;
		escalera3 = 58;
		escalera4 = 68;
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
					
					if(ficha.getBackground()==Color.green) {
						System.out.print("Posicion :"+posicion);
					}
					
					//Escalera 1 
					if(posicion==1 && n==1) {
						y -= dir*3;
						x += dir*3;
						posicion = 38;
						ficha.setLocation(x, y);
						timer.cancel();
						direccion = false;
					}
					
					//Escalera 2
					if(posicion==27 && n==1||posicion==26 && n==2||posicion==25 && n==3||posicion==24 && n==4
							||posicion==23 && n==5||posicion==22 && n==6) {
						y -= dir*6;
						x = 117;
						posicion = 84;
						ficha.setLocation(x, y);
						timer.cancel();
						direccion = true;
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
