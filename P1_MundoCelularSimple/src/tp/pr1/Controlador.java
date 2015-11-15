package tp.pr1;

import java.util.Scanner;


public class Controlador {
//pregunta al usuario que tiene que hacer

	private Mundo  mundo;
	private Scanner sc;

	public Controlador (Mundo mundo, Scanner sc){
	this.mundo=mundo;
	this.sc=sc;
 }


public Mundo getMundo() {
	return mundo;
}
public void setMundo(Mundo mundo) {
	this.mundo = mundo;
}

public boolean realizaSimulacion(){
	
	//leer lo que introduce usuario
	String lectura;
	boolean continuar =true;
	

	
	while (continuar==true){
	//Convertir a mayuscula
		System.out.print("Comando >");
		lectura = sc.nextLine(); //lee un String desde teclado
	    String comando = lectura.toUpperCase(); 
	    String aux =" ";
	    String[] arrayCmd =comando.split(aux);

	  if(arrayCmd.length == 1){  
		if (arrayCmd[0].equals("PASO")){
				 
             mundo.evolucina();	
		}
		else if(arrayCmd[0].equals("INICIAR")){
			System.out.print("Este es un iniciar \n");
			  mundo = new Mundo();
			  
		   
		} else if(arrayCmd[0].equals("AYUDA")){
		  System.out.print("PASO: realiza un paso en la simulación \n"
		  		+ "AYUDA: muestra esta ayuda \n"
		  		+ "SALIR: cierra la aplicación \n"
		  		+ "INICIAR: inicia una nueva simulación \n"
		  		+ "VACIAR: crea un mundo vacío \n"
		  		+ "CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible \n"
		  		+ "ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible \n");
			
		}
		
		else if(arrayCmd[0].equals("VACIAR")){
			  System.out.print("Vaciando superficie...\n");

			 mundo.vaciarMundo();
	
			
			
		}else if(arrayCmd[0].equals("SALIR")){
			  System.out.print("Fin de la simulación...\n");
			  continuar = false;
		}
			
	}

	  else if(arrayCmd.length == 3){  
	     //obtener posiciones
		  int posF= Integer.parseInt(arrayCmd[1]);
		  int posC=	Integer.parseInt(arrayCmd[2]);  	  
	     
			  if(arrayCmd[0].equals("CREARCELULA")){
				  
				  System.out.print("Creando célula en la posición: (" +posF+","+posC+ ")");
				  this.mundo.crearCelula(posF, posC);
			   }
	       
	          else if(arrayCmd[0].equals("ELIMINARCELULA")){
				  System.out.print("Eliminando célula en la posición: (" +posF+","+posC+ ")");

			       this.mundo.eliminarCelula(posF, posC);
	          }
	}
	
	else{
		  System.out.print("La opcion no existe \n");
	      
	}
 
	imprimeMundo();
}	//fin while	
  
  return true;	
	
  }
public void imprimeMundo(){
	System.out.println(this.mundo.toString());
}
}


