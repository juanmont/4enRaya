
package tp.pr4.logica;


/**
 * Clase que implementa el movimiento para el juego del Complica.
 * Se deben implementar los métodos abstractos de la clase padre.
 */
public class MovimientoComplica extends Movimiento{
	private Ficha guardada;
	/**
	 * Constructor del objeto.
	 * @param i - Columna en la que se colocará la ficha.
	 * @param turno - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoComplica(int i, Ficha turno) {
		super(i,turno);
		guardada = Ficha.VACIA;
	}

	@Override
	public Ficha getJugador() {
		return color;
	}

	/**
	 * Comprueba que la ficha este dentro del tablero y la coloca, desplazando hacia abajo las fichas, si fuese necesario.
	 * En caso de no poder colocarla, crea un excepcion y la lanza.
	 */
	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (tab.getAncho() >= donde && donde > 0) {
			fila = tab.buscaFila(donde);
			if (tab.getCasilla(donde, 1) != Ficha.VACIA) {
				guardada = desplazaAbajo(donde, tab);
				tab.setCasilla(donde, 1, color);
				return true;
			} else {
				if (fila > 0) {
					tab.setCasilla(donde, fila, color);
					return true;
				}
			}
		}
		throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+ tab.getAncho()+ ".");
	}
	
	/**
	 * Desplaza todas las fichas de una columna, desde la posicion mas baja, una posicion hacia abajo.
	 * @param col - Columna a desplazar.
	 * @param t - Tablero sobre el que desplazar.
	 * @return La ficha que se va a borrar por la parte de abajo.
	 * @throws MovimientoInvalido 
	 */
	private Ficha desplazaAbajo(int col, Tablero t) {
		Ficha guardada = t.getCasilla(col, t.getAlto());
		for (int i = t.getAlto(); i > 1; i--)
			t.setCasilla(col, i, t.getCasilla(col, i - 1));
		return guardada;
	}

	/**
	 * Desplaza todas las fichas de una columna, desde la posicion mas alta, una posicion hacia arriba.
	 * @param col - Columna a desplazar.
	 * @param t - Tablero sobre el que desplazar.
	 * @throws MovimientoInvalido 
	 */
	private void desplazaArriba(int col, Tablero t){
		for (int i = 1; i < t.getAlto(); i++)
			t.setCasilla(col, i, t.getCasilla(col, i + 1));
	}

	@Override
	public void undo(Tablero tab) {
		if (guardada != Ficha.VACIA) {
			desplazaArriba(donde, tab);
			tab.setCasilla(donde, tab.getAlto(), guardada);
		} else
			tab.setCasilla(donde, tab.buscaFila(donde) + 1, Ficha.VACIA);
	}

}
