package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando deshacer. Le dice a la partida que deshaga un movimiento.
 */
public class ComandoDeshacer implements Comando{
	private ConsoleController control;
	private String ayudaDeshacer;
	
	/**
	 * Constructor de la clase.
	 * @param partida - Partida a la que se esta jugando.
	 */
	public ComandoDeshacer(ConsoleController control) {
		this.control = control;
		ayudaDeshacer = "DESHACER: deshace el último movimiento hecho en la partida." ;
	}

	/**
	 * Decide si el string de entrada es "deshacer".
	 */
	@Override
	public boolean analiza(String cmd) {
		if(cmd.equalsIgnoreCase("deshacer")) 
			return true;
		return false;
	}
	
	/**
	 * Le dice a la partida que deshaga el ultimo movimiento.
	 * Si no se ha podido deshacer el movimiento de la partida lanza una excepcion.
	 */
	@Override
	public void ejecuta(TipoJuego tipoJuego, Scanner in, Ficha turno) throws MovimientoInvalido {
		control.undo();
	}
	
	/**
	 * Devuelve la ayuda del comando.
	 */
	@Override
	public String devolverAyuda() {
		return ayudaDeshacer;
	}

}
