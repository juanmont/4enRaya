package tp.pr4.logica;


/**
 * Implementación de las reglas del Complica.
 * Se deben implementar todos los métodos del interfaz, además del constructor.
 */
public class ReglasComplica extends ReglasJuegoAbstracta{

	/**
	 * Comprueba si alguno de los jugadores ha hecho 4 en raya.
	 * comprueba la columna completa en la que se puso la ultima ficha, ficha por ficha,
	 * si gana alguna ficha le suma 1 a su contador de ganadas.
	 * Si los dos contadores son distintos de 0 no ai ganador.
	 * Si uno de los contadores es 0 y el otro mayor de 0 el ganador es el del color del contador.
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) throws MovimientoInvalido {
		int col = ultimoMovimiento.getDonde();
		int contBlancas = 0;// Cuenta las veces que las blancas hacen 4 en raya.
		int contNegras = 0;// Cuenta las veces que las negras hacen 4 en raya.
		int i = 1;
		
		//Comprueba que fichas hacen 4 en raya para cada ficha de la columna donde se 
		// ha puesto la ultima ficha (aumentando su contador).
		while(i <= t.getAlto() && (contNegras == 0 || contBlancas == 0)) {
			if (comprueba(col, i, t)) {
				if (t.getCasilla(col, i) == Ficha.BLANCA)
					contBlancas++;
				else if (t.getCasilla(col, i) == Ficha.NEGRA)
					contNegras++;
			}
			 i++;
		}
		//Se gana en caso de que solo un contador de una ficha sea mayor que cero.
		if (contBlancas > 0 && contNegras == 0)
			return Ficha.BLANCA;
		else if (contNegras > 0 && contBlancas == 0)
			return Ficha.NEGRA;
		else
			return Ficha.VACIA;
	}
	
	/**
	 * Inicia el tablero con dimensiones de 4x7, por defecto.
	 */
	@Override
	public Tablero iniciaTablero() {
		return new Tablero(4,7);
	}

	/**
	 * La partida no puede terminar en tablas en el complica.
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
	}
	
}
