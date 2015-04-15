package tp.pr4.logica;


/**
 * Clase que implementa el movimiento para el juego del Conecta 4.
 * Se deben implementar los métodos abstractos de la clase padre.
 */
public class MovimientoConecta4 extends Movimiento {
	/**
	 * Constructor del objeto.
	 * @param donde - Columna en la que se colocara la ficha
	 * @param color -  Color de la ficha que se pondra (o jugador que pone).
	 */
	public MovimientoConecta4(int donde, Ficha color){
		super(donde,color);
	}
	
	/**
	 * Devuelve el color del jugador que esta jugando.
	 */
	@Override
	public Ficha getJugador(){
		return color;
	}
	
	/**
	 * Comprueba que la ficha este dentro del tablero y la coloca si cabe en la columna.
	 * En caso de no poder colocarla, crea un excepcion y la lanza.
	 */
	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if(tab.getAncho()>=donde && donde>0){
			fila = tab.buscaFila(donde);
			if(fila>0){
				tab.setCasilla(donde,fila,color);
				return true;
			}
			else throw new MovimientoInvalido("Columna llena.");
		}else
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+ tab.getAncho()+ ".");
		
	}
	
	/**
	 * Deshace un movimento.
	 */
	@Override
	public void undo(Tablero tab) {
	tab.setCasilla(donde, fila, Ficha.VACIA);	
	}

}
