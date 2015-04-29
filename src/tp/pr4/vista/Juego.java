package tp.pr4.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr4.control.ViewController;
import tp.pr4.logica.TipoJuego;
/**
 * En esta clase se implementa el panel de Cambio de juego, que mostrara los tipos de juegos y
 * podra elegir redimensionar el tablero en el caso de jugar a Gravity.
 */
public class Juego extends JPanel {
	
	private JComboBox box;
	private JButton cambiar;
	private JLabel lFilas;
	private JLabel lColumnas;
	private JPanel panelInterno;
	private JTextField tFilas;
	private JTextField tColumnas;
	private ViewController control;
	
	/**
	 * Constructor al cual le pasamos el controlador de la ventana
	 */
	public Juego(final ViewController control) {
		this.control = control;
		//Etiqueta con la descripcion del panel
		this.setBorder(BorderFactory.createTitledBorder("Cambio de juego"));
		this.setBackground(new Color(153,217,234));
		//Tipos de juego
		String[] juego = {"Conecta4", "Complica", "Gravity" };
		
		//Panel con los tipos de juego, si el juego es gravity habilita la posiblidad de cambiar el tama�o del tablero
		box = new JComboBox(juego);
		box.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String tipo = box.getSelectedItem().toString();
						if(tipo.equalsIgnoreCase("Gravity"))
							panelInterno.setVisible(true);
						else panelInterno.setVisible(false);
					}
					
				});
		//Boton con imagen, para aceptar el cambio de juego y comprobar si se ha cambiado el tama�o del tablero
		cambiar = new JButton("Cambiar", new ImageIcon("Fotos/tick.png"));
		cambiar.setBackground(new Color(127,127,127));
		cambiar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						String tipo = box.getSelectedItem().toString();
						if(tipo.equalsIgnoreCase("Gravity")){
							if(tFilas.getText() != "" && tColumnas.getText() != ""){
								try{
									control.cambiaJuego(TipoJuego.GR,Integer.parseInt(tColumnas.getText()), Integer.parseInt(tFilas.getText()));
									tColumnas.setText("");
									tFilas.setText("");
								}catch(NumberFormatException exc){
									JOptionPane.showMessageDialog(cambiar, "Debe introducir números.");
									tColumnas.setText("");
									tFilas.setText("");
								}
							}
							else
								JOptionPane.showMessageDialog(cambiar, "Introduce parametros para el tablero");
						}
							else control.cambiaJuego(TipoJuego.fromParam(tipo),0, 0);
					}
					
				});
		
		lFilas = new JLabel("Filas");
		tFilas = new JTextField(5);
		lColumnas = new JLabel("Columnas");
		tColumnas = new JTextField(5);
		
		//Panel para las JLabel
		panelInterno = new JPanel();
		panelInterno.setBackground(new Color(153,217,234));
		panelInterno.add(lFilas);
		panelInterno.add(tFilas);
		panelInterno.add(lColumnas);
		panelInterno.add(tColumnas);
		panelInterno.setVisible(false);
		box.setPreferredSize(new Dimension(150,20));
		cambiar.setPreferredSize(new Dimension (20,40));
		this.setLayout(new BorderLayout());
		//A�adimos las componentes en su posicion correspondiente
		this.add(box, BorderLayout.NORTH);
		this.add(panelInterno, BorderLayout.CENTER);
		this.add(cambiar, BorderLayout.SOUTH);
		
		this.setVisible(true);
	}
	
	/**
	 * Inhabilita el boton de este panel
	 */
	public void in_habilitaBotones(boolean habilita) {
		cambiar.setEnabled(habilita);
	}
}
