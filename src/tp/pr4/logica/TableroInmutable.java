package tp.pr4.logica;

public interface TableroInmutable {

	public String creaTablero();
	public int getAncho();
	public int getAlto();
	public Ficha getCasilla(int x, int y);
}
