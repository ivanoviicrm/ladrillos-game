package elementos;

import java.awt.Color;

public class Ladrillo extends Elemento {

	// ATRIBUTOS
	private int golpes_para_romperlo;
	private boolean roto;
	
	// CONSTRUCTOR
	public Ladrillo(int x, int y, int ancho, int alto, Color color, int golpes_para_romperlo) {
		super(x, y, ancho, alto, color);
		this.golpes_para_romperlo = golpes_para_romperlo;
		roto = false;
	}

	// SETTERS & GETTERS
	public int getGolpes_para_romperlo() {
		return golpes_para_romperlo;
	}

	public void setGolpes_para_romperlo(int golpes_para_romperlo) {
		this.golpes_para_romperlo = golpes_para_romperlo;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isRoto() {
		return roto;
	}

	public void setRoto(boolean roto) {
		this.roto = roto;
	}
}
