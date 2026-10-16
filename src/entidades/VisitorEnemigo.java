package entidades;

public class VisitorEnemigo implements Visitor{

    public void visitarEnemigo(Enemigo e) {
        //Sos un enemigo y te encontras con un enemigo
    }

	@Override
	public void visitarEntidadEstatica(EntidadEstatica e) {
	}

	@Override
	public void visitarProtagonista(Protagonista p) {		
	}

}