package tp.pr4;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr4.control.ConsoleController;
import tp.pr4.control.FactoriaComplica;
import tp.pr4.control.FactoriaConecta4;
import tp.pr4.control.FactoriaGravity;
import tp.pr4.control.FactoriaTipoJuego;
import tp.pr4.control.ViewController;
import tp.pr4.logica.Partida;
import tp.pr4.logica.TipoJuego;
import tp.pr4.vista.Ventana;
import tp.pr4.vista.ViewConsole;

public class Main {
	/**
	 * Metodo principal de la aplicacion.
	 * @param args - Argumentos pasados a la aplicacion.
	 * @throws ParseException - Excepcion producida durante el parseo de los argumentos de entrada
	 */

	public static void main(String[] args){
		int tamX = 10; //Por defecto tamX para gravity
		int tamY = 10; //Por defecto tamY para gravity
		FactoriaTipoJuego factoria = new FactoriaConecta4(); //Factoria por defecto (c4)
		Scanner in = new Scanner(System.in);
		boolean window = false;
		CommandLineParser parser = new PosixParser();
		TipoJuego juego= TipoJuego.C4;
		
///////////////////////////////CREACION DEL OBJETO OPCIONES CON SUS OPCIONES//////////////////////////////
		Options opciones = creaOpciones();
////////////////////////////////////////CREACION DE COMMANDLINE/////////////////////////////////////////// 
        try{
        CommandLine Command = parser.parse(opciones, args);
 /////////////////////////////////////PARSEO DE LOS ARGMENTOS/////////////////////////////////////////////

        if(Command.hasOption("h"))		// OPCION -h
        {
        	new HelpFormatter().printHelp("tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]", opciones);
        	System.exit(0);
        }
        
        if(Command.hasOption("x") && Command.getOptionValue("x")!= null)		// OPCION -x
        {
        	tamX = Integer.parseInt(Command.getOptionValue("x"));
        }
		

        if(Command.hasOption("y") && Command.getOptionValue("y")!= null) 	// OPCION -y
        {
        	tamY = Integer.parseInt(Command.getOptionValue("y"));
        }

        if(Command.hasOption("u") && Command.getOptionValue("u")!= null)		// OPCION -g
        {
        	if(Command.getOptionValue("u").equalsIgnoreCase("window")){
    			window = true;
            }	
        }
        	
        if(Command.hasOption("g") && Command.getOptionValue("g")!= null)		// OPCION -g
        {
        	if(Command.getOptionValue("g").equalsIgnoreCase("gr")){
            	factoria = new FactoriaGravity(tamX, tamY);
            	juego = TipoJuego.GR;
            	//factoria.creaReglas();
            }
            else if(Command.getOptionValue("g").equalsIgnoreCase("co")){
            	factoria = new FactoriaComplica();
        		juego = TipoJuego.CO;
            }
            else if(Command.getOptionValue("g").equalsIgnoreCase("c4"))
            	factoria = new FactoriaConecta4();
            else { //Juego no valido
            	System.err.println("Uso incorrecto: Juego '"+ Command.getOptionValue("g") + "' incorrecto."
            			+ help());
            	System.exit(1);
            }
        	 if (Command.getArgList().size() > 1){ //hay argumentos sobrantes que no deberian estar
              	System.err.println("Uso incorrecto: Argumentos no entendidos: "+ argumentos(Command.getArgs())+
              			help());
              	System.exit(1);
         	 }
        }
        }catch(ParseException e) {
        	System.err.println("Uso incorrecto: "+ e.getMessage() + help());
        	System.exit(1);
        }
        
        Partida partida = new Partida(factoria.creaReglas(), juego);
        if(!window){
        	ConsoleController controlador = new ConsoleController(partida, factoria, juego);
     		ViewConsole vistaConsola = new ViewConsole(controlador,in);
        	vistaConsola.run();
        }
        else{
        	ViewController control = new ViewController(partida,factoria);
			Ventana Gui = new Ventana(control);
        }

			in.close();
			//System.exit(0);
		
	}
	
	/**
	 * añade string a la excepcion
	 * @return - String
	 */
	private static String help(){
		return "\nUse -h|--help para más detalles.";
	}
	
	/**
	 * crea el objeto opciones con todas las opciones posibles
	 * @return - opciones
	 */
	private static Options creaOpciones() {
		Options opciones = new Options();
		OptionBuilder.withArgName("game"); //<game>
		OptionBuilder.hasArg(); //True
		OptionBuilder.withLongOpt("game"); //"game"
		OptionBuilder.withDescription("Tipo de juego (c4, co, gr). Por defecto, c4."); //Descripcción
		Option g = OptionBuilder.create("g"); //"g"
        opciones.addOption(g);
        //------------------------------------------------- 
        OptionBuilder.withArgName("columnNumber"); //<columnNumber>
		OptionBuilder.hasArg(); //true
		OptionBuilder.withLongOpt("tamX"); //"tamX"
		OptionBuilder.withDescription("Número de columnas del tablero (sólo para Gravity). Por defecto, 10."); //Descripción
		Option x = OptionBuilder.create("x"); //"x"
        opciones.addOption(x);
        //-------------------------------------------------
        OptionBuilder.withArgName("rowNumber"); //<rowNumber>
		OptionBuilder.hasArg(); //true
		OptionBuilder.withLongOpt("tamY"); //"tamY"
		OptionBuilder.withDescription("Número de filas del tablero (sólo para Gravity). Por defecto, 10."); //Descripción
		Option y = OptionBuilder.create("y"); //"y"
        opciones.addOption(y);
        //------------------------------------------------
        OptionBuilder.withArgName("tipo"); //<rowNumber>
		OptionBuilder.hasArg(); //true
		OptionBuilder.withLongOpt("tamY"); //"tamY"
		OptionBuilder.withDescription("Tipo de interfaz (console, window). Por defecto, console"); //Descripción
		Option u = OptionBuilder.create("u"); //"y"
		opciones.addOption(u);
		//-------------------------------------------------
        opciones.addOption("h", "help", false, "Muestra la ayuda.");
        return opciones;

	}
	
	/**
	 * crea un String con concatenacion de los argumentos que sobran en CommandLine.
	 * @param args - argumentos que no se han parseado
	 * @return - String de argumentos unidos
	 */
	private static String argumentos(String[] args){
		String salida = "";
		int i = 0;
		while(i < args.length && args[i].startsWith("")){
			salida += args[i++];
			salida += " ";
		}
		return salida;
	}
}

