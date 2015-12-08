package general;

import java.util.Scanner;

import comandos.Comando;
import comandos.ComandoSalir;
import comandos.ParserComandos;

public class Controlador {
	private Mundo mundo;
	private Scanner sc;
	private Comando cmdParse;

	public Controlador(Mundo mundo, Scanner sc) {
		this.mundo = mundo;
		this.sc = sc;
	}

	/**
	 * Realiza la simulaci�n de las instrucciones que el usuario env�a El main
	 * lo invoca. Obtenemos los strings, y validamos lo que hace el programa
	 * Presenta el men� al usuario
	 * */
	public boolean realizaSimulacion() {

		String lectura;
		boolean continuar = true;
		Comando salir = new ComandoSalir();

		while (continuar == true) {
			// Convertir a mayuscula
			System.out.print("Comando >");
			lectura = sc.nextLine(); // lee un String desde teclado
			String comando = lectura.toUpperCase();
			String aux = " ";
			String[] arrayCmd = comando.split(aux);

			cmdParse = ParserComandos.parseaComando(arrayCmd);
			   
			    if(cmdParse != null){
				   cmdParse.ejecutar(this.mundo);
				   imprimeMundo();
			     }

			    else if(cmdParse.equals(salir)){
					System.out.print("Fin de la simulaci�n...\n");
					continuar = false;
			     }
			    
			    else
					System.out.print("La opcion no existe \n");
								
		}
			
			
		/*	
			
			if (arrayCmd.length == 1) {
				if (arrayCmd[0].equals("PASO")) {

					mundo.evolucina();
					imprimeMundo();
				} else if (arrayCmd[0].equals("INICIAR")) {
					// System.out.print("Este es un iniciar \n");
					mundo = new Mundo();
					imprimeMundo();

				} else if (arrayCmd[0].equals("AYUDA")) {
					System.out
							.print("PASO: realiza un paso en la simulaci�n \n"
									+ "AYUDA: muestra esta ayuda \n"
									+ "SALIR: cierra la aplicaci�n \n"
									+ "INICIAR: inicia una nueva simulaci�n \n"
									+ "VACIAR: crea un mundo vac�o \n"
									+ "CREARCELULA F C: crea una nueva celula en la posici�n (f,c) si es posible \n"
									+ "ELIMINARCELULA F C: elimina una celula de la posici�n (f,c) si es posible \n");

				}

				else if (arrayCmd[0].equals("VACIAR")) {
					System.out.print("Vaciando superficie...\n");

					mundo.vaciarMundo();
					imprimeMundo();

				} else if (arrayCmd[0].equals("SALIR")) {
					System.out.print("Fin de la simulaci�n...\n");
					continuar = false;
				} else
					System.out.print("La instrucci�n est� mal escrita.\n");

			}

			else if (arrayCmd.length == 3) {
				// obtener posiciones
				int posF = Integer.parseInt(arrayCmd[1]);
				int posC = Integer.parseInt(arrayCmd[2]);

				if (arrayCmd[0].equals("CREARCELULA")) {

					System.out.print("Creando c�lula en la posici�n: (" + posF
							+ "," + posC + ")\n");
					this.mundo.crearCelula(posF, posC);
					imprimeMundo();
				}

				else if (arrayCmd[0].equals("ELIMINARCELULA")) {
					System.out.print("Eliminando c�lula en la posici�n: ("
							+ posF + "," + posC + ")\n");

					this.mundo.eliminarCelula(posF, posC);
					imprimeMundo();
				} else {
					System.out.print("La instrucci�n est� mal escrita.\n");
				}

			}

			else {
				System.out.print("La opcion no existe \n");

			}

		} // fin while*/

		return true;

	}

	public void imprimeMundo() {
		System.out.println(this.mundo.toString());
	}
}
