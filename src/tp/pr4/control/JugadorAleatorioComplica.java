package tp.pr4.control;

import java.util.Random;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoComplica;
import tp.pr4.logica.Tablero;

/**
 * Jugador que juega de forma aleatoria a Complica. 
 * En este caso cualquier columna es válida, pues si está llena, se hará hueco.
 */
public class JugadorAleatorioComplica implements Jugador {
	
	/**
	 * Devuelve un movimiento creado de manera aleatoria de tipo complica.
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Random random = new Random();
		//crea un movimento con una posicion aleatoria entre 1 y el ancho del tablero.
		return new MovimientoComplica(random.nextInt(tab.getAncho())+1, color);
	}

}
