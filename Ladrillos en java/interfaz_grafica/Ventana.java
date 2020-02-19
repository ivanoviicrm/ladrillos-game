package interfaz_grafica;

import javax.swing.JFrame;

public class Ventana extends JFrame {

	// ATRIBUTOS
	private static final long serialVersionUID = 1L;
	private int ancho;
	private int alto;
	private Lamina lamina_principal;
	
	public Ventana() {
		ancho = 506;
		alto = 529;
		lamina_principal = new Lamina(ancho - 6, alto - 29);
		add(lamina_principal);
		setTitle("Ladrillos");
		setSize(ancho, alto);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
