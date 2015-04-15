package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando salir. Termina la ejecución del programa.
 */
public class ComandoSalir implements Comando{
	private String ayudaSalir;
	
	public ComandoSalir() {
		ayudaSalir = "SALIR: termina la aplicación.";
	}
	
	/**
	 * Decide si el string de entrada es "salir".
	 */
	@Override
	public boolean analiza(String cmd) {
		if(cmd.equalsIgnoreCase("salir")) 
			return true;
		return false;
	}
	
	/**
	 * Termina la ejecución del programa.
	 */
	@Override
	public void ejecuta(TipoJuego tipoJuego, Scanner in, Ficha turno) {
		System.exit(0);
	}
	
	/**
	 * Devuelve la ayuda del comando.
	 */
	@Override
	public String devolverAyuda() {
		return ayudaSalir;
	}

}
