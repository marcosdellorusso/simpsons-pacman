package entidades;

public abstract class Enemigo extends Entidad {
	
	protected Estado estado;
	
	public Enemigo(int x, int y) {
		super(x,y);
		estado = new EstadoSaliendo(this);
		visitor = new VisitorEnemigo();
	}
	
	public void accept(Visitor v) {
		v.visitarEnemigo(this);
	}
	
	public abstract PosicionAbstracta calculoDeMovimiento();
	
	public void moverse() {
		estado.moverse();
	}

	public void colisionarConProtagonista(){
		estado.colisionarConProtagonista();
	}

	public int getDireccion() {
		return direccion;
	}
	
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public void comerProtagonista() {
		logica.comerProtagonista();
	}

	public abstract void salir();
	public abstract void entrarEnPanico();
	public abstract void revivir();
	public abstract void morir();

	
	
}
