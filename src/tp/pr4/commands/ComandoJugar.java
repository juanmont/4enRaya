package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando jugar. Permite jugar al tipo de partida deseada 
 */
public class ComandoJugar implements Comando{
	private ConsoleController control;
	private String ayudaJugar;
	
	/**
	 * Controlador de la clase.
	 * @param partida - Partida a la que se esta jugando.
	 * @param control - Controlador de la partida.
	 */
	public ComandoJugar(ConsoleController control) {
		this.control = control;
		ayudaJugar = "JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.";
	}
	
	/**
	 * Decide si el string de entrada es jugar y si lo que continua es un juego valido. 
	 * Cambia el tipo de juego del controlador.
	 * Crea las reglas dependiendo del tipo de juego a traves de la factoria.
	 */
	@Override
	public boolean analiza(String cmd) {
		TipoJuego tipoJuego = null;
		String[] s = cmd.split(" ");
		if (s.length == 2) {
			if (s[0].equalsIgnoreCase("jugar")) {
				if (s[1].equalsIgnoreCase("c4") || s[1].equalsIgnoreCase("co")) {
					tipoJuego = TipoJuego.fromParam(s[1]);
					control.cambiaJuego(tipoJuego, 0, 0);
					return true;
				}
			}
		}
		if (s.length == 4) {
			if (s[0].equalsIgnoreCase("jugar")) {
				if (s[1].equalsIgnoreCase("gr")) {
					tipoJuego = TipoJuego.fromParam(s[1]);
					control.cambiaJuego(tipoJuego, Integer.parseInt(s[2]), Integer.parseInt(s[3]));
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Hace un reset de la partida con las nuevas reglas del juego.
	 */
	@Override
	public void ejecuta(TipoJuego tipoJuego, Scanner in, Ficha turno) {
		control.reiniciar();
	}
	
	/**
	 * Devuelve la ayuda del comando.
	 */
	@Override
	public String devolverAyuda() {
		return ayudaJugar;
	}

}
