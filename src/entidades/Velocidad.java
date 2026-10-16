package entidades;
import logica.TimerVelocidad;

public class Velocidad extends Pocion{
	
	public Velocidad(int x, int y) {
		super(x,y);
		posicion.setHeight(32);
		posicion.setWidth(17);
		this.entidadGrafica = new EntidadGrafica("/imagenes/pocionVelocidadCerveza17x32.png", this.posicion);
	}

	public void afectarJuego() {
		logica.activarPocionVelocidad();
		TimerVelocidad timer = new TimerVelocidad(this, 7000, this.logica);
		timer.start();
	}
}