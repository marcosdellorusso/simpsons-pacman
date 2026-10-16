package logica;

public class RelojProtagonista extends Thread{
	
	protected Logica logica;
	protected int velocidad;
	protected boolean enCurso;
	
	public RelojProtagonista(Logica logica) {
		this.logica = logica;
		enCurso = false;
		velocidad = 30;
	}
	
	public void detener() {
		enCurso = false;
	}	

	public void start() {
		enCurso = true;
		super.start();
	}
	
	@Override
	public void run() {
		while(enCurso) {
			try {
				Thread.sleep(velocidad);
				logica.moverProtagonista();
			}
			catch (InterruptedException e) {}
		}
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
}