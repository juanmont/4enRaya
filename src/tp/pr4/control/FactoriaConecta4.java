package tp.pr4.control;



import tp.pr4.logica.Ficha;
import tp.pr4.logica.Movimiento;
import tp.pr4.logica.MovimientoConecta4;
import tp.pr4.logica.ReglasConecta4;
import tp.pr4.logica.ReglasJuego;

/**
 * Implementación de la factoría para el juego del Conecta 4. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 */
public class FactoriaConecta4 implements FactoriaTipoJuego {

	@Override
	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col,color);
	}

	@Override
	public Jugador creaJugadorHumanoConsola() {
		return null;
	}

	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	

}
