package entidades;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class EntidadGrafica extends JLabel {
	//Atributos de instancia
	protected PosicionAbstracta posicion;
	//Constructor
	public EntidadGrafica(String urlImagen, PosicionAbstracta posicion) {
		this.posicion = posicion;
		this.setIcon(new ImageIcon(EntidadGrafica.class.getResource(urlImagen)));
		this.setBounds(posicion.getX(), posicion.getY(), posicion.getWidth(), posicion.getHeight());
		this.setVisible(true);
	}

	public void setImagen(String url) {
		this.setIcon(new ImageIcon(EntidadGrafica.class.getResource(url)));
	}
	
	public void actualizarPosicion() {
		this.setBounds(this.posicion.getX(), this.posicion.getY(), this.getWidth(), this.getHeight());
	}
}
