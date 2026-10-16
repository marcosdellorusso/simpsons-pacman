package entidades;

public class EstadoMuerto extends Estado {
	
	protected int cantIntentosMover;
	
	public EstadoMuerto(Enemigo enemigo) {
		super(enemigo);
		cantIntentosMover = 0;
	}
	
	public void moverse() {
		cantIntentosMover++;
		if (cantIntentosMover == 80)
			enemigo.salir();
	}

	public void colisionarConProtagonista() {

	}
}
