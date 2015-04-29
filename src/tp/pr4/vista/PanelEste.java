package tp.pr4.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;
/**
 * Panel para mejorar el dise�o en la ventana principal,
 * de de los paneles de vista partida y juego
 */
public class PanelEste extends JPanel {

	private static final long serialVersionUID = 1L;
	private VistaPartida partida;
	private Juego juego;
	private JLabel tipoJuego;
	
	/**
	 * Constructor de la clase
	 * @param control - Controlador de la vista de la ventana
	 */
	public PanelEste(ViewController control) {

		partida = new VistaPartida(control);
		juego = new Juego(control);
		tipoJuego = new JLabel("HOLA", (int) (CENTER_ALIGNMENT));
		tipoJuego.setFont(new Font("Arial black", 15, 15));
		tipoJuego.setVisible(true);
		//Alineamos verticalmente los paneles
		this.setLayout(new GridLayout(3,1));
		//A�adimos los paneles
		this.add(partida);
		this.add(juego);
		this.add(tipoJuego);
		this.setBackground(new Color(153,217,234));
		this.setVisible(true);
	}
	
	/**
	 *Inahbilitamos los paneles de vistaPartida y juego
	 */
	public void in_habilitaBotones(boolean habilita){
		partida.in_habilitaBotones(habilita);
		juego.in_habilitaBotones(habilita);
	}
	
	/**
	 * Cambia el juego en el texto del JLabel
	 * @param tipo - String con el juego al que se esta jugando
	 */
	public void cambiaLabelJuego(String tipo){
		tipoJuego.setText("Estas jugando a: "+ tipo);
	}
}
