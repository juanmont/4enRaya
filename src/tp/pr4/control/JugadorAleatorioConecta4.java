package tp.pr4.control;

import java.util.Random;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoConecta4;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
/**
 * Jugador que juega de forma aleatoria a Conecta 4. 
 * Simplemente elige una columna aleatoria en el tablero, 
 * comprobando antes que se podrá colocar en ella (no está llena).
 */
public class JugadorAleatorioConecta4 implements Jugador {
	
	/**
	 * Devuelve un movimiento creado de manera aleatoria de tipo conecta4. 
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido {
		Random random = new Random();
		int pos;
		//No sale del while hasta que no sea una columna con huecos vacios.
		do{
			pos = random.nextInt(tab.getAncho())+1;
		}while(tab.getCasilla(pos, 1) != Ficha.VACIA);
		//Crea un movimento con una posicion aleatoria entre 1 y el ancho del tablero.
		return new MovimientoConecta4(pos, color);
	}

}
