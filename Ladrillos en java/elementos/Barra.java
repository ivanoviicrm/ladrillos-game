package elementos;

import java.awt.Color;

public class Barra extends Elemento {

	// ATRIBUTOS
	private int velocidad;
	
	// CONSTRUCTOR
	public Barra(int x, int y, int ancho, int alto, Color color) {
		super(x, y, ancho, alto, color);
		velocidad = 20;
	}

	// SETTER & GETTERS
	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
		
}
