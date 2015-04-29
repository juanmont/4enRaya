package tp.pr4.vista;

import java.util.Scanner;

import tp.pr4.commands.GestorComandos;
import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TableroInmutable;
import tp.pr4.logica.TipoJuego;
import tp.pr4.observers.ObserverPartida;

/**
 * Constructuctor de la clase 
 * @param cont - Controlador de la consola
 * @param in - Scanner con el comando
 */
public class ViewConsole implements ObserverPartida{
	private ConsoleController control;
	private boolean terminada;
	private TipoJuego juego;
	private Ficha turno;
	private GestorComandos gestorCmd;
	private Scanner in;
	private Ficha ganador;
	private String tablero;
	
	/**
	 * Metodo que ejecuta el juego en modo consola
	 */
	public ViewConsole(ConsoleController cont, Scanner in, TipoJuego juego){
		tablero = "";
		control = cont;
		terminada = false;
		turno = Ficha.BLANCA;
		this.juego = juego;
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
	/**
	 * Pone terminada a true(la partida ha terminado)
	 */
	@Override
	public void onTerminada() {
		this.terminada = true;
	}
	
	/**
	 * Hace un reset poniendo el truno en blancas y sin haber ganador,
	 * e imprime un mensage por pantalla de que se ha reiniciado la partida
	 */
	@Override
	public void onReset(int x,int y) {
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		System.out.println("Partida reiniciada.");
	}
	/**
	 * Pone en ganador la ficha del color que ha ganado
	 */
	@Override
	public void onGanador(Ficha ganador) {
		this.ganador = ganador;
	}
	
	/**
	 * Imprime un error por pantalla
	 */
	@Override
	public void onError(String error) {
		System.err.println(error);
	}
	/**
	 * Pone el juego, en el modo de juego que le entra por parametro
	 */
	@Override
	public void onJuego(TipoJuego juego) {
		this.juego = juego;
	}
	
	/**
	 * Cambia el truno por el que le entra por para metro 
	 */
	@Override
	public void onTurno(Ficha turno) {
		this.turno = turno;
	}
	
	/**
	 * Sustituye el tablero por un tablero nuevo
	 */
	@Override
	public void actualizaTablero(TableroInmutable tab) {
		this.tablero = tab.creaTablero();
	}
	/**
	 * No lo utilizamos.
	 */
	@Override
	public void onIniciaTablero(int x, int y, TipoJuego juego) {
		// TODO Auto-generated method stub
		
	}
}
