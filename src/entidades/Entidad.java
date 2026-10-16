package entidades;

import logica.Laberinto;
import logica.Logica;

public abstract class Entidad {
	protected int direccion;
	protected Logica logica;
	protected Visitor visitor;	
	protected Laberinto lab;
	protected Posicion posicion;
	protected EntidadGrafica entidadGrafica;
	
	public Entidad(int x, int y) {
		posicion = new Posicion(x,y);
	}
	
	public void setXY(int x, int y) {
		posicion.setX(x);
		posicion.setY(y);
		entidadGrafica.actualizarPosicion();
	}
	
	public void setLaberinto(Laberinto lab) {
		this.lab = lab;
	}

	public void setLogica(Logica logica) {
		this.logica = logica;		
	}
	
	public abstract void accept(Visitor v);
	
	public Visitor getVisitor() {
		return visitor;
	}	
	
	public PosicionAbstracta getPosicion() {
		return posicion;
	}
	
	public int getDireccion() {
		return direccion;
	}
	public EntidadGrafica getEntidadGrafica() {
		return entidadGrafica;
	}
	
	public Laberinto getLaberinto() {
		return lab;
	}
}
