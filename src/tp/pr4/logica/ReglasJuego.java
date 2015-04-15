
package tp.pr4.logica;


	/**
	 * Interfaz que representa las reglas de un juego concreto.
	 * La partida delega en un objeto de este interfaz para hacer avanzar la partida.
	 */
public interface ReglasJuego {
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no.
	 * Devuelve el color del ganador (si lo hay).
	 * @param ultimoMovimiento - Ultimo movimiento realizado. Las distintas implementaciones,
	 * pueden utilizar este parametro para optimizar la busqueda del ganador.
	 * @param t -  Estado del tablero.
	 * @throws MovimientoInvalido 
	 * @return/Ficha/ color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA. 
	 * (eso NO significa necesariamente que la partida haya terminado en tablas).
	 */
	public abstract Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) throws MovimientoInvalido;
	
	/**
	 * Construye el tablero que hay que utilizar para la partida, segun las reglas del juego.
	 * @return /Tablero/ Tablero a utilizar. El estado del tablero sera  el de inicio de la partida.
	 */
	public abstract Tablero iniciaTablero();
	
	/**
	 * Devuelve el color del jugador que comienza la partida.
	 * @return/Ficha/ Color del primer jugador en colocar ficha.
	 */
	public abstract Ficha jugadorInicial();
	
	/**
	 * Devuelve true si, con el estado del tablero dado,
	 * la partida ha terminado en tablas.
	 * @param ultimoEnPoner - Jugador que acaba de poner ficha.
	 * @param t - Estado del tablero.
	 * @return true si la partida ha terminado sin ganador.
	 * @throws MovimientoInvalido 
	 */
	public abstract boolean tablas(Ficha ultimoEnPoner, Tablero t) throws MovimientoInvalido;
	
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @param ultimoEnPoner - ultimo jugador en poner ficha
	 * @param t - Estado del tablero.
	 * @return Siguiente jugador que debe poner ficha.
	 */
	public abstract Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
	
	
	
	
	
}
