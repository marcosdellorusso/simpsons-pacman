package entidades;

import java.util.LinkedList;
import java.util.List;

public class Pinky extends Enemigo {

	public Pinky(int x, int y) {
		super(x, y);
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/pinkySelma32x32.png", this.posicion);
	}
	
	public PosicionAbstracta calculoDeMovimiento(){
		PosicionAbstracta posicionAMover = null;
		List<Posicion> movimientosPosibles = new LinkedList<Posicion>();
		PosicionAbstracta posicionProtagonista = logica.getPosicionProtagonista();
		int direccionProtagonista = logica.getDireccionProtagonista();
		
		int xDestino = posicionProtagonista.getX();
		int yDestino = posicionProtagonista.getY();
		
		switch(direccionProtagonista) {
			case 0:{ //Pacman mira hacia arriba 
				xDestino = xDestino - (32*2);
				yDestino = yDestino - (32*4);
				break;
			}
			case 90:{//Pacman mira hacia la derecha
				xDestino = xDestino + (32*4);
				yDestino = yDestino - (32*2);
				break;
			}
			case 180:{//Pacman mira hacia abajo
				xDestino = xDestino + (32*2);
				yDestino = yDestino + (32*4);
				break;
			}
			case 270:{//Pacman mira hacia la izquierda
				xDestino = xDestino - (32*4);
				yDestino = yDestino + (32*2);
				break;
			}
		}
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
			if(x >= 384 && x <= 415 && y >= 260 && y <= 291) {
				movimientosPosibles.remove(contador);
			}
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
				aux = Math.sqrt(Math.abs(((xDestino - pos.getX())*(xDestino - pos.getX()) + 
								(yDestino - pos.getY())*(yDestino - pos.getY()))));
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
		entidadGrafica.setImagen("/imagenes/pinkySelmaPanico32x32.png");
	}
	
	public void revivir() {
		estado = new EstadoVivo(this);
		entidadGrafica.setImagen("/imagenes/pinkySelma32x32.png");
	}
	
	public void morir() {
		estado = new EstadoMuerto(this);
		entidadGrafica.setImagen("/imagenes/pinkySelmaMuerto32x32.png");
		this.setXY(416, 320);
		logica.sumarPuntos(500);
	}

	@Override
	public void salir() {
		estado = new EstadoSaliendo(this);
		entidadGrafica.setImagen("/imagenes/pinkySelma32x32.png");
	}
}