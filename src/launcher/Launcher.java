package launcher;

import gui.Gui;
import logica.Logica;

public class Launcher {

	public static void main(String[] args) {
		Logica logica = new Logica();
		Gui gui = new Gui(logica);
		logica.setGui(gui);
		gui.setVisible(true);
	}

}
