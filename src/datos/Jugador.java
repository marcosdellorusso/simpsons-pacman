package datos;

public class Jugador {
	//Atributos instancia
	private String nombre;
	private int puntaje;
	
	//Constructor
	public Jugador(String n, int p) {
		nombre = n;
		puntaje = p;
	}
	
	public String obtenerNombre() {
		return nombre;
	}
	
	public int obtenerPuntaje() {
		return puntaje;
	}

	public int compareTo(Jugador o1) {
		if (puntaje <= o1.obtenerPuntaje())
				return 1;
		else
			return -1;
	}
	
	public String toString() {
		return puntaje + " " + nombre;
	}
}
