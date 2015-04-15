
package tp.pr4.logica;


/**
 * Clase que representa el movimiento de un jugador.
 * Tiene un método para ejecutar el movimiento sobre la partida, y otro para deshacerlo.
 * Es una clase abstracta; habrá una clase no abstracta por cada tipo de juego soportado.
 */
public abstract class Movimiento {
	protected int fila;
	protected int donde;
	protected Ficha color;
	/**
	 *  Constructor del objeto.
	 * @param donde - Columna en la que se colocará la ficha.
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	protected Movimiento(int donde, Ficha color){
		this.donde = donde;
		this.color = color;
	}
	/**
	 * Devuelve el color del jugador al que pertenece el movimiento.
	 * (Puede hacerse abstracto)
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public abstract Ficha getJugador();
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como par�metro.
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento.
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @return true - Si todo fue bien. 
	 * Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 * @throws MovimientoInvalido 
	 */
	public abstract boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	/**
	 * Deshace el movimiento en el tablero recibido como parámetro.
	 * Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; en caso contrario, el comportamiento es indeterminado.
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * @param tab - Tablero de donde deshacer el movimiento.
	 * @throws MovimientoInvalido 
	 */
	public abstract void undo(Tablero tab);
	
	/**
	 * Devuelve la columna en la que se encuentra el movimiento.
	 * @return donde - Columna en la que se encuentra el movimiento.
	 */
	protected int getDonde(){
		return donde;
	}
	
	/**
	 * Devuelve la fila en la que se encuentra el movimiento.
	 * @return fila - Fila en la que se encuentra el movimiento.
	 */
	protected int getFila(){
		return fila;
	}

}
