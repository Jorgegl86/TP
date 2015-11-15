package tp.pr1;

import java.util.Scanner;



public class Main {

public static void main(String[] args) {
		// TODO Auto-generated method stub
    Scanner sc = new Scanner(System.in);  

//2. Crear el mundo
Mundo mundo = new Mundo();
//3. Crea el controlador
  Controlador controlador = new Controlador(mundo,sc);
//4. Invoca método, realiza simulacion
		
 controlador.realizaSimulacion();	
 }

}

