package tp.pr4.observers;

import tp.pr4.logica.Ficha;
import tp.pr4.logica.Tablero;
import tp.pr4.logica.TipoJuego;

public interface ObserverPartida {
	/**
	 * avisa a la vista cuando la partida ha terminado
	 */
	public void onTerminada();
	/**
	 * resetea la vista.
	 * @param x: ancho del tablero para la GUI
	 * @param y: alto del tablero para la GUI
	 */
	public void onReset(int x,int y);
	
	/**
	 * informa a la vista del jugador que a ganado
	 * @param ganador: color del ganador
	 */
	public void onGanador(Ficha ganador);
	
	/**
	 * informa a la vista de que ha habido un error en el modelo
	 * @param error: string de error.
	 */
	public void onError(String error);
	
	/**
	 * le informa a la vista del tipo de juego al que se esta jugando actualmente.
	 * @param juego: juego al que se jugará
	 */
	public void onJuego(TipoJuego juego);
	
	/**
	 * informa a la vista de quien tiene el turno
	 * @param turno: turno del jugador que tiene que poner
	 */
	public void onTurno(Ficha turno);
	
	/**
	 * le envia a la vista el string del tablero a mostrar (para vista consola).
	 * @param Tablero
	 */
	public void onMuestraTablero(String Tablero);
	
	/**
	 * le dice a la vista que debe actualizar su tablero (para vista GUI)
	 * @param tab: tablero con el que actualizar.
	 */
	public void actualizaTablero(Tablero tab);
	
	/**
	 * informa a la vista del tablero inicial con el que se empezará.
	 * @param x: ancho del tablero.
	 * @param y: alto de tablero
	 * @param juego: juego inicial.
	 */
	public void onIniciaTablero(int x, int y, TipoJuego juego);
}
