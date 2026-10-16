package datos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Datos {
	
	private String ruta = "./src/datos/ranking.txt";
	
	public Datos() {
		File archivo = new File(ruta);
		if (!archivo.exists()) {
			try {
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarPuntuacion(String nombre, String puntos) {		
		boolean archivoVacio = false;    
		try {
			BufferedReader br = new BufferedReader(new FileReader(ruta)); 
			if (br.readLine() == null)
			    archivoVacio = true;
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileWriter fileWriter = null;		
		try {
			fileWriter = new FileWriter(ruta, true);
			BufferedWriter bfwriter = new BufferedWriter(fileWriter);
			if (!archivoVacio)
				bfwriter.newLine();
			bfwriter.write(puntos+" "+nombre);
			bfwriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String[][] recuperarPuntuaciones(){
		File file = new File(ruta);
		Scanner scanner;
		String[][] toReturn = null;
		String linea[];
		int puntaje = 0;
		String nom;
		Jugador j = null;
		ArrayList<Jugador> ranking = new ArrayList<Jugador>();
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				linea = scanner.nextLine().split(" ");
				puntaje = Integer.parseInt(linea[0]);
				nom = linea[1];
				j = new Jugador(nom, puntaje);
				ranking.add(j);
			}
			ranking.sort((o1, o2) -> o1.compareTo(o2));
			
			toReturn = new String[ranking.size()][2];
			int i = 0;
			while (i < ranking.size()) {
				linea = ranking.get(i).toString().split(" ");
				toReturn[i][0] = linea[0];
				toReturn[i][1] = linea[1];
				i++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
	public void reiniciarPuntuaciones() {
		File archivo = new File(ruta);
		if (archivo.exists()) {
			try {
				archivo.delete();
				archivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
