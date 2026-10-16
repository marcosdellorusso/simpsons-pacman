package entidades;

public class Bomba extends Pocion{
	
	public Bomba(int x, int y) {
		super(x,y);
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/pocionBomba32x32.png", this.posicion);
	}
	
	public void afectarJuego() {
		logica.recogerBomba();
	}

}
