package general;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		/**
		 * Crea el mundo, el controlador y e inicia la simulaci�n
		 */
		// 2. Crear el mundo
		Mundo mundo = new Mundo();
		// 3. Crea el controlador
		Controlador controlador = new Controlador(mundo, sc);
		// 4. Invoca m�todo, realiza simulacion

		controlador.realizaSimulacion();
	}

}
