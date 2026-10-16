package entidades;

public class PowerPellet extends EntidadEstatica{
	
	public PowerPellet(int x, int y) {
		super(x,y);
		posicion.setHeight(20);
		posicion.setWidth(20);
		this.entidadGrafica = new EntidadGrafica("/imagenes/powerPelletRosca20x20.png", this.posicion);
	}
	
	public void afectarJuego() {
		logica.sumarPuntos(200);
		logica.panico();
	}

}
