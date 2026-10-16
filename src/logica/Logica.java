package logica;

import entidades.Blinky;
import entidades.Bomba;
import entidades.Clyde;
import entidades.Enemigo;
import entidades.Inky;
import entidades.Pinky;
import entidades.PosicionAbstracta;
import entidades.Protagonista;
import gui.Gui;

public class Logica {
	
	private Gui gui;
	private Protagonista protagonista;
	private Enemigo enemigoBlinky, enemigoInky, enemigoClyde, enemigoPinky;
	private Laberinto laberinto;
	private GeneradorDeNiveles generador;
	private RelojEnemigos relojEnemigos;
	private RelojProtagonista relojProtagonista;
	private boolean partidaEnCurso;
	private boolean partidaEnPausa;
	private boolean enemigosCongelados;
	private int vidasActuales;
	private int vidasIniciales;
	private int puntuacion;
	private int bombasPlantadas;
	private int dificultad;
	
	/**
	 * Constructor
	 */
	public Logica() {
		protagonista = new Protagonista(0,0);
		enemigoBlinky = new Blinky(0,0);
		enemigoInky = new Inky(0,0,enemigoBlinky);
		enemigoClyde = new Clyde(0,0);
		enemigoPinky = new Pinky(0,0);
		partidaEnCurso = false;
		enemigosCongelados = false;
		partidaEnPausa = false;
		vidasActuales = 0;
		vidasIniciales = 3;
		puntuacion = 0;
		dificultad = 1;
	}
	
	public PosicionAbstracta getPosicionProtagonista() {
		return protagonista.getPosicion();
	}
	
	public void moverProtagonista(){
		protagonista.moverse();
		if (laberinto.getCantEntidades() == 5)
			subirNivel();
	}
	
	public void moverEnemigos() {
		enemigoBlinky.moverse();
		enemigoClyde.moverse();
		enemigoInky.moverse();
		enemigoPinky.moverse();
	}
	
	public void cambiarDireccionProtagonista(int direccion) {
		protagonista.setDireccion(direccion);
	}

	public void iniciarJuego() {
		puntuacion = 0;
		vidasActuales = vidasIniciales;
		partidaEnCurso = true;
		partidaEnPausa = false;
		enemigosCongelados = false;
		bombasPlantadas = 0;
		generador = new GeneradorDeNiveles();
		protagonista.setBombas(0);
		protagonista.setPocionVelocidad(false);
		subirNivel();
	}
	
	public void pausarJuego() {
		partidaEnPausa = true;
		relojEnemigos.detener();
		relojProtagonista.detener();
	}
	
	public void reanudarJuego() {
		partidaEnPausa = false;
		relojProtagonista = new RelojProtagonista(this);
		relojProtagonista.setVelocidad(laberinto.getVelocidadProtagonista());
		relojProtagonista.start();
		if (!enemigosCongelados) {
			relojEnemigos = new RelojEnemigos(this);
			relojEnemigos.setVelocidad(laberinto.getVelocidadEnemigos());
			relojEnemigos.start();
		}
	}
	
	public void sumarPuntos(int puntos) {
		this.puntuacion += puntos;
		gui.actualizarPuntos(puntuacion);
	}
	
	private void finalizarJuego() {
		partidaEnCurso = false;
		if (relojProtagonista != null)
			relojProtagonista.detener();
		if (relojEnemigos != null)
			relojEnemigos.detener();
		protagonista.setPocionVelocidad(false);
		enemigosCongelados = false;
		bombasPlantadas = 0;
	}
	
	public void comerProtagonista(){
		if (partidaEnCurso) {
			vidasActuales--;		
			if(vidasActuales == 0)
				finalizarJuego();
			else {
				protagonista.setXY(384, 576);
				protagonista.setDireccion(0);
				enemigoBlinky.setXY(384, 352);
				enemigoInky.setXY(352, 320);
				enemigoClyde.setXY(384, 320);
				enemigoPinky.setXY(416, 320);
				enemigoBlinky.salir();
				enemigoInky.salir();
				enemigoClyde.salir();
				enemigoPinky.salir();
			}
			gui.perderVida();
		}
	}
	
