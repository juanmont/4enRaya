package tp.pr4.commands;

import java.util.ArrayList;
import java.util.Scanner;

import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.TipoJuego;

/**
 * Clase que gestiona los comandos. Analiza si la llamada al comando ha sido correcta y en caso de que exista
 * el comando lo ejecuta.
 */
public class GestorComandos {
	//ArrayList que contiene todos los comandos posibles.
	private ArrayList<Comando> comandos;

	/**
	 * Constructora de la clase.
	 * @param partida - Partida sobre la que ejecutaremos los comandos.
	 * @param in - Escaner del teclado.
	 * @param control - Controlador.
	 */
	public GestorComandos(ConsoleController control) {
		comandos = new ArrayList<Comando>();
		comandos.add(new ComandoPoner(control));
		comandos.add(new ComandoDeshacer(control));
		comandos.add(new ComandoReiniciar(control));
		comandos.add(new ComandoJugar(control));
		comandos.add(new ComandoJugador(control));
		comandos.add(new ComandoSalir());
		comandos.add(new ComandoAyuda(this));
	}
	
	/**
	 * Comprueba si el comando introducido existe y lo ejecuta.
	 * @param cmd - Comando introducido por el usuario.
	 * @return Si se a podido ejecutar o no.
	 * @throws MovimientoInvalido si se ha producido algún error de ejecución
	 */
	public boolean ejecuta(String cmd, TipoJuego tipoJuego, Scanner in, Ficha turno) throws MovimientoInvalido {
		boolean ok = false;
		for(Comando c : comandos) {
			if (c.analiza(cmd)){
				ok = true;
				c.ejecuta(tipoJuego, in, turno);
			}
		}
			return ok;
	}
	
	/**
	 * Recorre todos los comandos concatenando en un String sus ayudas.
	 * @return - String con la ayuda de todos los comandos.
	 */
	public String devuelveAyuda(){
		StringBuilder ayuda = new StringBuilder();
		for(Comando c : comandos)
			ayuda.append(c.devolverAyuda()+"\n");
		return ayuda.toString();
	}
}
