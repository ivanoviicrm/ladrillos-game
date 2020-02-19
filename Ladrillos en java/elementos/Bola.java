package elementos;

import java.awt.Color;

import interfaz_grafica.Lamina;

public class Bola extends Elemento {
	
	// ATRIBUTOS
	private boolean arriba;
	private boolean abajo;
	private boolean izquierda;
	private boolean derecha;
	private int velocidad;
	
	
	// CONSTRUCTOR
	public Bola(int x, int y, int ancho, int alto, Color color) {
		super(x, y, ancho, alto, color);
		arriba = true;
		abajo = false;
		izquierda = false;
		derecha = false;
		velocidad = 10;
	}

	// METODO PARA MOVER LA BOLA
	public void mover() {
		// Movimiento normal de la pelota
		if (arriba)
			this.y -= velocidad;
		if (abajo)
			this.y += velocidad;
		if (izquierda)
			this.x -= velocidad;
		if (derecha)
			this.x += velocidad;
		
		// Rebotes de la pelota
		if (this.x <= 0 && arriba && izquierda) {
			izquierda = false;
			derecha = true;
		}
		if (this.x <= 0 && abajo && izquierda) {
			izquierda = false;
			derecha = true;
		}
		if (this.y <= 0 && arriba) {
			arriba = false;
			abajo = true;
		}
		if (this.x >= (Lamina.ancho - this.ancho) && derecha) {
			derecha = false;
			izquierda = true;
		}
		if (this.y >= 470 && abajo) {
			abajo = false;
			Lamina.gameover = true;
			
		}
		// Rebote con la barra (jugador)
		if (this.x >= Lamina.barra.getX() && this.x <= Lamina.barra.getX()+Lamina.barra.getAncho()+5
			&& this.y >= Lamina.barra.getY()-alto*2)
		{
			if (derecha) {
				abajo = false;
				arriba = true;
			}
		}
		
		// Rebote contra ladrillos
		for (Ladrillo l: Lamina.ladrillos) {
			if (l.isRoto() == false) {
				int anchoL = l.getAncho() +3;
				int altoL = l.getAlto() +3;
				int xL = l.getX();
				int yL = l.getY();
				
				if (x > xL && x < xL+anchoL) {
					if (y > yL && y < yL+altoL) {
						if (arriba) {
							arriba = false;
							abajo = true;
						}
						if (abajo) {
							abajo = false;
							arriba = true;
						}
						if (izquierda) {
							izquierda = false;
							derecha = true;
						}
						if (derecha) {
							derecha = false;
							izquierda = true;
						}
						l.setRoto(true);
					}
				}
			}
		}
	}
	
	// SETTERS & GETTERS
	public boolean isArriba() {
		return arriba;
	}

	public void setArriba(boolean arriba) {
		this.arriba = arriba;
	}

	public boolean isAbajo() {
		return abajo;
	}

	public void setAbajo(boolean abajo) {
		this.abajo = abajo;
	}

	public boolean isIzquierda() {
		return izquierda;
	}

	public void setIzquierda(boolean izquierda) {
		this.izquierda = izquierda;
	}

	public boolean isDerecha() {
		return derecha;
	}

	public void setDerecha(boolean derecha) {
		this.derecha = derecha;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


}
