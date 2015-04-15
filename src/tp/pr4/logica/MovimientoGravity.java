package tp.pr4.logica;

/**
 * Clase que implementa el movimiento para el juego del Gravity.
 * Se deben implementar los métodos abstractos de la clase padre.
 */
public class MovimientoGravity extends Movimiento {
	private int[] dxDy;
	private final int max = 2;
	/**
	 * Constructor del objeto.
	 * @param x - Columna en la que se colocará la ficha.
	 * @param y - Fila en la que se colocará la ficha.
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoGravity(int x,int y, Ficha color) {
		super(x, color);
		fila = y;
		dxDy = new int[max];
	}

	@Override
	public Ficha getJugador() {
		return color;
	}
	
	/**
	 * Mueve la ficha a la posicion adecuada.
	 * @param tab - Tablero del juego.
	 */
	public void iteraMovimiento(Tablero tab) {
		while (donde > 1 && donde < tab.getAncho() && fila > 1 && fila < tab.getAlto()
				&& tab.getCasilla(donde+dxDy[0], fila+dxDy[1]) == Ficha.VACIA) {
			donde += dxDy[0];
			fila += dxDy[1];
		}
		tab.setCasilla(donde, fila, color);
	}

	/**
	 * Comprueba que la ficha este dentro del tablero y no intente ocupar el lugar de otra.
	 * En caso de no ser asi, lanza un excepcion.
	 * Coloca la ficha en caso de estar en el centro.
	 */
	@Override
	public boolean ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		if (tab.getAlto() >= fila && fila > 0 && donde > 0 && donde <= tab.getAncho()) {
			if (tab.getCasilla(donde, fila) == Ficha.VACIA) {
				calculaDxDy(tab);
				if (dxDy[0] == 0 && dxDy[1] == 0)
					tab.setCasilla(donde, fila, color);
				else
					iteraMovimiento(tab);
				return true;
			} else
				throw new MovimientoInvalido("Casilla ocupada.");
		} else
			throw new MovimientoInvalido("Posición incorrecta.");
	}

	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(donde, fila, Ficha.VACIA);
		
	}
	
	/**
	 * Devuelve la fila en la que estamos.
	 */
	public int getFila(){
		return fila;
	}
	
	/**
	 * Calcula la posicion de la ficha en el tablero, respecto a las nuevas auxiliares de fila y columna.
	 * @param tab - Tablero de la partida.
	 */
	private void calculaDxDy(Tablero tab){
		int dx = 0, distanciaX = 0;
		//Comprueba columna de la ficha comparado con el centro
		if(donde<= tab.getAncho()/2){ //Lado izquierdo dx = -1
			dx=-1;
			distanciaX = donde;
		}else if(tab.getAncho()%2==1 && donde == ((tab.getAncho()/2) + 1)){ //centro dx = 0
			dx=0;
			distanciaX = donde;
		}else{	//Lado derecho dx = 1
			dx = 1;
			distanciaX = tab.getAncho() - donde + 1;
		}
		calculaDy(tab,distanciaX,dx);
	}
	
	/**
	 * Calcula en que parte del tablero esta, respecto a las filas.
	 * @param tab - Tablero de la partida.
	 * @param distanciaX - Distancia a la pared vertical mas cercana.
	 * @param dx - Auxiliar para las columnas.
	 */
	private void calculaDy(Tablero tab, int distanciaX,int dx){
		int dy = 0, distanciaY = 0;
		//Comprueba fila de la ficha comparado con el centro 
		if(fila <= tab.getAlto()/2){	//parte superior dy = -1
			dy=-1;
			distanciaY = fila;
		}else if(tab.getAlto()%2 == 1 && fila == ((tab.getAlto()/2) + 1)){ //centro dy = 0;
			dy = 0;
			distanciaY = fila;
		}else{ //parte inferior dy = 1
			dy = 1;
			distanciaY = tab.getAlto() - fila +1;
		}
		compruebaDistancias(dx,dy,distanciaY,distanciaX);
		
	}
	
	/**
	 * Comprueba cual de las paredes esta mas cerca de la ficha.
	 * @param dx - Auxiliar para las columnas.
	 * @param dy - Auxiliar para las filas.
	 * @param distanciaY - Distancia a la pared vertical mas cercana.
	 * @param distanciaX - Distancia a la pared horizontal mas cercana.
	 */
	private void compruebaDistancias(int dx, int dy, int distanciaY,int distanciaX) {
		if (distanciaX > distanciaY) {
			dx = 0;
		} else if (distanciaY > distanciaX) {
			dy = 0;
		}
		// Guardo en la primera posicion dx.
		dxDy[0] = dx;
		// Guardo en la segunda posicion dy.
		dxDy[1] = dy;
	}
}
