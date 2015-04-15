package tp.pr4.control;

import java.util.Random;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoGravity;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Tablero;
/**
 * Jugador que juega de forma aleatoria a Gravity. 
 * Simplemente elige una posición aleatoria en el tablero,
 * comprobando antes que esa casilla está vacía (se podrá poner).
 */
public class JugadorAleatorioGravity implements Jugador {
	
	/**
	 * Devuelve un movimiento creado de manera aleatoria de tipo Gravity. 
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) throws MovimientoInvalido {
		Random random = new Random();
		int posX;
		int posY;
		//No sale del while hasta que no sea una columna con huevos vacios.
		do{
			posX = random.nextInt(tab.getAncho())+1;
			posY = random.nextInt(tab.getAlto())+1;
		}while(tab.getCasilla(posX, posY) != Ficha.VACIA);
		//Crea un movimento con una posicion aleatoria entre 1 y el ancho del tablero.
		return new MovimientoGravity(posX,posY, color);
	}

}
