package comandos;

import general.Mundo;

public class ComandoCrearCelulaCompleja extends Comando{

  private int f;
  private int c;
  private boolean parseCorrecto = false;

	 
  public ComandoCrearCelulaCompleja(int f, int c){
	 this.f=f;
	 this.c=c;
  }

 public void ejecutar(Mundo mundo) {
	   mundo.crearCelula(f, c);		
 }
	
 public Comando parsea(String[] cadenaComando) {
	Comando c = null;
	int posF = Integer.parseInt(cadenaComando[1]);
	int posC = Integer.parseInt(cadenaComando[2]);
		 
		if(cadenaComando.length == 3 && cadenaComando[0].equals("CREARCELULACOMPLEJA")){
			c = new ComandoCrearCelulaCompleja(posF,posC);
			this.parseCorrecto = true;

		 } 			 
			
	return c;
 }


 public String textoAyuda() {
	 
	 String texto ="CREARCELULACOMPLEJA F C: crea una nueva celula en la posición (f,c) si es posible. \n";
		
	  return texto;
  }
 public boolean getParseCorrecto() {
	  return parseCorrecto;
  }
}