	private void subirNivel() {
		if (relojProtagonista != null)
			relojProtagonista.detener();
		if (relojEnemigos != null)
			relojEnemigos.detener();
		gui.borrarLaberinto();
		laberinto = new Laberinto(gui, this);
		generador.inicializarNivelSiguiente(laberinto);
		protagonista.setDireccion(0);
		protagonista.setXY(384, 576);
		protagonista.setPocionVelocidad(false);
		protagonista.setBombas(0);
		bombasPlantadas = 0;
		enemigoBlinky.setXY(384, 352);
		enemigoInky.setXY(352, 320);
		enemigoClyde.setXY(384, 320);
		enemigoPinky.setXY(416, 320);
		enemigoBlinky.salir();
		enemigoInky.salir();
		enemigoClyde.salir();
		enemigoPinky.salir();
		laberinto.setEntidad(protagonista);
		laberinto.setEntidad(enemigoBlinky);
		laberinto.setEntidad(enemigoInky);
		laberinto.setEntidad(enemigoClyde);
		laberinto.setEntidad(enemigoPinky);
		relojProtagonista = new RelojProtagonista(this);
		relojEnemigos = new RelojEnemigos(this);
		relojProtagonista.setVelocidad(laberinto.getVelocidadProtagonista());
		relojEnemigos.setVelocidad(laberinto.getVelocidadEnemigos());
		int dificultad = this.dificultad;
		this.dificultad = 1;
		setDificultad(dificultad);
		relojProtagonista.start();
		relojEnemigos.start();
	}
	
	public void panico() {
		enemigoBlinky.entrarEnPanico();
		enemigoInky.entrarEnPanico();
		enemigoClyde.entrarEnPanico();
		enemigoPinky.entrarEnPanico();
	}
	
	public void setVidasIniciales(int vidasIniciales) {
		this.vidasIniciales = vidasIniciales;
	}
	
	public int getVidasIniciales() {
		return vidasIniciales;
	}
	
	public void setDificultad(int dificultad) {
		if (partidaEnCurso) {
			switch(dificultad) {
			case 0:
				if (this.dificultad == 1)
					modificarVelocidad(5, 5);
				else if (this.dificultad == 2)
					modificarVelocidad(10, 10);
				break;
			case 1:
				if (this.dificultad == 0)
					modificarVelocidad(-5, -5);
				else if (this.dificultad == 2)
					modificarVelocidad(5, 5);
				break;
			case 2:
				if (this.dificultad == 0)
					modificarVelocidad(-10, -10);
				else if(this.dificultad == 1)
					modificarVelocidad(-5, -5);
				break;
			}
		}
		this.dificultad = dificultad;
	}

	public int getDificultad() {
		return dificultad;
	}
	
	public void setGui(Gui gui) {
		this.gui = gui;		
	}
	
	public void recogerBomba() {
		protagonista.setBombas(protagonista.cantBombas() + 1);
		gui.activarPocionBomba();
	}
	
	public boolean soltarBomba() {
		if(partidaEnCurso && !partidaEnPausa && protagonista.cantBombas() > 0) {
			PosicionAbstracta pos = getPosicionProtagonista();
			Bomba bomba = new Bomba(pos.getX(),pos.getY());
			gui.agregarEntidadGrafica(bomba.getEntidadGrafica());
			protagonista.setBombas(protagonista.cantBombas() - 1);
			bombasPlantadas++;
			TimerBomba timer = new TimerBomba(this, bomba);
			timer.start();
			return true;
		}
		return false;
	}
	
