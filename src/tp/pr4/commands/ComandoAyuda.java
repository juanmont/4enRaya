package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando ayuda. Mostrara la ayuda por pantalla.
 */
public class ComandoAyuda implements Comando{
	
	private GestorComandos gestor;
	private String ayudaSalir;
	
	/**
	 * Constructor de la clase.
	 * @param gestorComandos - Se utiliza para poder pedir a cada comando su ayuda en el metodo ejecuta.
	 */
	public ComandoAyuda(GestorComandos gestorComandos) {
		gestor = gestorComandos;
		ayudaSalir = "AYUDA: muestra esta ayuda.";
	}
	
	/**
	 * Decide si el string de entrada es "ayuda".
	 */
	@Override
	public boolean analiza(String cmd) {
		if(cmd.equalsIgnoreCase("ayuda")) 
			return true;
		return false;
	}
	
	/**
	 * Imprime todos los comandos por pantalla.
	 */
	@Override
	public void ejecuta(TipoJuego tipoJuego, Scanner in, Ficha turno) throws MovimientoInvalido {
		System.out.println("Los comandos disponibles son:\n\n" + gestor.devuelveAyuda());
	}
	
	/**
	 * Devuelve la ayuda del comando.
	 */
	@Override
	public String devolverAyuda() {
		return ayudaSalir;
	}

}
