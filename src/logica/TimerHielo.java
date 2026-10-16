package logica;
import entidades.Hielo;

public class TimerHielo extends Thread{
	
	protected Hielo hielo;
	protected int duracion;
	protected Logica logica;
	
	public TimerHielo(Hielo hielo, int duracion, Logica logica) {
		this.hielo = hielo;
		this.duracion = duracion;
		this.logica = logica;
	}
	
	public void start() {
		super.start();
	}
	
	@Override
	public void run() {
		try {
			boolean terminar = true;
			while (duracion != 0 && terminar) {
				terminar = logica.enemigosCongelados();
				if (!logica.juegoEnPausa()) {
					duracion--;
					Thread.sleep(1);
				}
			}
			while (terminar && !logica.descongelarEnemigos()) {	
				terminar = logica.enemigosCongelados();
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
}