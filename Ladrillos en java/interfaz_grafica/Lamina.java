package interfaz_grafica;

import elementos.Barra;
import elementos.Bola;
import elementos.Ladrillo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Lamina extends JPanel implements ActionListener, KeyListener {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	public static int ancho;
	public static int alto;
	private Timer timer = new Timer(100, this);
	public static Barra barra;
	private Bola bola;
	private boolean primer_movimiento;
	public static boolean gameover = false;
	public static CopyOnWriteArrayList<Ladrillo> ladrillos;
	
	// CONSTRUCTOR
	public Lamina(int ancho, int alto) {
		Lamina.ancho = ancho;
		Lamina.alto = alto;
		setFocusable(true);
		setBackground(Color.BLACK);
		barra = new Barra(ancho/2-20, 465, 40, 5, Color.WHITE); // BARRA NORMAL
//		barra = new Barra(10, 465, 480, 5, Color.WHITE); // BARRA TESTER
		bola = new Bola(barra.getX()+barra.getAncho()/2-2, barra.getY()-barra.getAlto()-2,5,5,Color.CYAN);
		primer_movimiento = false;
		ladrillos = new CopyOnWriteArrayList<Ladrillo>();
		crear_y_anadir_ladrillos();
		timer.start();
		addKeyListener(this);
	}
	
	// METODO PARA CREAR LOS LADRILLOS (dentro del area marcada)
	public void crear_y_anadir_ladrillos() {
		
		int x = 55;
		int y = 51;
		int ancho = 47;
		int alto = 10;
		
		
		// Creo ladrillos
		for (int i = 0; i < 49; i++) {
			int golpes = (int) (Math.random()*3)+1;
			Ladrillo nuevo = new Ladrillo(x, y, ancho, alto, Color.YELLOW, golpes);
			ladrillos.add(nuevo);
		}
		
		// Coloco ladrillos
		int posicion_X = 55;
		int posicion_Y = 55;
		int limite_X = 450;
		for (Ladrillo l: ladrillos) {
			// Si se pasa del limite agrego una fila abajo
			if (posicion_X >= limite_X - ancho) {
				posicion_X = 55;
				posicion_Y += alto + 10;
			}
			
			// Pongo la posicion al ladrillo
			l.setXY(posicion_X, posicion_Y);
			posicion_X += ancho + 10;
			
			// Los coloreo
			if (l.getGolpes_para_romperlo() == 1)
				l.setColor(Color.DARK_GRAY);
			else if (l.getGolpes_para_romperlo() == 2)
				l.setColor(Color.ORANGE);
			else
				l.setColor(Color.RED);
		}
	}
	
	// PINTAR (Y REPINTAR)
	public void paint(Graphics g) {
		super.paint(g);
		
		// Pinto la linea de limite
		g.setColor(Color.GRAY);
		g.drawLine(0, 470, 500, 470);
	
		// Pinto la barra
		g.setColor(barra.getColor());
		g.fillRect(barra.getX(), barra.getY(), barra.getAncho(), barra.getAlto());
		
		// Pinto la pelota
		g.setColor(bola.getColor());
		g.fillOval(bola.getX(), bola.getY(), bola.getAncho()+2, bola.getAlto()+2);
		
		// Pinto la zona de los ladrillos
		g.setColor(Color.DARK_GRAY);
		g.drawRect(50, 50, 400, 140);
		
		// Pinto los ladrillos
		for (Ladrillo l: ladrillos) {
			if (l.isRoto() == false) {
				g.setColor(l.getColor());
				g.fillRect(l.getX(), l.getY(), l.getAncho(), l.getAlto());
			} else {
				
			}
		}
		
	}

	// ACCION REALIZADA POR CADA TICK DEL RELOJ (TIMER)
	public void actionPerformed(ActionEvent e) {
		if (!gameover) {
			if (primer_movimiento)
				bola.mover();
			repaint();
		} else {
			timer.stop();
			System.out.println("gameover");
		}
		
	}

	// ACCION REALIZADA AL PULSAR TECLAS
	public void keyPressed(KeyEvent e) {
		char key = e.getKeyChar();
		
		if (key == 'a') {
			barra.setX(barra.getX()-barra.getVelocidad());
			if (primer_movimiento == false) {
				bola.setIzquierda(true);
				primer_movimiento = true;
			}
		}
		if (key == 'd') {
			barra.setX(barra.getX()+barra.getVelocidad());
			if (primer_movimiento == false) {
				bola.setDerecha(true);
				primer_movimiento = true;
			}
		}
		
		repaint();
	}

	public void keyReleased(KeyEvent e) {
		// No lo uso
	}

	public void keyTyped(KeyEvent e) {
		// No lo uso
	}
	
}
