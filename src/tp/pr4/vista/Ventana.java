package tp.pr4.vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.control.ViewController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.TipoJuego;
import tp.pr4.observers.ObserverPartida;
/**
 * Ventana principal sobre la que iran el resto de componentes
 */
public class Ventana extends JFrame implements ObserverPartida {
	private static final long serialVersionUID = 1L;
	
	private BInferiores botonesInf;
	private PanelEste panelEste;
	private VentanaTablero ventanaTab;


public Ventana(final ViewController control){
	//Panel de la ventana
	this.getContentPane();
	this.setLayout(new BorderLayout());
	
	botonesInf = new BInferiores(control);
	panelEste = new PanelEste(control);
	ventanaTab = new VentanaTablero(control);
	//Pone en modo visible los componentes de la ventana
	ventanaTab.setVisible(true);
	panelEste.setVisible(true);
	botonesInf.setVisible(true);
	//A�ade todos los componentes a la ventana
	this.add(botonesInf, BorderLayout.SOUTH);
	this.add(panelEste, BorderLayout.EAST);
	this.add(ventanaTab, BorderLayout.CENTER);
	
	this.setBackground(new Color(153,217,234));
	this.pack();
	this.setLocation(100,100);
	this.setSize(850, 700);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	 //Le paso el controlador al observado, que es la propia ventana
	control.addObserver(this);
	control.creaTablero();
	}

/**
 * Lanza un optionPane informando de que la partida ha terminado
 */
@Override
public void onTerminada() {
	JOptionPane.showMessageDialog(this,"Partida Terminada", "FIN", JOptionPane.INFORMATION_MESSAGE);
	inhabilitaBotones();
}

/**
 * Hace un reset de la partida y lanza un optionPane informando de que se ha reiniciado la partida
 */
@Override
public void onReset(int x, int y) {
	ventanaTab.reset(x, y);
	habilitaBotones();
	JOptionPane.showMessageDialog(this,"Partida reinicida", "Informacion", JOptionPane.INFORMATION_MESSAGE);
}

/**
 * Lanza un optionPane infromando de que la partida ha termina en tablas, no ha ganado nadie o ha ganado alguien
 */
@Override
public void onGanador(Ficha ganador) {
	if(ganador == Ficha.VACIA)
		JOptionPane.showMessageDialog(this,"Partida en tablas", "No gano nadie", JOptionPane.INFORMATION_MESSAGE);
	else
	JOptionPane.showMessageDialog(this,"Ganan las "+ ganador.toString(), "Has Ganado!!", JOptionPane.INFORMATION_MESSAGE);
	
}

/**
 * Lanza un optionPane de que ha habido un error
 */
@Override
public void onError(String error) {
	JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
}

/**
 * Cambia el JLabel de quien esta jugando
 */
@Override
public void onJuego(TipoJuego juego) {
	panelEste.cambiaLabelJuego(juego.toString());
}

/**
 * Cambia el turno por el del jugado, que esta jugando
 */
@Override
public void onTurno(Ficha turno) {
	ventanaTab.actualizaTurno(turno.toString());
}

/**
 * Inhabilita los botones inferiores y del panel este 
 */
private void inhabilitaBotones(){
	panelEste.in_habilitaBotones(false);
	botonesInf.in_habilitaBotones(false);
}
/**
 * Habilita los botones inferiores y del panel este 
 */
private void habilitaBotones(){
	panelEste.in_habilitaBotones(true);
	botonesInf.in_habilitaBotones(true);
}

/**
 * Actualiza el tablero.
 */
@Override
public void actualizaTablero(TableroInmutable tab) {
	for(int i = 1; i <= tab.getAncho(); i++)
		for(int j = 1; j <= tab.getAlto(); j++)
			ventanaTab.cambiaCasilla(i, j, tab.getCasilla(i, j));
}

/**
 * Inicia el tablero haciendo un reset y cambiando el jugador que estaba jugando a blancas.
 */
@Override
public void onIniciaTablero(int x,int y, TipoJuego juego) {
	ventanaTab.reset(x,y);
	panelEste.cambiaLabelJuego(juego.toString());
}

}
