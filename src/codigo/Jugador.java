package codigo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
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
	private static ArrayList<Object[]> posEscaleras = new ArrayList<>();
	public Jugador(int turno, Ficha ficha){
		this.turno = turno;
		this.ficha = ficha;
		this.direccion = true;
		this.posicion = 1;
		posEscaleras.add(new Object[] {2,1,-3,false,38});
		posEscaleras.add(new Object[] {28,-4,-6,true,84});
		posEscaleras.add(new Object[] {43,1,-2,true,64});
		posEscaleras.add(new Object[] {59,-1,-3,true,81});
		posEscaleras.add(new Object[] {69,-3,-3,false,94});
		
		
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
					System.out.println("Posicion Final: " +  posicion);
					for(Object[] fila: posEscaleras) {
						if((int)fila[0] == posicion) {
							//posicion,x,y,direcion, posfinal
							y += dir*(int)fila[2];
							x += dir*(int)fila[1];
							direccion = (boolean)fila[3];
							posicion = (int) fila[4];
							ficha.setLocation(x, y);
						}
					}
					timer.cancel();
				}else {
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
