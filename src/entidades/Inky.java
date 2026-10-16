package entidades;

import java.util.LinkedList;
import java.util.List;

public class Inky extends Enemigo {
	
	//Inky conoce a blinky porque se basa en su posicion y la de pacman para calcular su movimiento 
	protected Enemigo blinky;
	
	public Inky(int x, int y,Enemigo blinky) {
		super(x, y);
		this.blinky = blinky;
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/inkyFlanders32x32.png", this.posicion);
	}

	public PosicionAbstracta calculoDeMovimiento() {
		PosicionAbstracta posicionAMover = null;
		List<Posicion> movimientosPosibles = new LinkedList<Posicion>();
		PosicionAbstracta posicionProtagonista = logica.getPosicionProtagonista();
		int xBlinky = blinky.getPosicion().getX();
		int yBlinky = blinky.getPosicion().getY();
		int xProtagonista = posicionProtagonista.getX();
		int yProtagonista = posicionProtagonista.getY();
		int xEspejo = 2*(xProtagonista) - xBlinky;
		int yEspejo = 2*(yProtagonista) - yBlinky;
		//Guardo los movimientos posibles de Pinky y luego chequeo cual corresponde segun las posicion del pacman 
		//Movimiento a derecha 
		if(direccion != 180 && lab.movimientoPosible(posicion.getX() + 2, posicion.getY(), posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX() + 2, posicion.getY()));
				
		//Movimiento hacia abajo
		if(direccion != 270 && lab.movimientoPosible(posicion.getX(), posicion.getY() + 2, posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX(), posicion.getY() + 2));
		//Movimiento hacia izquierda 
		if(direccion != 0 && lab.movimientoPosible(posicion.getX() - 2, posicion.getY(), posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX() - 2, posicion.getY()));
				
		//Movimiento hacia abajo
		if(direccion != 90 && lab.movimientoPosible(posicion.getX(), posicion.getY() - 2, posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX(), posicion.getY() - 2));
				
		double distancia = 10000;
		double aux;
		int contador = 0;
		for(Posicion posPosible: movimientosPosibles) {
			int x = posPosible.getX();
			int y = posPosible.getY();
			if(x >= 384 && x <= 415 && y >= 260 && y <= 291) 
				movimientosPosibles.remove(contador);
			contador++;
		}
				
		//Si el fantasma tiene que volver para atras porque esta encerrado
		if(movimientosPosibles.isEmpty()) {
			switch(direccion) {
				case 0:
					posicionAMover = new Posicion(posicion.getX() - 2, posicion.getY());
					break;
				case 90:
					posicionAMover = new Posicion(posicion.getX(), posicion.getY() - 2);
					break;
				case 180:
					posicionAMover = new Posicion(posicion.getX() + 2, posicion.getY());
					break;
				case 270:
					posicionAMover = new Posicion(posicion.getX(), posicion.getY() + 2);
					break;
				}
			}else {
				Posicion posicionAux = movimientosPosibles.get(0);
				for(Posicion pos: movimientosPosibles) {
					aux = Math.sqrt(Math.abs(((xEspejo - pos.getX())*(xEspejo - pos.getX()) + 
										(yEspejo - pos.getY())*(yEspejo - pos.getY()))));
					if(aux < distancia) {
						distancia = aux;
						posicionAux = pos;
					}
				}
					posicionAMover = posicionAux;
			}
				
			if(posicion.getX() < posicionAMover.getX())
				direccion = 0;
			else if(posicion.getX() > posicionAMover.getX())
				direccion = 180;
			else if(posicion.getY() > posicionAMover.getY())
				direccion = 270;
			else if(posicion.getY() < posicionAMover.getY())
				direccion = 90;
				
			return posicionAMover;
	}
	
	public void entrarEnPanico() {
		estado = new EstadoPanico(this);
		entidadGrafica.setImagen("/imagenes/inkyFlandersPanico32x32.png");
	}

	public void revivir() {
		estado = new EstadoVivo(this);
		entidadGrafica.setImagen("/imagenes/inkyFlanders32x32.png");
	}
	
	public void morir() {
		estado = new EstadoMuerto(this);
		entidadGrafica.setImagen("/imagenes/inkyFlandersMuerto32x32.png");
		this.setXY(352, 320);
		logica.sumarPuntos(500);
	}

	@Override
	public void salir() {
		estado = new EstadoSaliendo(this);
		entidadGrafica.setImagen("/imagenes/inkyFlanders32x32.png");
	}
}