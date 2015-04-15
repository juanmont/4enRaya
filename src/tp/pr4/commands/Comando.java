package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.TipoJuego;

/**
 * Interfaz que representa un comando. 
 */
public interface Comando {
	
	/**
	 * Analiza dado un string, si coincide con este comando.
	 * @param cmd - string con el que compara.
	 */
	public boolean analiza(String cmd);
	
	/**
	 * Ejecuta el comado introducido.
	 * @param tipoJuego - Tipo de juego al que se esta jugando.
	 * @throws MovimientoInvalido si se ha producido algun error de ejecucion
	 */
	public void ejecuta(TipoJuego tipoJuego, Scanner in, Ficha turno) throws MovimientoInvalido;
	/**
	 * Devuelve la ayuda de cada comando
	 * @return String de ayuda
	 */
	public String devolverAyuda();
}
