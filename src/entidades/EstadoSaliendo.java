package entidades;

import java.util.LinkedList;
import java.util.List;

import logica.Laberinto;

public class EstadoSaliendo extends Estado {
	
	protected int cantMovimientos;
	
	public EstadoSaliendo(Enemigo enemigo) {
		super(enemigo);
		cantMovimientos = 0;
	}

	@Override
	public void moverse() {
		
		cantMovimientos++;
		
		if (cantMovimientos == 80)
			enemigo.revivir();
		if(cantMovimientos > 40) {
		PosicionAbstracta posicionAMover = null;
		List<Posicion> movimientosPosibles = new LinkedList<Posicion>();
		PosicionAbstracta posicion = enemigo.getPosicion();
		int xViejo = posicion.getX();
		int yViejo = posicion.getY();
		Laberinto lab = enemigo.getLaberinto();
		int direccion = enemigo.getDireccion();
		int xDestino = 400;
		int yDestino = 0;
		
		//Guardo los movimientos posibles luego chequeo cual corresponde segun las posicion del pacman 
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
		
		enemigo.setXY(posicionAMover.getX(), posicionAMover.getY());
		lab.movimiento(xViejo, yViejo, enemigo);
		enemigo.setDireccion(direccion);
		}
	}
	

	@Override
	public void colisionarConProtagonista() {
		enemigo.comerProtagonista();
	}
}
