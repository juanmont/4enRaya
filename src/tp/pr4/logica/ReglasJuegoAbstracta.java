package tp.pr4.logica;


/**
 * Clase abstracta con los algoritmos comunes en los juegos.
 */
public abstract class ReglasJuegoAbstracta implements ReglasJuego {
	
	/** 
	 * Comprobara si hay 4 en raya en cada una de las direcciones.
	 * @param col - Columna de la ficha a comprobar.
	 * @param fila - Fila de la ficha a comprobar.
	 * @param t - Tablero sobre el que realizaremos las comprobaciones.
	 * @return Si se han producido o no las 4 en raya.
	 * @throws MovimientoInvalido 
	 */
	protected boolean comprueba(int col, int fila, Tablero t) throws MovimientoInvalido {
		// Comprueba en horizontal.
		if (enRaya(col, fila, 1, 0, t))
			return true;
		// Comprueba en vertical. 
		else if (enRaya(col, fila, 0, 1, t))
			return true;
		// Comprueba en diagonal derecha.
		else if (enRaya(col, fila, 1, -1, t))
			return true;
		// Comprueba en diagonal izquierda.
		else if (enRaya(col, fila, -1, -1, t))
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo polivalente, dada una posicion del tablero comprueba si existen 4 en raya 
	 * en la direcion dada por los incrementos y en su opuesta.
	 * @param x - Columna de la ficha dada.
	 * @param y - Fila de la ficha dada.
	 * @param incX - Direccion horizontal a desplazar.
	 * @param incY - Direccion vertical a desplazar.
	 * @param tablero - Tablero sobre el que vamos a trabajar.
	 * @return Si se han encontrado o no las 4 en raya.
	 * @throws MovimientoInvalido 
	 */
	private boolean enRaya(int x, int y, int incX, int incY, Tablero tablero) throws MovimientoInvalido {
		
		//Inicializa el contador y el boolean y guarda la posicion de la ficha inicial.
		int cont = 0;
		int xAux = x;
		int yAux = y;
		boolean salir = false;
		
		// Comprueba las fichas inferiores y de la izquierda de la dada, asi como su diagonal.
		while (cont < 3 && salir == false) {
			if (tablero.getCasilla(xAux, yAux) == tablero.getCasilla(xAux - incX, yAux - incY)) {
				xAux -= incX;
				yAux -= incY;
				cont++;
			} else
				salir = true;
		}
		
		// Vuelve a la posicion inicial dada y restaura el boolean.
		xAux = x;
		yAux = y;
		salir = false;
		
		//Comprueba las fichas superiores y de la derecha de la dada, asi como su diagonal.
		while (cont < 3 && salir == false) {
			if (tablero.getCasilla(xAux, yAux) == tablero.getCasilla(xAux + incX, yAux + incY)) {
				xAux += incX;
				yAux += incY;
				cont++;
			} else
				salir = true;
		}

		return cont == 3;
	}
	
	/**
	 * Cambia el color de la ficha.
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		return ultimoEnPoner.contraria();
	}
	
	/**
	 * Empiezan jugando las blancas por defecto.
	 */
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

}
