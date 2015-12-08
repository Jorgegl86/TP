package comandos;

import general.Mundo;

public class ComandoEliminarCelula extends Comando {

	private int f;
	private int c;
	private boolean parseCorrecto = false;
	public ComandoEliminarCelula(int f, int c){
		
		this.f=f;
		this.c=c;
	}
	public void ejecutar(Mundo mundo) {
         mundo.eliminarCelula(this.f, this.c); 		
	}

	@Override
	public Comando parsea(String[] cadenaComando) {
		Comando c = null;
		int posF = Integer.parseInt(cadenaComando[1]);
		int posC = Integer.parseInt(cadenaComando[2]);
		 
		if(cadenaComando.length == 3 && cadenaComando[0].equals("ELIMINARCELULA")){
			c = new ComandoEliminarCelula(posF,posC);
			this.parseCorrecto = true;
		 } 
			
	 return c;
	}

	@Override
	public String textoAyuda() {
		String texto ="ELIMINARCELULA F C: Elimina una celula de la posición (f,c) si es posible \n";
	  
	  return texto;
	}
	public boolean getParseCorrecto() {
		  return parseCorrecto;
	   }
}
