package codigo;

public class Jugador{
	private String nombre;
	private int posicion;
	private boolean esReal;
	private Ficha ficha;
	private Controlador controlador;
	protected int turno;
	
	public Jugador(int turno, boolean esReal){
		this.turno = turno;
	}
	
	public Jugador(int turno, boolean esReal, Controlador controlador){
		this.turno = turno;
		this.controlador = controlador;
	}
	
	protected void moverFicha(int cantidad) {
		
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
