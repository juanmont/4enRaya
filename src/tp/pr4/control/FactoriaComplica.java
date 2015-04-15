package tp.pr4.control;



import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoComplica;
import tp.pr4.logica.ReglasComplica;
import tp.pr4.logica.ReglasJuego;

/**
 * Implementación de la factoría para el juego del Complica. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 */
public class FactoriaComplica implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col,color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola() {
		return null;
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

}
