package tp.pr4.logica;
/**
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 */
public class MovimientoInvalido extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * Constructor sin parámetros.
	 */
	public MovimientoInvalido(){
		super();
	}
	
	/**
	 * Constructor con un parámetro para la causa inicial que provocó la excepción.
	 * @param arg - Causa.
	 */
	public MovimientoInvalido(Throwable arg){
		super(arg);
	}
	
	/**
	 * Constructor con un parámetro para el mensaje.
	 * @param msg - Mensaje
	 */
	public MovimientoInvalido(String msg){
		super(msg);
	}
	
	/**
	 * Constructor con un parámetro para el mensaje y otro para la causa.
	 * @param msg - Mensaje.
	 * @param arg - Causa.
	 */
	public MovimientoInvalido(String msg, Throwable arg){
		super(msg,arg);
	}
	
	
	
}
