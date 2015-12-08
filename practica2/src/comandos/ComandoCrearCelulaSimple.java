package comandos;

import general.Mundo;

 public class ComandoCrearCelulaSimple extends Comando {
	 private int f;
	 private int c;
	 private boolean parseCorrecto = false;

 
 public ComandoCrearCelulaSimple(int f, int c){
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
		 
		if(cadenaComando.length == 3 && cadenaComando[0].equals("CREARCELULASIMPLE")){
			c = new ComandoCrearCelulaSimple(posF,posC);
			this.parseCorrecto = true;

		 } 			 
			
	return c;
  }


 public String textoAyuda() {
	 String texto ="CREARCELULASIMPLE F C: crea una nueva celula en la posición (f,c) si es posible. \n";
		return texto;
  }
 
 public boolean getParseCorrecto() {
	  return parseCorrecto;
  }
}
