package entidades;

public class Fruta extends EntidadEstatica{
	
	private int valorFruta;
	
	public Fruta(int x, int y,int valorFruta) {
		super(x,y);
		this.valorFruta = valorFruta;
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("", this.posicion);
	}
	
	public void afectarJuego() {
		logica.sumarPuntos(valorFruta);
	}
	
}
