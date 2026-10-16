package entidades;

public class PacDot extends EntidadEstatica{
	
	public PacDot(int x, int y) {
		super(x,y);
		posicion.setHeight(8);
		posicion.setWidth(8);
		this.entidadGrafica = new EntidadGrafica("/imagenes/pacDotRosca8x8.png", this.posicion);
	}
	
	public void afectarJuego() {
		logica.sumarPuntos(50);
	}

}
