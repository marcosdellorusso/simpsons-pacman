package entidades;

public abstract class EntidadEstatica extends Entidad {

	public EntidadEstatica(int x, int y) {
		super(x, y);
		visitor = new VisitorEntidadEstatica();
	}
	
	public void accept(Visitor v) {
		v.visitarEntidadEstatica(this);
	}

	public abstract void afectarJuego();
	
}
