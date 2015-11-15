package tp.pr1;




public class Mundo {

	
	private Superficie superficie;
	private int filas = 3;
	private int columnas = 4;
	private final int NUM_CELULAS=6;

   


public Mundo(){
	// Superficie superficie = new Superficie(3,4);	//enunciado
	this.superficie = new Superficie(filas, columnas);
	//inicializa
    this.superficie.iniciar(NUM_CELULAS);
	
}



//superfcie
public boolean crearCelula(int f, int c){
	
	boolean creado = this.superficie.crearCelula(f, c);
	 //obtener f y c - ver si esa posicion está libre
return creado;	
}

//elimina celula Posicion

public boolean eliminarCelula(int f, int c){
	boolean eliminado = this.superficie.eliminarCelula(f,c);
return eliminado;
}


//paso


public void evolucina(){
	
	
	 //para validad posiciones y obtener casillas libres segun la posicion de la celula en la superficie
	Casilla[] casillaLibrePorCelula = null;
	//aqui tengo todas las casillas que estan ocupadas en la superficie
	 Casilla[] casillasOcupadas = this.superficie.encontrarCelulasSuperficie();
	 //recorro el array para buscar los posiciones libres para cada celula
	 int longOcupadas = numCasillasOcupadas(casillasOcupadas);
  for(int i=0; i < longOcupadas;i++){
		   
		 if((longOcupadas) == 0 ){
			 System.out.println("No hay posiciones libres \n");
		 }else
			 
			 //obtengo las casillas libres
			casillaLibrePorCelula = this.buscarPosicionesLibres(casillasOcupadas[i].getFilas(), casillasOcupadas[i].getColumnas());
			
		    int numCasillaOcupadas = this.numCasillasOcupadas(casillaLibrePorCelula);
			//obtengo la posicion aleatoria a donde me voy a mover
			int posCasillaSelecciona = seleccionarCasilla(numCasillaOcupadas);
			
			//acceder a la posicion de la casillaSelecciona para obtener la Casilla
			//voy a obtener la fila y colum de la casilla aleatoria seleccionada
			
			Casilla casillaAleatroia = obtenerCasillaAleatoria(casillaLibrePorCelula,posCasillaSelecciona);
			
			
			//llamo a la función moverCelula con los datos de la Casilla Seleccionada
			//controlar los movimientos para la celula
			//tengo mis casiilas libres correspondiente a la celula - ahora tengo
			//que decidir a qué casilla tengo que mover
			//seleccionar casilla a mover aleatoria con random
			//En la casilla selecciona, creo la celula y en la casilla anterior, la elimino, o a NULL
			boolean movido = this.superficie.moverCelula(casillasOcupadas[i].getFilas(), casillasOcupadas[i].getColumnas(),
					casillaAleatroia.getFilas(), casillaAleatroia.getColumnas());
			if(movido){
				
				
				System.out.println("Movimiento de ("+casillasOcupadas[i].getFilas() +","+ casillasOcupadas[i].getColumnas()+")"+
				" a ("+ casillaAleatroia.getFilas() +","+ casillaAleatroia.getColumnas()+")\n");
				//preguntar si va a tener hijos o no
						
							 //hay posiciones libres
				 if (this.superficie.seRepreoduce(casillaAleatroia.getFilas(), casillaAleatroia.getColumnas())){	

                             System.out.println("Nace nueva celula en ("+casillasOcupadas[i].getFilas() +","+ casillasOcupadas[i].getColumnas()+")"+
									" cuyo padre a sido ("+ casillaAleatroia.getFilas() +","+ casillaAleatroia.getColumnas()+")\n");					
							  if( numCasillaOcupadas == 0){
								  this.superficie.seMuere(casillasOcupadas[i].getFilas(), casillasOcupadas[i].getColumnas());
								  System.out.print("Muere la celula de la casilla, ("+ casillasOcupadas[i].getFilas()+","+casillasOcupadas[i].getColumnas()+" por no poder reproducirse.\n");
							  }

						}
				//System.out.println(toString());
				//
			}// sino se ha movido, quito max pasos sin moverse
			else{		
				this.superficie.matarCelula(casillasOcupadas[i].getFilas(), casillasOcupadas[i].getColumnas());
				System.out.println("No se ha realizado ningun movimiento, la celula muere por inactividad");}
			
			

			
			/*2.- FALTA POR CONTROLAR LOS PASOS SIN MOVER, CUANDO NACE NUEVA CELULA*/
     }
	 
  }

public int numCasillasOcupadas(Casilla[] casillaLibrePorCelula){
	int ocupadas=0;
	for(int i=0; i<casillaLibrePorCelula.length;i++){
		if(casillaLibrePorCelula[i] != null){
			ocupadas++;
		}
	}
	return ocupadas;
}

public Casilla obtenerCasillaAleatoria(Casilla[] casillaLibrePorCelula, int posCasillaSelecciona){
	
	Casilla casilla = null;
	int longitud=casillaLibrePorCelula.length;
	for(int i=0; i < longitud;i++){
		if(i == posCasillaSelecciona){//obtengo posicion
			casilla = new Casilla(casillaLibrePorCelula[i].getFilas(), casillaLibrePorCelula[i].getColumnas());
		break;
		}
	}
	return casilla;
}
	
public int seleccionarCasilla(int numOcupadas){
	
	int posicionElegida = (int) (Math.random() * numOcupadas);
	return posicionElegida;
}	
//encuentro la casilla, primero valido el rango y devuelvo la casilla
public Casilla[] buscarPosicionesLibres(int fil, int col){
	 
	Casilla[] casillaLibreEncontrada = new Casilla[9];

   int posCasilla = 0; 
	 //busco la posicion libre para mi celula
	 for(int c = col-1; c <= col+1; c++){ //colum
		 for(int f = fil-1; f <= fil+1; f++){ //fila
			 //metodo superficie
			 if( (esValido(f, c) ) && (this.superficie.buscarPosicionLibre(f,c))){
				  //creo casilla
				 casillaLibreEncontrada[posCasilla]= new Casilla(f,c);
				 posCasilla++;
				 //System.out.print("Casilla libre:" + f +"-"+c+"\n");
				 
			 }
			 
		 }
	 } 
	 
 return casillaLibreEncontrada;
}


public boolean esValido(int f, int c){
	 
 boolean posValida = false;
	if ( (0 <= f) && (f < this.superficie.getFilas())) {
		if ( (0 <= c) && (c < superficie.getColumnas())) {
			posValida = true;
	      }
	 }
	 
  return posValida;
	
}


 @Override
public String toString() {
	return ""+getSuperficie()+"";
}

public void vaciarMundo(){
	
	this.superficie.vaciar();
}


public Superficie getSuperficie() {
	return superficie;
}

public void setSuperficie(Superficie superficie) {
	this.superficie = superficie;
}





}
