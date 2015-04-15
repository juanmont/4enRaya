package tp.pr4.control;

/**
 * Controlador de consola. realiza la conexion entre la vista de consola y la partida.
 */
import tp.pr4.logica.Ficha;
import tp.pr4.logica.MovimientoInvalido;
import tp.pr4.logica.Partida;
import tp.pr4.logica.TipoJuego;
import tp.pr4.observers.ObserverPartida;


public class ConsoleController {
	
	private Partida partida;	//partida a la que se jugara.
	private boolean jugadorBlancas; //si estan a true significa que el jugador es humano
	private boolean jugadorNegras;
	private FactoriaTipoJuego factoria; //factoria para crear los objetos necesarios para jugar.
	
	/**
	 * Constructor de la clase
	 * @param p: partida a la que jugaremos
	 * @param f: factoria inicial con la que empezaremos jugando
	 * @param juego: tipo de juego al que se empieza jugando
	 */
	public ConsoleController(Partida p, FactoriaTipoJuego f, TipoJuego juego){
		this.factoria = f;
		this.partida = p;
		jugadorBlancas = true;
		jugadorNegras = true;
	}
	
	/**
	 * cambia el jugador a humano
	 * @param ficha: decide que jugador será humano.
	 */
	public void cambiaAJugadorHumano(Ficha ficha){
		if (ficha == Ficha.BLANCA)
			jugadorBlancas = true;
		else if (ficha == Ficha.NEGRA)
			jugadorNegras = true;
	}
	
	/**
	 * cambia el jugador a aleatorio.
	 * @param ficha: decide que jugador será aleatorio.
	 */
	public void cambiaAJugadorAleatorio(Ficha ficha) {
		if (ficha == Ficha.BLANCA)
			jugadorBlancas = false;
		else if (ficha == Ficha.NEGRA)
			jugadorNegras = false;
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
	}

	/**
	 * segun si el jugador que tiene el turno es humano o aleatorio crea un tipo de movimiento utilizando los datos(Humano) o
	 * crea un jugador aleatorio y le pide un movimiento a este jugador (aleatorio)
	 * @param x: columna en la que poner.
	 * @param y: fila en la que poner la ficha, si no es de tipo Gravity el juego vendra como un 0.
	 * @param turno: ficha a poner.
	 * @throws MovimientoInvalido
	 */
	public void poner(int x, int y, Ficha turno) throws MovimientoInvalido {
		if (!jugadorNegras && turno == Ficha.NEGRA)
			partida.ejecutaMovimiento(factoria.creaJugadorAleatorio().getMovimiento(partida.getTablero(), turno));
		else if (!jugadorBlancas && turno == Ficha.BLANCA)
			partida.ejecutaMovimiento(factoria.creaJugadorAleatorio().getMovimiento(partida.getTablero(), turno));
		else
			partida.ejecutaMovimiento(factoria.creaMovimiento(x, y, turno));
	}
	
	/**
	 * le dice a la partida que se reinicie y pone sus jugadores en modo "humano"(true)
	 */
	public void reiniciar(){
		jugadorNegras = true;
		jugadorBlancas = true;
		partida.reset(factoria.creaReglas());
	}
	
	/**
	 * Le dice a la partida que deshaga un movimiento.
	 */
	public void undo(){
		partida.undo();
	}
	
	/**
	 * informa a la vista dependiendo de que tipo de jugador sea el que tiene el turno
	 * para que pida datos por consola o no.
	 * @return true si tiene que pedir datos.
	 */
	public boolean pideDatos(){
		Ficha turno = partida.getTurno();
		if(turno == Ficha.BLANCA)
			return jugadorBlancas;
		else if(turno == Ficha.NEGRA)
			return jugadorNegras;
		else
			return false;
	}
	
	/**
	 * añade un observador a la partida de tipo ObserverPartida.
	 * @param obs: observer que tiene que añadir
	 */
	public void addObserver(ObserverPartida obs){
		partida.addObserver(obs);
	}
	
	/**
	 * le pide a la partida que actualize su tablero.
	 */
	 public void actualizaTablero(){
		 partida.creaTablero();
	 }
}