	public boolean explotarBomba(Bomba b) {
		if (!partidaEnCurso)
			return true;
		else if (!partidaEnPausa && bombasPlantadas > 0) {
			PosicionAbstracta posBlinky = enemigoBlinky.getPosicion();
			PosicionAbstracta posInky = enemigoInky.getPosicion();
			PosicionAbstracta posClyde = enemigoClyde.getPosicion();
			PosicionAbstracta posPinky = enemigoPinky.getPosicion();
			int x = b.getPosicion().getX();
			int y = b.getPosicion().getY();
			
			if(x - 32*3 <= posBlinky.getX() && posBlinky.getX() <= x + (32*3) && 
				y - 32*3 <= posBlinky.getY() && posBlinky.getY() <= y + (32*3) ) {
				enemigoBlinky.morir();
			}
			
			if(x- 32*3 <= posInky.getX() && posInky.getX() <= x + (32*3) && 
			   y - 32*3 <= posInky.getY() && posInky.getY() <= y + (32*3) ) {
				enemigoInky.morir();
			}
			
			if(x - 32*3 <= posClyde.getX() && posClyde.getX() <= x + (32*3) && 
			   y - 32*3 <= posClyde.getY() && posClyde.getY() <= y + (32*3) ) {
				enemigoClyde.morir();
			}
			
			if(x - 32*3 <= posPinky.getX() && posPinky.getX() <= x + (32*3) && 
			   y - 32*3 <= posPinky.getY() && posPinky.getY() <= y + (32*3) ) {
				enemigoPinky.morir();
			}			
			b.getEntidadGrafica().setVisible(false);
			return true;
		}else
			return false;
	}
	
	private void modificarVelocidad(int velocidadProtagonista, int velocidadEnemigos) {
		int velProtagonistaFinal = laberinto.getVelocidadProtagonista() + velocidadProtagonista;
		int velEnemigosFinal = laberinto.getVelocidadEnemigos() + velocidadEnemigos;
		relojProtagonista.setVelocidad(velProtagonistaFinal);
		relojEnemigos.setVelocidad(velEnemigosFinal);
		laberinto.setVelocidadProtagonista(velProtagonistaFinal);
		laberinto.setVelocidadEnemigos(velEnemigosFinal);
	}
	
	public void activarPocionVelocidad() {
		if (partidaEnCurso && !partidaEnPausa && !protagonista.tieneVelocidad()) {
			protagonista.setPocionVelocidad(true);
			modificarVelocidad(-10, 0);
			gui.activarPocionVelocidad();
		}
	}
	
	public boolean desactivarPocionVelocidad() {
		if (!partidaEnCurso)
			return true;
		else if (!partidaEnPausa && protagonista.tieneVelocidad()) {
			protagonista.setPocionVelocidad(false);
			modificarVelocidad(12, 0);
			gui.desactivarPocionVelocidad();
			return true;
		}else
			return false;
	}
	
	public boolean protagonistaConVelocidad() {
		return protagonista.tieneVelocidad();
	}

	public void congelarEnemigos() {
		if (partidaEnCurso && !partidaEnPausa && !enemigosCongelados) {
			relojEnemigos.detener();
			gui.activarPocionHielo();
			enemigosCongelados = true;
		}
	}

	public boolean descongelarEnemigos() {
		if (!partidaEnCurso)
			return true;
		else if (!partidaEnPausa && enemigosCongelados) {
			enemigosCongelados = false;
			relojEnemigos = new RelojEnemigos(this);
			relojEnemigos.setVelocidad(laberinto.getVelocidadEnemigos());
			relojEnemigos.start();
			gui.desactivarPocionHielo();
			return true;
		}else
			return false;
	}
	
	public void sumarVida() {
		if (vidasActuales != 4) {
			vidasActuales++;
			gui.ganarVida();
		}		
	}

	public int getDireccionProtagonista() {
		return protagonista.getDireccion();
	}

	public boolean juegoEnPausa() {
		return partidaEnPausa;
	}
	
	public boolean enemigosCongelados() {
		return enemigosCongelados;
	}
	
	public int bombasPlantadas() {
		return bombasPlantadas;
	}
	
	public boolean juegoEnCurso() {
		return partidaEnCurso;
	}
}
