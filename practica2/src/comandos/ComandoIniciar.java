package comandos;

import general.Mundo;

public class ComandoIniciar extends Comando {

	private boolean parseCorrecto = false;

	@Override
	public void ejecutar(Mundo mundo) {
		mundo = new Mundo();
		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
	  Comando c = null;
		   if((cadenaComando.length == 1) && cadenaComando[0].equals("INICIAR")){
				 c = new ComandoIniciar();
				 parseCorrecto = true;
			}
		 
		return c;
	}

	@Override
	public String textoAyuda() {
		String texto ="INICIAR:inicia una nueva simulación.\n";
		  
	  return texto;
	}
public boolean getParseCorrecto() {
		  return parseCorrecto;
	   }
}
