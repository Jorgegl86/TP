package comandos;

import general.Mundo;

public class ComandoAyuda extends Comando{
	
	private boolean parseCorrecto = false;

	@Override
	public void ejecutar(Mundo mundo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comando parsea(String[] cadenaComando){
	  Comando c = null;
		 if((cadenaComando.length == 1)&& cadenaComando[0].equals("AYUDA")){
			 
				c = new ComandoAyuda();
				parseCorrecto = true;
			}
		 
		return c;
	}

	@Override
	public String textoAyuda() {
		String texto ="PASO: realiza un paso en la simulaci�n \n"
		  		+ "AYUDA: muestra esta ayuda \n"
		  		+ "SALIR: cierra la aplicaci�n \n"
		  		+ "INICIAR: inicia una nueva simulaci�n \n"
		  		+ "VACIAR: crea un mundo vac�o \n"
		  		+ "CREARCELULA F C: crea una nueva celula en la posici�n (f,c) si es posible \n"
		  		+ "ELIMINARCELULA F C: elimina una celula de la posici�n (f,c) si es posible \n";
		return texto;
		
	}
public boolean getParseCorrecto() {
		  return parseCorrecto;
	   }

}
