package tp.pr4.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;
/**
 * Clase en la que se implementan los botones del panel Partida
 */
public class VistaPartida extends JPanel {

	private static final long serialVersionUID = 1L;
	//Componenetes
	private JButton deshacer;
	private JButton reiniciar;
	private ViewController control;
	
	/**
	 * Constructor del panel 
	 * @param control - Controlador de la vista de la ventana
	 */
	public VistaPartida(final ViewController control) {
		this.control = control;
		this.setBackground(new Color(153,217,234));
		FlowLayout FlowLayout1 = new FlowLayout();
		//Boton para deshacer un movimiento
		deshacer = new JButton("deshacer", new ImageIcon("Fotos/undo.png"));
		deshacer.setBackground(new Color(127,127,127));
		//Boton para reiniciar la partida
		reiniciar = new JButton("reiniciar", new ImageIcon("Fotos/restart.png"));
		reiniciar.setBackground(new Color(127,127,127));
		//Oyente para deshacer un movimiento
		deshacer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						control.deshacer();
					}
					
				});
		//Oyente para reiniciar la partida
		reiniciar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						control.reiniciar();
					}
					
				});
		//Dimensions de los botones
		deshacer.setPreferredSize(new Dimension(150,50));
		reiniciar.setPreferredSize(new Dimension(150,50));
		
		this.setLayout(FlowLayout1);
		//Destancia entre botones
		FlowLayout1.setHgap(10);
		
		this.setBorder(BorderFactory.createTitledBorder("Partida"));
		//A�ade los botones al panel
		this.add(deshacer);
		this.add(reiniciar);
		
		this.setVisible(true);
	}
	
	/**
	 * Inhabilita los dos botones.
	 */
	public void in_habilitaBotones(boolean habilita) {
		deshacer.setEnabled(habilita);
	}
}
