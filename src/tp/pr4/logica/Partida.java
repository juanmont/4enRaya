
package tp.pr4.logica;

import java.util.ArrayList;
import java.util.Stack;

import tp.pr4.observers.ObserverPartida;

/**
 * Clase para representar la información de una partida.
 * Se encarga de almacenar el estado del tablero, a quién le toca, si ya hay un ganador, etc.,
 * así como la lista de movimientos que se han ido realizando para poder deshacerlos.
 * La partida guarda al menos los 10 últimos movimientos.
 */
public class Partida {

	private ReglasJuego reglas;
	private Tablero tab;
	private boolean terminada;
	private Ficha turno;
	private Ficha ganador;
	private TipoJuego juego;
	private Stack<Movimiento> pila;
	private ArrayList<ObserverPartida> obs;
	
	/**
	 * Construye una partida nueva.
	 * @param reglas -  Reglas del juego que se utilizaran durante la partida 
	 * (al menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas, TipoJuego juego) {
		pila = new Stack<Movimiento>();
		this.juego = juego;
		obs = new ArrayList<ObserverPartida>();
		reset(reglas);
	}
	
	public void addObserver(ObserverPartida o){
		obs.add(o);
	}
	
	/**
	 * Reinicia la partida en curso. Esta operacion no puede deshacerse. Gracias al parametro,
	 * permite cambiar el tipo de juego al que se juega.
	 * @param reglas -  Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {
		tab = reglas.iniciaTablero();
		this.reglas = reglas;
		pila.clear();
		terminada = false;
		turno = Ficha.BLANCA;
		ganador = Ficha.VACIA;
		for(ObserverPartida o: obs)
			o.onReset(tab.getAncho(),tab.getAlto());
	}
	
	public void cambiaJuego(TipoJuego juego){
		this.juego = juego;
		for(ObserverPartida o: obs)
			o.onJuego(this.juego);
	}
	
	/**
	 * 
	 * @param mov
	 *            - Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible con las reglas de la partida que se esta jugando
	 *            (si se esta jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 * @return true si se puede efectuar el movimiento. Es un error intentar
	 *         colocar una ficha del jugador que no tiene el turno, cuando la
	 *         partida esta terminada, columna llena, ...
	 * @throws MovimientoInvalido
	 */
	public void ejecutaMovimiento(Movimiento mov) {
		if (!terminada) {
			if (mov.getJugador() == turno) {
					try {
						if (mov.ejecutaMovimiento(tab)) {
							pila.push(mov);
							for(ObserverPartida o: obs)
								o.actualizaTablero(tab);
							
							turno = reglas.siguienteTurno(turno, tab);
							for(ObserverPartida o: obs){
								o.onTurno(turno);
							}
							
							Ficha f = reglas.hayGanador(mov, tab);
							if (f != Ficha.VACIA || reglas.tablas(turno, tab)) {
								ganador = f;
								terminada = true;
								for(ObserverPartida o: obs){
									o.onTerminada();
									o.onGanador(ganador);
								}
							}
						}
					} catch (MovimientoInvalido e) {
						for(ObserverPartida o: obs)
							o.onError(e.getMessage());					}
			} else{
				for(ObserverPartida o: obs)
					o.onError("Jugador no tiene el turno");
			}
		} else{
			for(ObserverPartida o: obs)
				o.onError("Partida terminada.");
		}
	}
	
	/**
	 * Deshace el ultimo movimiento ejecutado.
	 * @return true si se pudo deshacer.
	 * @throws MovimientoInvalido 
	 */
	public void undo() {
		if (pila.size() > 0) {
			Movimiento mov = pila.pop();
			mov.undo(tab);
			for (ObserverPartida o : obs)
				o.actualizaTablero(tab);
			turno = reglas.siguienteTurno(turno, tab);
			for (ObserverPartida o : obs) {
				o.onTurno(turno);
			}
		} else {
			for (ObserverPartida o : obs)
				o.onError("Imposible deshacer.");
		}
	}
	
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno(){
		if(isTerminada())
			return Ficha.VACIA;
		else
			return turno;
	}
	
	/**
	 * Devuelve el color del ganador. Solo valido si la partida ya ha terminado (isTerminada() == true).
	 * @return Color del ganador. Si la partida termina en tablas, Ficha.VACIA. Si la partida no ha terminado aun, el resultado es indeterminado.
	 */
	public Ficha getGanador(){
		return ganador;
	}
	
	/**
	 * Metodo para saber si la partida ha concluido ya o no.
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada(){
		return terminada;	
	}
	
	/**
	 * Método de acceso al tablero. Dependiendo de como se haga la implementacion del resto de clases 
	 * (principalmente de la clase Controlador), es posible que no se utilice para nada en la practica. 
	 * Sin embargo, es necesario implementarlo para poder ejecutar los test de unidad.
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero() {
		return tab;
	}
	
	/**
	 * Método para imprimir por pantalla el tablero.
	 */
	public void creaTablero() {
		for(ObserverPartida o: obs)
			o.onMuestraTablero(tab.creaTablero());
	}

	/**
	 * le dice a la ventana cuales son los parametros y el tipo de juego iniciales.
	 */
	public void cargaTablero() {
		for(ObserverPartida o: obs)
			o.onIniciaTablero(tab.getAncho(),tab.getAlto(), juego);
	}
}

