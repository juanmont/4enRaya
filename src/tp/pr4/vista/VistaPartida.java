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

public class VistaPartida extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton deshacer;
	private JButton reiniciar;
	private ViewController control;
	
	public VistaPartida(final ViewController control) {
		this.control = control;
		this.setBackground(new Color(153,217,234));
		FlowLayout FlowLayout1 = new FlowLayout();
		deshacer = new JButton("deshacer", new ImageIcon("Fotos/undo.png"));
		deshacer.setBackground(new Color(127,127,127));
		reiniciar = new JButton("reiniciar", new ImageIcon("Fotos/restart.png"));
		reiniciar.setBackground(new Color(127,127,127));
		deshacer.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						control.deshacer();
					}
					
				});
		reiniciar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						control.reiniciar();
					}
					
				});
		
		deshacer.setPreferredSize(new Dimension(150,50));
		reiniciar.setPreferredSize(new Dimension(150,50));
		
		this.setLayout(FlowLayout1);
		FlowLayout1.setHgap(10);
		
		this.setBorder(BorderFactory.createTitledBorder("Partida"));
		
		this.add(deshacer);
		this.add(reiniciar);
		
		this.setVisible(true);
	}

	public void inhabilitaBotones() {
		deshacer.setEnabled(false);
		reiniciar.setEnabled(false);
	}
}
