package tp.pr4.vista;

import java.util.Scanner;

import tp.pr4.commands.GestorComandos;
import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TipoJuego;
import tp.pr4.observers.ObserverPartida;

public class ViewConsole implements ObserverPartida{
	private ConsoleController control;
	private boolean terminada;
	private TipoJuego juego;
	private Ficha turno;
	private GestorComandos gestorCmd;
	private Scanner in;
	private Ficha ganador;
	private String tablero;
	
	public ViewConsole(ConsoleController cont, Scanner in){
		tablero = "";
		control = cont;
		terminada = false;
		turno = Ficha.BLANCA;
		juego = TipoJuego.C4;
		ganador = Ficha.VACIA;
		this.in = in;
		gestorCmd = new GestorComandos(control);
		control.addObserver(this);
	}
	public void run(){
		control.actualizaTablero();
		System.out.println(tablero);
		
		// Comprobar y ejecutara los comandos introducidos hasta que la partida termine. 
		while (!terminada) {
			
			System.out.println("\nJuegan " + turno);
			System.out.print("Qué quieres hacer? ");

			try {
				String comando = in.nextLine();
				if (!gestorCmd.ejecuta(comando,juego,in, turno))
					System.err.println("No te entiendo.");
			} catch (MovimientoInvalido e) {
				System.err.println(e.getMessage());
			} 
			control.actualizaTablero();
			System.out.println(tablero);
			
		}
		
		//Mostrara el ganador o partida en tablas. 
		if (ganador != Ficha.VACIA)
			System.out.println("\nGanan las " + ganador);
		else
			System.out.println("\nPartida terminada en "
					+ ganador);
	}
	@Override
	public void onTerminada() {
		this.terminada = true;
	}
	@Override
	public void onReset(int x,int y) {
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		System.out.println("Partida reiniciada.");
	}
	@Override
	public void onGanador(Ficha ganador) {
		this.ganador = ganador;
	}
	@Override
	public void onError(String error) {
		System.err.println(error);
	}
	@Override
	public void onMuestraTablero(String tablero) {
		this.tablero = tablero;
	}

	@Override
	public void onJuego(TipoJuego juego) {
		this.juego = juego;
	}
	@Override
	public void onTurno(Ficha turno) {
		this.turno = turno;
	}
	@Override
	public void actualizaTablero(Tablero tab) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onIniciaTablero(int x, int y, TipoJuego juego) {
		// TODO Auto-generated method stub
		
	}
}
