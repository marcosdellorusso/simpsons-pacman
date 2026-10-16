package entidades;

public class Vida extends Pocion{
	
	public Vida(int x, int y) {
		super(x,y);
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/protagonistaHomero32x32.png", this.posicion);
	}
	
	public void afectarJuego() {
		logica.sumarVida();
	}

}