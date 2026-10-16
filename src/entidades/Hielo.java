package entidades;

import logica.TimerHielo;

public class Hielo extends Pocion{
	
	public Hielo(int x, int y) {
		super(x,y);
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/pocionHielo32x32.png", this.posicion);
	}
	
	public void afectarJuego() {
		logica.congelarEnemigos();
		TimerHielo timer = new TimerHielo(this, 2500, this.logica);
		timer.start();
	}
}