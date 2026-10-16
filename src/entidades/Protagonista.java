package entidades;

public class Protagonista extends Entidad {
	
	private int direccionGuardada;
	private int contDireccionGuardada;
	private int bombas;
	private boolean pocionVelocidad;
	
	public Protagonista(int x, int y) {
		super(x,y);
		visitor = new VisitorProtagonista();
		bombas = 0;
		posicion.setHeight(32);
		posicion.setWidth(32);
		this.entidadGrafica = new EntidadGrafica("/imagenes/protagonistaHomeroDerecha32x32.gif", this.posicion);
	}
	
	public void moverse() {
		int xViejo = posicion.getX();
		int yViejo = posicion.getY();
		boolean seMovio = false;
		if(xViejo >= 352 && xViejo <= 447 && yViejo >= 256 && yViejo <= 287)
			if (direccionGuardada == 90 || direccionGuardada == 270)
				direccionGuardada = direccion;
		if (contDireccionGuardada > 0)
			switch(direccionGuardada) {
				case 0: {
					if (lab.movimientoPosible(posicion.getX() + 4, posicion.getY(), posicion.getHeight(), posicion.getWidth())) {
						posicion.setX(posicion.getX()+4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroDerecha32x32.gif");
						seMovio = true;
						direccion = direccionGuardada;
						contDireccionGuardada = 0;
					}
					break;
				}
				case 90: {
					if (lab.movimientoPosible(posicion.getX(), posicion.getY() + 4, posicion.getHeight(), posicion.getWidth())) {
						posicion.setY(posicion.getY()+4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroAbajo32x32.gif");
						seMovio = true;
						direccion = direccionGuardada;
						contDireccionGuardada = 0;
					}
					break;
				}
				case 180: {
					if (lab.movimientoPosible(posicion.getX() - 4, posicion.getY(), posicion.getHeight(), posicion.getWidth())) {
						posicion.setX(posicion.getX()-4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroIzquierda32x32.gif");
						seMovio = true;
						direccion = direccionGuardada;
						contDireccionGuardada = 0;
					}
					break;
				}
				case 270: {
					if (lab.movimientoPosible(posicion.getX(), posicion.getY() - 4, posicion.getHeight(), posicion.getWidth())) {
						posicion.setY(posicion.getY()-4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroArriba32x32.gif");
						seMovio = true;
						direccion = direccionGuardada;
						contDireccionGuardada = 0;
					}
					break;
				}
			}
		if (!seMovio)
			switch(direccion) {
				case 0: {
					if (lab.movimientoPosible(posicion.getX() + 4, posicion.getY(), posicion.getHeight(), posicion.getWidth())) {
						posicion.setX(posicion.getX()+4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroDerecha32x32.gif");
					}
					break;
				}
				case 90: {
					if (lab.movimientoPosible(posicion.getX(), posicion.getY() + 4, posicion.getHeight(), posicion.getWidth())) {
						posicion.setY(posicion.getY()+4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroAbajo32x32.gif");
					}
					break;
				}
				case 180: {
					if (lab.movimientoPosible(posicion.getX() - 4, posicion.getY(), posicion.getHeight(), posicion.getWidth())) {
						posicion.setX(posicion.getX()-4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroIzquierda32x32.gif");
					}
					break;
				}
				case 270: {
					if (lab.movimientoPosible(posicion.getX(), posicion.getY() - 4, posicion.getHeight(), posicion.getWidth())) {
						posicion.setY(posicion.getY()-4);
						entidadGrafica.setImagen("/imagenes/protagonistaHomeroArriba32x32.gif");
					}
					break;
				}
			}
		contDireccionGuardada--;
		entidadGrafica.actualizarPosicion();
		lab.movimiento(xViejo, yViejo, this);
	}
	
	public void setDireccion(int direccion) {
		this.direccionGuardada = direccion;
		this.contDireccionGuardada = 30;
	}
	
	public boolean tieneVelocidad() {
		return pocionVelocidad;
	}

	public void accept(Visitor v) {
		v.visitarProtagonista(this);
	}
	
	public void setBombas(int bombas) {
		this.bombas = bombas;
	}
	
	public int cantBombas() {
		return bombas;
	}	
	
	
	public void setPocionVelocidad(boolean velocidad) {
		pocionVelocidad = velocidad;
	}
	
}
