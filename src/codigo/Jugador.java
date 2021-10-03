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
	private static ArrayList<Object[]> posSerpientes = new ArrayList<>();
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
		
		posSerpientes.add(new Object[] {52,1,2,false,31});
		posSerpientes.add(new Object[] {53,0,5,true,8});
		posSerpientes.add(new Object[] {56,1,4,false,15});
		posSerpientes.add(new Object[] {61,1,5,false,19});
		posSerpientes.add(new Object[] {98,-1,3,true,61});
		
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
				
				if(posicion==99 && n>1||posicion==98 && n>2||posicion==97 && n>3||
						posicion==96 && n>4||posicion==95 && n>5) {
					movimientos = 0;
					timer.cancel();
				}
				
				if(posicion==100) {
					movimientos = 0;
					Tabla.lanzar.setVisible(false);
					timer.cancel();
				}
				
				if(movimientos == 0) {
					//System.out.println("Posicion Final: " +  posicion);
					
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
					
					for(Object[] filaSerpientes: posSerpientes) {
						if((int)filaSerpientes[0] == posicion) {
							//posicion,x,y,direcion, posfinal
							y += dir*(int)filaSerpientes[2];
							x += dir*(int)filaSerpientes[1];
							direccion = (boolean)filaSerpientes[3];
							posicion = (int)filaSerpientes[4];
							ficha.setLocation(x, y);
						}
					}
					
					timer.cancel();
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
	
	public int getPosicion() {
		return posicion;
	}

}
