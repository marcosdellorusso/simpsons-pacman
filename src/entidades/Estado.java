package entidades;

public abstract class Estado {
	
	protected Enemigo enemigo;
	
	public Estado(Enemigo e) {
		enemigo = e;
	}
	
	public abstract void moverse();
	public abstract void colisionarConProtagonista();

}
