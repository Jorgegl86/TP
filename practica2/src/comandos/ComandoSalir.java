package comandos;

import general.Mundo;

public class ComandoSalir extends Comando{
    
	private boolean parseCorrecto = false;

	@Override
	public void ejecutar(Mundo mundo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comando parsea(String[] cadenaComando){
		  Comando c = null;
			 if((cadenaComando.length == 1)&&cadenaComando[0].equals("SALIR")){
				  c = new ComandoSalir();
				  parseCorrecto = true;
			 }
			 
		 return c;
	 }

	@Override
	public String textoAyuda() {
		String texto ="SALIR: cierra la aplicaciónn";
		  
	 return texto;
	}
	public boolean getParseCorrecto() {
		  return parseCorrecto;
	   }

}
