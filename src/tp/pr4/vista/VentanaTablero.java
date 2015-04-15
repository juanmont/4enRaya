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

public class VentanaTablero extends JPanel {
	
	private VistaTablero tablero;
	private JLabel label;
	private ViewController control;
	
	public VentanaTablero(ViewController control){
		this.control = control;
		tablero = new VistaTablero(control, 7,6);
		label = new JLabel("Turno Blancas", (int) (CENTER_ALIGNMENT));
		label.setFont(new Font("Arial black", 15,15));
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(153,217,234));
		
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setPreferredSize(new Dimension(20,50));
		this.add(tablero, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setVisible(true);
	}
	
	public void actualizaTurno(String turno){
		label.setText("Turno "+ turno);
	}
	
	public void reset(int x,int y){
		this.removeAll();
		tablero = new VistaTablero(control, x, y);
		this.add(tablero, BorderLayout.CENTER);
		this.add(label, BorderLayout.SOUTH);
		label.setText("Juegan Blancas");
	}
	
	public void cambiaCasilla(int x,int y, Ficha color){
		tablero.cambiaCasilla(x, y, color);
	}
}
