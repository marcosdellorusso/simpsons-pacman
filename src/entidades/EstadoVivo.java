package entidades;

import logica.Laberinto;

public class EstadoVivo extends Estado{

	public EstadoVivo(Enemigo enemigo) {
		super(enemigo);
	}

	public void moverse() {
		PosicionAbstracta aDondeMover = enemigo.calculoDeMovimiento();
        Laberinto lab = enemigo.getLaberinto();
        PosicionAbstracta posicion = enemigo.getPosicion();
        int xViejo = posicion.getX();
        int yViejo = posicion.getY();
        enemigo.setXY(aDondeMover.getX(), aDondeMover.getY());
        lab.movimiento(xViejo, yViejo, enemigo);
	}

	public void colisionarConProtagonista() {
		enemigo.comerProtagonista();
	}

}
