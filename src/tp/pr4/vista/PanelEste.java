package tp.pr4.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;

public class PanelEste extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VistaPartida partida;
	private Juego juego;
	private JLabel tipoJuego;
	
	public PanelEste(ViewController control) {

		partida = new VistaPartida(control);
		juego = new Juego(control);
		tipoJuego = new JLabel("HOLA", (int) (CENTER_ALIGNMENT));
		tipoJuego.setFont(new Font("Arial black", 15, 15));
		tipoJuego.setVisible(true);
		this.setLayout(new GridLayout(3,1));
		this.add(partida);
		this.add(juego);
		this.add(tipoJuego);
		this.setBackground(new Color(153,217,234));
		this.setVisible(true);
	}

	public void inhabilitaBotones() {
		partida.inhabilitaBotones();
		juego.inhabilitaBotones();
	}
	
	public void cambiaLabelJuego(String tipo){
		tipoJuego.setText("Estas jugando a: "+ tipo);
	}
}
