package entidades;

import java.util.LinkedList;
import java.util.List;

public class Clyde extends Enemigo {

	public Clyde(int x, int y) {
		super(x, y);
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/clydePatty32x32.png", this.posicion);
	}

	public PosicionAbstracta calculoDeMovimiento() {
		PosicionAbstracta posicionAMover = null;
		List<Posicion> movimientosPosibles = new LinkedList<Posicion>();
		PosicionAbstracta posicionProtagonista = logica.getPosicionProtagonista();
		int xProtagonista = posicionProtagonista.getX();
		int yProtagonista = posicionProtagonista.getY();
		double distanciaAlProtagonista = Math.sqrt(Math.abs(((xProtagonista - posicion.getX())*(xProtagonista - posicion.getX()) + 
				(yProtagonista - posicion.getY())*(yProtagonista - posicion.getY()))));
		
		
		//Guardo los movimientos posibles de Blinky y luego chequeo cual corresponde segun las posicion del pacman 
		//Movimiento a derecha 
		if(direccion != 180 && lab.movimientoPosible(posicion.getX() + 4, posicion.getY(), posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX() + 4, posicion.getY()));
		
		//Movimiento hacia abajo
		if(direccion != 270 && lab.movimientoPosible(posicion.getX(), posicion.getY() + 4, posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX(), posicion.getY() + 4));
		//Movimiento hacia izquierda 
		if(direccion != 0 && lab.movimientoPosible(posicion.getX() - 4, posicion.getY(), posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX() - 4, posicion.getY()));
		
		//Movimiento hacia abajo
		if(direccion != 90 && lab.movimientoPosible(posicion.getX(), posicion.getY() - 4, posicion.getHeight(), posicion.getWidth()))
			movimientosPosibles.add(new Posicion(posicion.getX(), posicion.getY() - 4));
		
		double distancia = 10000;
		double aux;
		int contador = 0;
		for(Posicion posPosible: movimientosPosibles) {
			int x = posPosible.getX();
			int y = posPosible.getY();
			if(x >= 384 && x <= 415 && y >= 260 && y <= 291) {
				movimientosPosibles.remove(contador);
			}
			contador++;
		}
		
		//Si el fantasma tiene que volver para atras porque esta encerrado
		if(movimientosPosibles.isEmpty()) {
			switch(direccion) {
			case 0:
				posicionAMover = new Posicion(posicion.getX() - 4, posicion.getY());
				break;
			case 90:
				posicionAMover = new Posicion(posicion.getX(), posicion.getY() - 4);
				break;
			case 180:
				posicionAMover = new Posicion(posicion.getX() + 4, posicion.getY());
				break;
			case 270:
				posicionAMover = new Posicion(posicion.getX(), posicion.getY() + 4);
				break;
			}
		}else {
			Posicion posicionAux = movimientosPosibles.get(0);
			if(distanciaAlProtagonista > 8*32) {	
				for(Posicion pos: movimientosPosibles) {
					aux = Math.sqrt(Math.abs(((xProtagonista - pos.getX())*(xProtagonista - pos.getX()) + 
								(yProtagonista - pos.getY())*(yProtagonista - pos.getY()))));
					if(aux < distancia) {
						distancia = aux;
						posicionAux = pos;
					}
				}
				posicionAMover = posicionAux;
			}else {
				for(Posicion pos: movimientosPosibles) { //Huye de Homero
					aux = Math.sqrt(Math.abs(((0 - pos.getX())*(0 - pos.getX()) + 
							(800 - pos.getY())*(800 - pos.getY()))));
					if(aux < distancia) {
						distancia = aux;
						posicionAux = pos;
					}
				}
				posicionAMover = posicionAux;
			}
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
		entidadGrafica.setImagen("/imagenes/clydePattyPanico32x32.png");
	}

	public void revivir() {
		estado = new EstadoVivo(this);
		entidadGrafica.setImagen("/imagenes/clydePatty32x32.png");
	}
	
	public void morir() {
		estado = new EstadoMuerto(this);
		entidadGrafica.setImagen("/imagenes/clydePattyMuerto32x32.png");
		this.setXY(384, 320);
		logica.sumarPuntos(500);
	}

	@Override
	public void salir() {
		estado = new EstadoSaliendo(this);
		entidadGrafica.setImagen("/imagenes/clydePatty32x32.png");
	}
	
}