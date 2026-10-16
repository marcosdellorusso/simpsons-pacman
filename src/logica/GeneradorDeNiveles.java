package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import entidades.Bomba;
import entidades.Fruta;
import entidades.Hielo;
import entidades.PacDot;
import entidades.PowerPellet;
import entidades.Velocidad;
import entidades.Vida;

public class GeneradorDeNiveles{
	
	private int nivelSiguiente;
	
	public GeneradorDeNiveles(){
		nivelSiguiente = 1;
	}
	
	public void inicializarNivelSiguiente(Laberinto lab) {
		Fruta fruta = new Fruta(384,416, 500);
		switch(nivelSiguiente) {
		case 1:
			setNivel(lab, "./src/logica/nivel1.txt");
			lab.setVelocidadProtagonista(30);
			lab.setVelocidadEnemigos(33);
			lab.setImagenLaberinto("/imagenes/laberintoNivel1.png");
			fruta.getEntidadGrafica().setImagen("/imagenes/frutaNivel1Pizza32x32.png");
			lab.setEntidad(fruta);
			break;
		case 2:
			setNivel(lab, "./src/logica/nivel2.txt");
			lab.setVelocidadProtagonista(27);
			lab.setVelocidadEnemigos(30);
			lab.setImagenLaberinto("/imagenes/laberintoNivel2.png");
			fruta.getEntidadGrafica().setImagen("/imagenes/frutaNivel2Hamburguesa32x32.png");
			lab.setEntidad(fruta);
			break;
		case 3:
			setNivel(lab, "./src/logica/nivel3.txt");
			lab.setVelocidadProtagonista(23);
			lab.setVelocidadEnemigos(26);
			lab.setImagenLaberinto("/imagenes/laberintoNivel3.png");
			fruta.getEntidadGrafica().setImagen("/imagenes/frutaNivel3Atun32x32.png");
			lab.setEntidad(fruta);
			break;
		}
		nivelSiguiente++;
	}
	
	private void setNivel(Laberinto lab, String path) {
		File file = new File(path);
		Scanner scanner;
		String linea;
		try {
			scanner = new Scanner(file);
			for(int i=1; i<24; i++) {
				linea = scanner.nextLine();
				for(int k = 1; k<=linea.length(); k++) {
					switch(linea.charAt(k-1)) {
					case 'B':
						lab.setEntidad(new Bomba(k*32, i*32));
						break;
					case 'V':
						lab.setEntidad(new Velocidad(k*32+7, i*32));
						break;
					case 'o':
						lab.setEntidad(new PacDot(k*32+12, i*32+12));
						break;
					case 'O':
						lab.setEntidad(new PowerPellet(k*32+6, i*32+6));
						break;
					case 'H':
						lab.setEntidad(new Hielo(k*32, i*32));
						break;
					case 'L':
						lab.setEntidad(new Vida(k*32, i*32));
						break;
					}					
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
