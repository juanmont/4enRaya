package tp.pr4.commands;

import java.util.Scanner;

import tp.pr4.control.ConsoleController;
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.TipoJuego;

/**
 * Implementación de comando para el comando poner. Le dice a la partida que ejecute el movimiento, que le devulve el jugador.
 */
public class ComandoPoner implements Comando {

	private ConsoleController control;
	private String ayudaPoner;
	
	public ComandoPoner(ConsoleController control) {
		this.control = control;
		ayudaPoner = "PONER: utilízalo para poner la siguente ficha.";
	}
	
	/**
	 * Decide si el string de entrada es "poner".
	 */
	@Override
	public boolean analiza(String cmd) {
		if(cmd.equalsIgnoreCase("poner")) 
			return true;
		return false;
	}
	
	/**
	 * Si la partida no ha terminado 
	 * le dirá a la partida que ejecute el movimiento del jugador al que pertenece el turno
	 */
	@Override
	public void ejecuta(TipoJuego tipoJuego, Scanner in2, Ficha turno) throws MovimientoInvalido {
		int columna = 0;
		int fila = 0;
		if (control.pideDatos()) {
			System.out.println("Introduce la columna: ");
			if (in2.hasNextInt()) {
				if (in2.hasNextInt())
					columna = in2.nextInt();
				in2.nextLine();
			}
			if (tipoJuego == TipoJuego.GR) {
				System.out.println("Introduce la fila: ");
				if (in2.hasNextInt()) {
					if (in2.hasNextInt())
						fila = in2.nextInt();
					in2.nextLine();
				}
			}
		}
		control.poner(columna, fila, turno);
	}
	
	/**
	 * Devuelve la ayuda del comando.
	 */
	@Override
	public String devolverAyuda() {	
		return ayudaPoner;
	}
}
	

