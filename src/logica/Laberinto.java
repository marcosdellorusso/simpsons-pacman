package logica;
import java.util.HashSet;
import java.util.Set;

import entidades.Entidad;
import entidades.PosicionAbstracta;
import entidades.Visitor;
import gui.Gui;

public class Laberinto {
	//Atributos de instancia
	private Zona[][] zonas;
	private Gui gui;
	private int velocidadProtagonista, velocidadEnemigos;
	private Logica logica;
	private int cantEntidades;
	
	public Laberinto(Gui gui, Logica logica) {
		this.gui = gui;
		this.logica = logica;
		zonas = new Zona[25][25];
		zonas[12][9] = new Zona(); // puerta de los fantasmas
		cantEntidades = 0;
	}
	
	public void setEntidad(Entidad e) {
		int[] punto1 = {e.getPosicion().getX(), e.getPosicion().getY()};
		int[] punto2 = {e.getPosicion().getX() + e.getPosicion().getWidth() - 1, e.getPosicion().getY()};
		int[] punto3 = {e.getPosicion().getX(),e.getPosicion().getY() + e.getPosicion().getHeight() - 1};
		int filaZona, colZona;
		
		filaZona = punto1[0] / 32;
		colZona = punto1[1] / 32;
		if (zonas[filaZona][colZona] == null)
			zonas[filaZona][colZona] = new Zona();
			
		filaZona = punto2[0] / 32;
		colZona = punto2[1] / 32;
		if (zonas[filaZona][colZona] == null)
			zonas[filaZona][colZona] = new Zona();
		
		filaZona = punto3[0] / 32;
		colZona = punto3[1] / 32;
		if (zonas[filaZona][colZona] == null)
			zonas[filaZona][colZona] = new Zona();
		
		gui.agregarEntidadGrafica(e.getEntidadGrafica());
		e.setLaberinto(this);
		e.setLogica(logica);
		actualizarZonas(e);
		cantEntidades++;
	}
	
	private Set<Zona> zonasQuePertenece(int x, int y, int height, int width) {
		Set<Zona> conjunto = new HashSet<Zona>();
		int[] punto1 = {x,y};
		int[] punto2 = {x + width - 1,y};
		int[] punto3 = {x,y + height - 1};
		int[] punto4 = {x + width - 1, y + height -1};
		int filaZona, colZona;
		
		filaZona = punto1[0] / 32;
		colZona = punto1[1] / 32;
		conjunto.add(zonas[filaZona][colZona]);	
			
		filaZona = punto2[0] / 32;
		colZona = punto2[1] / 32;
		conjunto.add(zonas[filaZona][colZona]);	
		
		filaZona = punto3[0] / 32;
		colZona = punto3[1] / 32;
		conjunto.add(zonas[filaZona][colZona]);
		
		filaZona = punto4[0] / 32;
		colZona = punto4[1] / 32;
		conjunto.add(zonas[filaZona][colZona]);
		
		return conjunto;
	}
	
	public void eliminarEntidad(Entidad e) {
		eliminarZonas(e.getPosicion().getX(), e.getPosicion().getY(), e);
		cantEntidades--;
	}
	
	private void eliminarZonas(int x, int y,Entidad e) {
		PosicionAbstracta pos = e.getPosicion();
		for(Zona zona : zonasQuePertenece(x,y,pos.getHeight(),pos.getWidth())) {
			zona.eliminarEntidad(e);
		}
	}
	
	private void actualizarZonas(Entidad e){
		PosicionAbstracta pos = e.getPosicion();
		for(Zona zona : zonasQuePertenece(pos.getX(),pos.getY(),pos.getHeight(),pos.getWidth())) {
			zona.setEntidad(e);
		}
	}
	
	public synchronized void movimiento(int xOrigen, int yOrigen, Entidad e) {
		eliminarZonas(xOrigen, yOrigen, e);
		actualizarZonas(e);
		Visitor v = e.getVisitor();
		for(Zona z : zonasQuePertenece(e.getPosicion().getX(), e.getPosicion().getY(), e.getPosicion().getHeight(), e.getPosicion().getWidth()))
			for(Entidad ent : z.getEntidades())
				if (ent != e && colisionan(ent, e)) {
					ent.accept(v);
				}
	}
	
	private boolean colisionan(Entidad e1, Entidad e2) {
		return e1.getEntidadGrafica().getBounds().intersects(e2.getEntidadGrafica().getBounds());
	}

	public boolean movimientoPosible(int x, int y, int height, int width) {
		return zonas[x/32][y/32] != null && zonas[(x + width - 1)/32][y/32] != null && zonas[x/32][(y + height - 1)/32] != null && zonas[(x + width - 1)/32][(y + height - 1)/32] != null;	
	}

	public void setImagenLaberinto(String imagen) {
		gui.setImagenLaberinto(imagen);		
	}
	
	public void setVelocidadProtagonista(int velocidad) {
		velocidadProtagonista = velocidad;
	}
	
	public void setVelocidadEnemigos(int velocidad) {
		velocidadEnemigos = velocidad;
	}

	public int getVelocidadProtagonista() {
		return velocidadProtagonista;
	}
	
	public int getVelocidadEnemigos() {
		return velocidadEnemigos;
	}
	
	public int getCantEntidades() {
		return cantEntidades;
	}
	
}