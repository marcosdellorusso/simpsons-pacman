package entidades;

public interface Visitor {
    public void visitarProtagonista(Protagonista p);
    public void visitarEnemigo(Enemigo e);
    public void visitarEntidadEstatica(EntidadEstatica e);
}