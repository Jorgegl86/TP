package comandos;

public class ParserComandos {
 
	private static Comando[] comandos =  {new ComandoAyuda(),
		new ComandoIniciar(), new ComandoPaso(), new ComandoSalir(),
		new ComandoCrearCelulaSimple(0,0), new ComandoCrearCelulaCompleja(0,0),
		new ComandoEliminarCelula(0,0)};
    
	private static boolean parseCorrecto;
	public ParserComandos(Comando[] cmd){
		this.comandos = cmd;
	}
  static public String AyudaComandos(){
	  StringBuilder texto = new StringBuilder(null);
		 
	   for(int i=0; i<ParserComandos.comandos.length; i++){
			 texto.append(comandos[i].textoAyuda()); 
		  }
	   String cadena = texto.toString();
	 
	 return cadena;
  }
  
  static public Comando parseaComando(String[] cadena){
	  
	  boolean seguir = true;
	  int i=0;
	  Comando comandoParse = null;
	   
	    while(i < ParserComandos.comandos.length && seguir){
	    	comandoParse = comandos[i].parsea(cadena);
	    	  
	       if(comandoParse != null){//ha encontrado el comando
	    		  seguir = false;
	    		  
	    	  }
	    	  else i++; 
	     }
	  return comandoParse;
  }

  

}
