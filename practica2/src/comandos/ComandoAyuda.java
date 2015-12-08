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
		String texto ="PASO: realiza un paso en la simulación \n"
		  		+ "AYUDA: muestra esta ayuda \n"
		  		+ "SALIR: cierra la aplicación \n"
		  		+ "INICIAR: inicia una nueva simulación \n"
		  		+ "VACIAR: crea un mundo vacío \n"
		  		+ "CREARCELULA F C: crea una nueva celula en la posición (f,c) si es posible \n"
		  		+ "ELIMINARCELULA F C: elimina una celula de la posición (f,c) si es posible \n";
		return texto;
		
	}
public boolean getParseCorrecto() {
		  return parseCorrecto;
	   }

}
