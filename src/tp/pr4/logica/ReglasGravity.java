package tp.pr4.logica;


/**
 * Implementación de las reglas del Gravity. 
 * Se deben implementar todos los métodos del interfaz, además del constructor.
 */
public class ReglasGravity extends ReglasJuegoAbstracta{
	private int numCols;
	private int numFilas;
	
	/**
	 * Constructor de la clase.
	 * @param numCols - Número de columnas del tablero.
	 * @param numFilas -  Número de filas del tablero.
	 */
	public ReglasGravity(int numCols, int numFilas)
	{
		this.numCols = numCols;
		this.numFilas = numFilas;
	}
	
	/**
	 * Comprueba que se haya hecho 4 en raya a través de la última ficha puesta.
	 * Sino lanza una excepcion.
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) throws MovimientoInvalido {
		int col = ultimoMovimiento.getDonde();
		int fila = ultimoMovimiento.getFila();
		if (comprueba(col, fila, t))
			return ultimoMovimiento.getJugador();
		else
			return Ficha.VACIA;
	}
	
	/**
	 * Incia el tablero con las dimensiones que recibe.
	 */
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(numCols, numFilas);
	}

	/**
	 * Comprueba que la partida no haya terminado en tablas recorriendo el tablero.
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t){
		for (int i = 1; i <= numCols; i++) {
			for (int j = 1; j <= numFilas; j++) {
				if (t.getCasilla(i, j) == Ficha.VACIA)
					return false;
			}
		}
		return true;
	}

}
