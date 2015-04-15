package tp.pr4.control;



import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoGravity;
import tp.pr4.logica.ReglasGravity;
import tp.pr4.logica.ReglasJuego;
/**
 * Implementación de la factoría para el juego del Gravity. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 * Dado que el tamaño del tablero de Gravity no está fijo, sino que se puede cambiar en cada partida, 
 * la factoría puede configurarse con el tamaño del tablero que se quiere utilizar.
 */
public class FactoriaGravity implements FactoriaTipoJuego {
	private int tamX = 10;
	private int tamY = 10;
	
	public FactoriaGravity(){}
	/**
	 * Constructor de la clase, para modificar los atributos.
	 * @param x - Dimension columnas.
	 * @param y - Dimension filas.
	 */
	public FactoriaGravity(int x,int y) {
		tamX = x;
		tamY = y;
	}
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(tamX,tamY);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col,fila,color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola() {
		return null;
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}
	
}
