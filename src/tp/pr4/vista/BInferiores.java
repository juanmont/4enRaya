package tp.pr4.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;
import tp.pr4.logica.MovimientoInvalido;
/**
 * En esta clase se dise�an los dos botnes inferiores 
 */
public final class BInferiores extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton aleatorio;
	private JButton salir;
	private ViewController control;
	
	/**
	 * Implementamos los dos botones inferiores de la ventana principal.
	 * @param control - Controlador de la vista de la ventana
	 */
	public BInferiores(final ViewController control) {
		this.control = control;
		//Lo usamos para colocar los botones con separacion y de forma centrada
		FlowLayout FlowLayout1 = new FlowLayout();
		//Boton para poner una ficha aleatoria 
		aleatorio = new JButton("Poner Aleatorio", new ImageIcon("Fotos/random.png"));
		aleatorio.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						try {
							control.ponerAleatorio();
						} catch (MovimientoInvalido e1) {
							JOptionPane.showMessageDialog(aleatorio, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					
				});
		//Dimensiones del boton
		aleatorio.setPreferredSize(new Dimension(180,40));
		
		//Boton para salir del juego
		salir = new JButton("Salir", new ImageIcon("Fotos/power1.png"));
		salir.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					}
					
				});
		//Dimensiones del boton
		salir.setPreferredSize(new Dimension(100,40));
		
		this.setLayout(FlowLayout1);
		//Distancia entre los botones(50)
		FlowLayout1.setHgap(50);
		FlowLayout1.getAlignment();
		
		this.setBackground(new Color(153,217,234));
		aleatorio.setBackground(new Color(127,127,127));
		salir.setBackground(new Color(127,127,127));
		//A�adimos las componentes al panel
		this.add(aleatorio);
		this.add(salir);
		//Ponemos el panel en modo visible
		this.setVisible(true);
	}

	/**
	 * Inhabilitamos solo el boton aleatorio.
	 */
	public void in_habilitaBotones(boolean habilita) {
		aleatorio.setEnabled(habilita);
	}
}
