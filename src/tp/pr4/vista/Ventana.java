package tp.pr4.vista;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import tp.pr4.control.ViewController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TipoJuego;
import tp.pr4.observers.ObserverPartida;

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
	ventanaTab.setVisible(true);
	panelEste.setVisible(true);
	botonesInf.setVisible(true);
	//Botones inferiores 
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


@Override
public void onTerminada() {
	JOptionPane.showMessageDialog(this,"Partida Terminada", "FIN", JOptionPane.INFORMATION_MESSAGE);
	inhabilitaBotones();
}


@Override
public void onReset(int x, int y) {
	ventanaTab.reset(x, y);
	JOptionPane.showMessageDialog(this,"Partida reinicida", "Informacion", JOptionPane.INFORMATION_MESSAGE);
}


@Override
public void onGanador(Ficha ganador) {
	if(ganador == Ficha.VACIA)
		JOptionPane.showMessageDialog(this,"Partida en tablas", "No gano nadie", JOptionPane.INFORMATION_MESSAGE);
	else
	JOptionPane.showMessageDialog(this,"Ganan las "+ ganador.toString(), "Has Ganado!!", JOptionPane.INFORMATION_MESSAGE);
	
}


@Override
public void onError(String error) {
	JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
}


@Override
public void onJuego(TipoJuego juego) {
	panelEste.cambiaLabelJuego(juego.toString());
}


@Override
public void onTurno(Ficha turno) {
	ventanaTab.actualizaTurno(turno.toString());
}


@Override
public void onMuestraTablero(String Tablero) {
	//No Hace nada, es para la ventana de consola
}

private void inhabilitaBotones(){
	panelEste.inhabilitaBotones();
	botonesInf.inhabilitaBotones();
}


@Override
public void actualizaTablero(Tablero tab) {
	for(int i = 1; i <= tab.getAncho(); i++)
		for(int j = 1; j <= tab.getAlto(); j++)
			ventanaTab.cambiaCasilla(i, j, tab.getCasilla(i, j));
}


@Override
public void onIniciaTablero(int x,int y, TipoJuego juego) {
	ventanaTab.reset(x,y);
	panelEste.cambiaLabelJuego(juego.toString());
}

}
