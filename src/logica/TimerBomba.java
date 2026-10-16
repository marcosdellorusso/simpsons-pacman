package logica;

import entidades.Bomba;

public class TimerBomba extends Thread{
	
	protected Logica logica;
	protected Bomba bomba;
	
	public TimerBomba(Logica logica, Bomba b) {
		this.logica = logica;
		bomba = b;
	}
	
	public void start() {
		super.start();
	}
	
	@Override
	public void run() {
		try {			
			int duracion = 800;			
			boolean terminar = true;
			while (duracion != 0 && terminar) {
				terminar = logica.bombasPlantadas() > 0;
				if (!logica.juegoEnPausa()) {
					duracion--;
					Thread.sleep(1);
				}
			}
			while (terminar && !logica.explotarBomba(bomba)) {		
				terminar = logica.bombasPlantadas() > 0;
			}
			logica.explotarBomba(bomba);
		}catch (InterruptedException e) {}
		
		
	}
	
}