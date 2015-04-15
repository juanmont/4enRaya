package tp.pr4.logica;
/**
 * Enumerado con los tipos de juego soportados que facilita distintas implementaciones.
 * No se obliga a los alumnos a hacerlo (no aparece en la documentación).
 */
public enum TipoJuego {
	C4, CO, GR;	
	
	/**
	 * Devuelve el tipo de juego a partir de la cadena del parámetro.
	 * @param param
	 * @return null si es incorrecto.
	 */
	public static TipoJuego fromParam(String param) {
		if (param.equalsIgnoreCase("C4") || param.equalsIgnoreCase("Conecta4"))
			return C4;
		else if (param.equalsIgnoreCase("CO") || param.equalsIgnoreCase("Complica"))
			return CO;
		else if (param.equalsIgnoreCase("GR") || param.equalsIgnoreCase("Gravity"))
			return GR;
		else
			return null;
	}
	
	public String toString() {
		switch (this) {
		case C4:
			return "Conecta4";
		case CO:
			return "Complica";
		case GR:
			return "Garvity";
		default:
			return "";
		}
	}
}
