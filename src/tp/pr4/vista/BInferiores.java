package tp.pr4.vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;
import tp.pr4.logica.MovimientoInvalido;

public final class BInferiores extends JPanel{

	private static final long serialVersionUID = 1L;
	private JButton aleatorio;
	private JButton salir;
	private ViewController control;
	
	
	public BInferiores(final ViewController control) {
		this.control = control;
		
		FlowLayout FlowLayout1 = new FlowLayout();
		
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
		aleatorio.setPreferredSize(new Dimension(180,40));
		salir = new JButton("Salir", new ImageIcon("Fotos/power1.png"));
		salir.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						System.exit(0);
					}
					
				});
		salir.setPreferredSize(new Dimension(100,40));
		
		this.setLayout(FlowLayout1);
		FlowLayout1.setHgap(50);
		FlowLayout1.getAlignment();
		
		this.setBackground(new Color(153,217,234));
		aleatorio.setBackground(new Color(127,127,127));
		salir.setBackground(new Color(127,127,127));
		this.add(aleatorio);
		this.add(salir);
		
		this.setVisible(true);
	}


	public void inhabilitaBotones() {
		aleatorio.setEnabled(false);
	}
}
