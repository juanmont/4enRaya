package tp.pr4.control;

/**
 * controlador para la GUI. Realizara la conexion entre la ventana grafica y la partida.
 */
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.TipoJuego;
import tp.pr4.observers.ObserverPartida;

public class ViewController {
	private Partida partida;
	private FactoriaTipoJuego factoria;
	
	/**
	 * constructor de la clase
	 * @param p: partida a la que se jugara.
	 * @param f: factoria inicial
	 */
	public ViewController(Partida p, FactoriaTipoJuego f){
		this.partida = p;
		factoria = f;
	}
	
	/**
	 * cambia la factoria dependiendo del tipo de juego
	 * e informa a la partida de a que juego se quiere jugar
	 * @param juego: juego al que se quiere juagr.
	 * @param x: ancho del tablero para Gravity
	 * @param y: alto del tablero para Gravity
	 */
	public void cambiaJuego(TipoJuego juego, int x,int y) {
		switch(juego){
		case C4:
			factoria = new FactoriaConecta4();
			break;
		case CO:
			factoria = new FactoriaComplica();
			break;
		case GR:
			factoria = new FactoriaGravity(x,y);
			break;
		}
		partida.cambiaJuego(juego);
		partida.reset(factoria.creaReglas());
	}
	
	/**
	 * crea un jugador aleatorio y le pide que le de un movimiento aleatorio.
	 * @throws MovimientoInvalido
	 */
	public void ponerAleatorio() throws MovimientoInvalido{
		partida.ejecutaMovimiento(factoria.creaJugadorAleatorio().getMovimiento(partida.getTablero(), partida.getTurno()));
	}
	
	/**
	 * crea un movimiento con los datos dados por la vista.
	 * @param x: columna en la que introducir la ficha
	 * @param y: fila en la que introducir la ficha.
	 * @throws MovimientoInvalido
	 */
	public void poner(int x, int y) throws MovimientoInvalido{
		partida.ejecutaMovimiento(factoria.creaMovimiento(x, y, partida.getTurno()));
	}
	
	/**
	 * le dice a la partida que se reinicie y pone sus jugadores en modo "humano"(true)
	 */
	public void reiniciar(){
		partida.reset(factoria.creaReglas());
	}
	
	/**
	 * Le dice a la partida que deshaga un movimiento.
	 */
	public void deshacer(){
		partida.undo();
	}
	
	/**
	 * le pide a la partida que introduzca un nuevo observador en su array.
	 * @param o
	 */
	public void addObserver(ObserverPartida o){
		partida.addObserver(o);
	}
	
	/**
	 * le pide a la partida que cargue el tablero para poder mostrarlo bien desde el inicio
	 */
	public void creaTablero() {
		partida.cargaTablero();
	}

}
