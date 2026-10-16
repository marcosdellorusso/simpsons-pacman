package entidades;

public class VisitorProtagonista implements Visitor{

    @Override
    public void visitarEnemigo(Enemigo e) {
        //Sos un personaje y te encontras un enemigo
    	e.colisionarConProtagonista();
    }

	@Override
	public void visitarEntidadEstatica(EntidadEstatica e) {
		e.afectarJuego();
		e.getEntidadGrafica().setVisible(false);
		e.getLaberinto().eliminarEntidad(e);
	}

	@Override
	public void visitarProtagonista(Protagonista p) {
	}

}