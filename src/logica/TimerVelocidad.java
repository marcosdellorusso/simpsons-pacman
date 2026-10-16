package logica;
import entidades.Velocidad;

public class TimerVelocidad extends Thread{
	
	protected Velocidad velocidad;
	protected int duracion;
	protected Logica logica;
	
	public TimerVelocidad(Velocidad velocidad, int duracion, Logica logica) {
		this.velocidad = velocidad;
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
				terminar = logica.protagonistaConVelocidad();
				if (!logica.juegoEnPausa()) {
					duracion--;
					Thread.sleep(1);
				}
			}
			while (terminar && !logica.desactivarPocionVelocidad()) {			
				terminar = logica.protagonistaConVelocidad();
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
}