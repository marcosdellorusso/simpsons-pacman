package logica;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import entidades.Entidad;

public class Zona {
	//Atributos de instancia
	private Set<Entidad> entidades;
	
	//Constructor
	public Zona() {
		entidades = new HashSet<Entidad>(); 
	}
	
	//Metodos
	public Iterable<Entidad> getEntidades(){
		Iterator<Entidad> it = entidades.iterator();
		List<Entidad> lista = new LinkedList<Entidad>();
		
		while(it.hasNext()) {
			lista.add(it.next());
		}
		
		return lista;
	}
	
	public void setEntidad(Entidad e) {
		entidades.add(e);
	}
	
	public void eliminarEntidad(Entidad e) {
		entidades.remove(e);
	}
	
	
}
