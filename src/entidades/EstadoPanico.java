package entidades;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import logica.Laberinto;

public class EstadoPanico extends Estado {
	
	protected int cantMovimientos;

	public EstadoPanico(Enemigo enemigo) {
		super(enemigo);
		cantMovimientos = 0;
	}

	public void moverse(){
		cantMovimientos++;
		if (cantMovimientos == 200)
			enemigo.revivir();
		
		Laberinto lab = enemigo.getLaberinto();
		int direccion = enemigo.getDireccion();
		PosicionAbstracta posicion = enemigo.getPosicion();
	
		int xViejo = posicion.getX();
		int yViejo = posicion.getY();
		
		PosicionAbstracta posicionAMover = null;
		List<Posicion> movimientosPosibles = new LinkedList<Posicion>();
		
		
		//Guardo los movimientos posibles y luego chequeo cual corresponde segun las posicion del pacman 
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
			int direccionRandom = new Random().nextInt(movimientosPosibles.size());	
			posicionAMover = movimientosPosibles.get(direccionRandom);
			
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
	
	public void colisionarConProtagonista() {
		enemigo.morir();
	}

}