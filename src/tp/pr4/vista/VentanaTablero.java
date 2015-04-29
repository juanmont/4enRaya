package tp.pr4.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;
import tp.pr4.logica.Ficha;
/**
 * Panel que agrupa los paneles del tablero
 */
public class VentanaTablero extends JPanel {
	//Componentes
	private VistaTablero tablero;
	private JLabel label;
	private ViewController control;
	
	/**
	 * Constructor de la clase
	 * @param control - Controlador de la vista de la ventana
	 */
	public VentanaTablero(ViewController control){
		this.control = control;
		tablero = new VistaTablero(control, 7,6);
		//Texto con la informacion de a quien toca poner la ficha 
		label = new JLabel("Turno Blancas", (int) (CENTER_ALIGNMENT));
		//Tipo de letra
		label.setFont(new Font("Arial black", 15,15));
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(153,217,234));
		
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setPreferredSize(new Dimension(20,50));
		//Añadimos las componentes al panel
		this.add(tablero, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
	}
	
	/**
	 * Actualizamos el turno de label
	 * @param turno - String con el turno en el que estamos
	 */
	public void actualizaTurno(String turno){
		label.setText("Turno "+ turno);
	}
	
	/**
	 * Devuelve el tablero a su estado inicial
	 * @param x - Dimesion horizontal
	 * @param y - Dimesion vertical
	 */
	public void reset(int x,int y){
		this.removeAll();
		tablero = new VistaTablero(control, x, y);
		this.add(tablero, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);
		label.setText("Juegan Blancas");
	}
	
	/**
	 * Colorea la ficha en la matriz del  tablero
	 * @param x - Posicion horizontal
	 * @param y - Posicion vertical
	 * @param color - Color de la ficha
	 */
	public void cambiaCasilla(int x,int y, Ficha color){
		tablero.cambiaCasilla(x, y, color);
	}
}
