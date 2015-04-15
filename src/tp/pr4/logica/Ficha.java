
package tp.pr4.logica;
/**
 * Repesenta la informacion del color de una ficha.
 * El enumerado es utilizado para guardar la informacion de cada posicion de los tableros,
 * por lo que contiene tambien un simbolo para indicar la ausencia de ficha en esa posicion.
 * Tambien se utiliza para el color de un jugador.
 * IMPORTANTE: en la documentacion aparecen una serie de metodos (valueOf, values) 
 * que NO HAY QUE IMPLEMENTAR, sino que son aniadidos automaticamente por el compilador.
 */
public enum Ficha {
	BLANCA, NEGRA, VACIA;
	
	/**
	 * Convierte un tipo de ficha a un string.
	 * @param f - Ficha.
	 * @return Devuleve el string dependiendo del tipo de ficha.
	 */
	public String toString() {
		switch (this) {
		case BLANCA:
			return "blancas";
		case NEGRA:
			return "negras";
		case VACIA:
			return "tablas.";
		default:
			return "";
		}
	}
	
	/**
	 * Convierte un tipo de ficha a un char.
	 * @return - Devuleve el char dependiendo del tipo de ficha.
	 */
	public char pintaFicha() {
		switch (this) {
		case BLANCA:
			return 'O';
		case NEGRA:
			return 'X';
		case VACIA:
			return ' ';
		}
		return 0;
	}
	
	/**
	 * cambia la ficha que se ha introducido por su contrario
	 * @return valor de la ficha cambiada.
	 */
	public Ficha contraria() {
		if (this == Ficha.BLANCA)
			return Ficha.NEGRA;
		else if (this == Ficha.NEGRA)
			return Ficha.BLANCA;
		else
			return Ficha.VACIA;
	}
}

