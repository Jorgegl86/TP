package tp.pr1;

import java.util.Random;


public class Superficie {

	private Celula[][] superficie;                                      
	//determinan el tamaño de la superficie
	
	private int filas;
	private int columnas;


	private final int N=1; //pasos sin moverse
	private final int M=2;//pasos para reproducirse
    
	
	
	public Superficie(int nf, int nc){
		
		this.filas = nf;
		this.columnas = nc;
		this.superficie = new Celula[filas][columnas];
		//vaciamos
		this.vaciar();
		
		
	}
	
	public void iniciar(int numeroCelulas){
		 
		// System.out.print("metodo inicializar");		
			Random rnd1 = new Random();
			Random rnd2 = new Random();
			
			int i =0;
			//genero el num de celulas aleatorios en las posiciones
			while(i < numeroCelulas){
				int fila = rnd1.nextInt(10) % this.filas;
				int colum= rnd2.nextInt(10) % this.columnas;
	             
				 if(superficie[fila][colum] == null){
					 //rellenamos y creamos celulas
					 superficie[fila][colum] = new Celula(this.N, this.M);
					 i++;
					 
				 }
	 
			}
		}
		
 public void vaciar(){
		for (int i=0; i < filas; i++){
			for (int j=0; j < columnas; j++){
					superficie[i][j] = null;
				}
			}
		
}	
	 

 //metodo del enunciado
 public boolean crearCelula(int f, int c){
	 //creo casilla en la posicion
	 boolean crear = true;
	 
	if(buscarPosicionLibre(f,c)){ //casilla libre
		//creo celula
		this.superficie[f][c] = new Celula(this.N, this.M);
	 }
	else{ 
		  crear=false;
	      System.out.print("No se creó la celula. Posición ocupada");
	}
	 
	 return crear;
	 
 }
 //metodo del enunciado
 public boolean eliminarCelula(int f, int c){
	 boolean eliminado = true;
	if(existePosicion(f, c)){
		 this.superficie[f][c] = null;
	 }
	else{
		System.out.print("No hay células en dicha posición. No se realizó ninguna acción.");
	    eliminado = false;  
	}
	return eliminado;
 }
	 

 public boolean existePosicion(int f, int c){
		 boolean existe =  false;
		 
		 for (int i=0; i < filas; i++){
			 for (int j=0; j < columnas; j++){
					if((i==f) && (j==c)){
						existe=true;
						break;//romper
				  }
				}
		 }
		 
		 return existe;
	 }
	 
public boolean buscarPosicionLibre(int f, int c){
		 
		 boolean libre = false;
		  if( existePosicion(f, c)){//si es verdadero, la posición es libre, ya no recorreo voy directo
			 if(this.superficie[f][c] == null){
				libre=true;
				}
			 //else { System.out.print("La posición está ocupada.\n");}
			}
			//la posición no existe --
		 else { System.out.print("La posición no existe\n"); }
		return libre;
	 }
	 
	 
	
/*****************************************************
 * **************************************************
METODO EVELOCIONA */
//metodo para recorrer la superificie y coger la posición de la celula f,c

	 /*CONTROLAR ESTA PARTE*/
public Casilla[] encontrarCelulasSuperficie(){
	
	Casilla[] casillas = new Casilla[9];
	Casilla casillaConCelula = null;
//	boolean encontrado = false;
	int i=0; //para las posiciones del array de Casillas
	
	for(int fm = 0; fm < this.filas; fm++){
		for(int cm = 0; cm < this.columnas; cm++){
			
			if(this.superficie[fm][cm] != null){//hay una celula
				 //buscar posiciones libres en Casilla
              //crear casilla
				casillaConCelula = new Casilla(fm,cm);
				//añado al array de casilla
				casillas[i] = casillaConCelula; //???
				i++;
			
			   //System.out.print("La celula se encuentra en la "+ fm + " - "+cm+ "\n ");
			}
			
		}
	}
	
  return  casillas;  //tengo posicion 
}

public boolean moverCelula(int filaEliminar, int columEliminar, int filPoner, int colPoner){
	
	//pon celula
	//obtener la celula de la posicion y fila dada
	//disminuir los datos de la celula
	
	/*
	 this.MAX_PASOS_SIN_MOVER=pasosSinMover;
		this.PASOS_REPRODUCCION= pasosReproducción;

	*/
	boolean movido = true;
	int pasosDados;
	    if(movido){
			//muevo la celula con los valores modificados, la obtengo con los valores a eliminar
			Celula celula = this.superficie[filaEliminar][columEliminar];
			
			if (celula.equals("null")){
				System.out.println("Problema con la posicion de las celulas en la superficie ");
				movido = false;
			 }
		    else{
			//modifico los valores
				pasosDados = celula.getPasosDados() - 1;
					if(pasosDados < 0){
						
						//en la fila eliminar me creo una nueva celula
						this.eliminarCelula(filaEliminar, columEliminar);
						//creo con nuevos valores
						this.crearCelula(filaEliminar, columEliminar);
						//pongo celula en la posicion aleatoria
						this.crearCelula(filPoner, colPoner);
						movido = true;
					 }
					else{
					
						celula.setPasosDados(pasosDados);//modifico la celula
						//controlar pasos sin mover
						this.superficie[filPoner][colPoner] = new Celula(celula.getPasosSinMover(), celula.getPasosDados());
						//eliminar celula 
				
							if(this.superficie[filaEliminar][columEliminar] == null){
								movido =false;
								System.out.println("Problema con eliminar la posición a la hora de mover ");
							 }else{
								 	this.superficie[filaEliminar][columEliminar] = null;}
		       
					}
           
	      }
	   }
 return movido;
}

public boolean seRepreoduce(int filaAcc, int colAcc){
	
	boolean nace = false;
	Celula celula = this.superficie[filaAcc][colAcc];
	 
	int pasosDados= celula.getPasosDados();
	 if(pasosDados < 0){
		 //la celula se tiene que reproducir
		 nace = true;
	 }

	return nace;

}

//le preparamos para morir en la siguiente iteración
public boolean matarCelula(int filMatar, int colMatar){
	boolean matar = true;
	int pasosSinMover;
	
	Celula celula = this.superficie[filMatar][colMatar];
	
	if (celula.equals("null")){
		System.out.println("Problema con la posicion de las celulas en la superficie ");
		matar= false;
	 }
    else{
    	pasosSinMover = celula.getPasosSinMover() - 1;
    	//editar los pasos sin mover
    	celula.setPasosSinMover(pasosSinMover);
    	matar = true;
    }

	return matar;
}
 
public boolean seMuere(int filMuere, int colMuere){
	 this.superficie[filMuere][colMuere] = null;
	 return true;

}
  public String toString(){
	 StringBuffer cadena = new StringBuffer();
	 for (int i=0; i < filas; i++){
			for (int j=0; j < columnas; j++){
			 cadena.append(superficie[i][j]);
			 }
			cadena.append("\n");
		}
	return cadena.toString(); 
}
  
  
	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

}
