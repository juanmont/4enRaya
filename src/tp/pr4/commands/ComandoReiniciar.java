package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando reiniciar. Reinicia la partida.
 */
public class ComandoReiniciar implements Comando{
	private ConsoleController control;
	private String ayudaReiniciar;
	
	public ComandoReiniciar(ConsoleController control)
	{
		this.control = control;
		ayudaReiniciar = "REINICIAR: reinicia la partida.";
	}
	
	/**
	 * Decide si el string de entrada es "reiniciar".
	 */
	@Override
	public boolean analiza(String cmd) {
		if(cmd.equalsIgnoreCase("reiniciar")) 
			return true;
		return false;
	}
	
	/**
	 * Reinicia el juego al que se estaba jugando, sus reglas a traves de la factoria.
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
		return ayudaReiniciar;
	}

}
