package comandos;

import general.Mundo;

public class ComandoPaso extends Comando{
  
	private boolean parseCorrecto = false;

	@Override
	public void ejecutar(Mundo mundo) {
        mundo.evolucina();		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
	  Comando c = null;
	   if((cadenaComando.length == 1)&& cadenaComando[0].equals("PASO")){
			 c = new ComandoPaso();
			 parseCorrecto = true;

		}
	 return c;
	}

	@Override
	public String textoAyuda() {
		 String texto ="PASO: realiza un paso en la simulación.\n";
		  
	  return texto;
	}
	public boolean getParseCorrecto() {
		  return parseCorrecto;
	   }

}
