package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando Jugador. Crea un jugador humano o consola, para un color de ficha determinado.
 */
public class ComandoJugador implements Comando {
	private Ficha ficha;
	private String tipoJugador;
	private ConsoleController control;
	private String ayudaJugador;
	
	/**
	 * Controlador de la clase.
	 * @param control - Controlador de la partida.
	 */
	public ComandoJugador(ConsoleController control){
		this.control = control;
		ayudaJugador = "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.";
	}
	
	/**
	 * Decide si el string de entrada es jugador y  si los colores y los tipos de jugadores son válidos.
	 * Mete en ficha del color introducido
	 * introduce el tipo de jugador del string en tipoJugador.
	 */
	@Override
	public boolean analiza(String cmd) {
		String[] s = cmd.split(" ");
		if (s.length == 3 && s[0].equalsIgnoreCase("jugador")) {
			if (s[1].equalsIgnoreCase("blancas")
					|| s[1].equalsIgnoreCase("negras")) {
				if (s[2].equalsIgnoreCase("humano")
						|| s[2].equalsIgnoreCase("aleatorio")) {
					switch (s[1]) {
					case "blancas":
						this.ficha = Ficha.BLANCA;
						break;
					case "negras":
						this.ficha = Ficha.NEGRA;
						break;
					}
					tipoJugador = s[2];
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Si el string es "humano", cambia el jugador a humano con el color de la ficha.
	 * Sino cambia a jugador aleatorio con el color de la ficha.
	 */
	@Override
	public void ejecuta(TipoJuego tipoJuego, Scanner in, Ficha turno) {
		if(tipoJugador.equalsIgnoreCase("humano")){
			control.cambiaAJugadorHumano(ficha);
		}
		else 
			control.cambiaAJugadorAleatorio(ficha);
			
	}
	
	/**
	 * Devuelve la ayuda del comando.
	 */
	@Override
	public String devolverAyuda() {
		return ayudaJugador;
	}

}
