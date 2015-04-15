
package tp.pr4.logica;


/**
 * Implementacion de las reglas del Conecta 4.
 * Se deben implementar todos los metodos del interfaz, ademas del constructor.
 */
public class ReglasConecta4 extends ReglasJuegoAbstracta{
	
	/**
	 * Comprueba si alguno de los jugadores ha hecho 4 en raya.
	 * desde la ultima ficha puesta.
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) throws MovimientoInvalido {
		int col = ultimoMovimiento.getDonde();
		int fila = t.buscaFila(col) + 1;
		if (comprueba(col, fila, t))
			return ultimoMovimiento.getJugador();
		else
			return Ficha.VACIA;
	}
	
	/**
	 * Inicia el tablero con dimensiones de 7x6, por defecto.
	 */
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(7, 6);
	}
	
	/**
	 * Comprueba que la partida haya terminado en tablas, cuando toda la fila superior este llena.
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) throws MovimientoInvalido {
			for (int i = 1; i <= t.getAncho(); i++) {
				if (t.getCasilla(i, 1) == Ficha.VACIA)
					return false;
			}
			return true;
	}
}
