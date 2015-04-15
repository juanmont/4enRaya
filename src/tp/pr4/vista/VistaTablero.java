package tp.pr4.vista;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr4.control.ViewController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;

public class VistaTablero extends JPanel{
	private JButton [][]mBotones;
	private ViewController control;
		
	public VistaTablero(final ViewController control, final int x, final int y){
		super();
		this.control = control;
		mBotones = new JButton[x][y];
		this.setLayout(new GridLayout(y,x));
		for(int i = 0 ; i < y; i++) {
			for(int j = 0; j < x; j++) {
				JButton botonTab = new JButton();
				botonTab.setBackground(Color.gray);
				botonTab.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent e){
							try {
								for(int i = 0 ; i < y; i++) {
									for(int j = 0; j < x; j++) {
										if(e.getSource() == mBotones[j][i])
											control.poner(j+1, i+1);
											
									}
								}
							} catch (MovimientoInvalido e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
							}
							}
						});
				mBotones[j][i] = botonTab;
				this.add(botonTab);
			}
		}
		this.setVisible(true);
	}
	public void cambiaCasilla(int x, int y, Ficha color){
		switch(color){
		case BLANCA:
			/*ImageIcon fotB = new ImageIcon("images/Ficha_Blanca.png");
			ImageIcon iconoEscalaB = new ImageIcon(fotB.getImage().getScaledInstance(this.getHeight(), this.getWidth(), Image.SCALE_DEFAULT));
			this.setIcon(iconoEscalaB);*/
			mBotones[x-1][y-1].setBackground(Color.white);
			break;
		case NEGRA:
			/*ImageIcon fotN = new ImageIcon("images/Ficha_Negra.png");
			ImageIcon iconoEscalaN = new ImageIcon(fotN.getImage().getScaledInstance(this.getHeight(), this.getWidth(), Image.SCALE_DEFAULT));
			this.setIcon(iconoEscalaN);*/
			mBotones[x-1][y-1].setBackground(Color.black);
			break;
		case VACIA:
			/*ImageIcon fotV = new ImageIcon("images/Ficha_Vacia.png");
			ImageIcon iconoEscalaV = new ImageIcon(fotV.getImage().getScaledInstance(this.getHeight(), this.getWidth(), Image.SCALE_DEFAULT));
			this.setIcon(iconoEscalaV);*/
			mBotones[x-1][y-1].setBackground(Color.gray);
			break;
		}	
	}

}
