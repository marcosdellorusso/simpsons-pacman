package entidades;

public class Posicion extends PosicionAbstracta{
	//Constructor
	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Metodos
	public void setX(int x) {
		this.x = x;
	}
		
	public void setY(int y) {
		this.y = y;
	}
		
	public void setHeight(int height) {
		this.height = height;
	}
		
	public void setWidth(int width){
		this.width = width;
	}
}
