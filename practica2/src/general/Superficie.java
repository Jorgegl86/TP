package general;

import java.util.Random;

public class Superficie {

	private Celula[][] superficie;
	// determinan el tamaño de la superficie
	private int filas;
	private int columnas;

	private final int N = 1; // pasos sin moverse
	private final int M = 2;// pasos para reproducirse

	public Superficie(int nf, int nc) {

		this.filas = nf;
		this.columnas = nc;
		this.superficie = new Celula[filas][columnas];
		// vaciamos
		this.vaciar();

	}

	/**
	 * Inicia el juego, simulación con el num max de celula, que puede haber en
	 * la superficie Se crean las celulas aleatoriamente, en las posiciones de
	 * la superficie
	 * */
	public void iniciar(int numeroCelulas) {

		Random rnd1 = new Random();
		Random rnd2 = new Random();

		int i = 0;
		// genero el num de celulas aleatorios en las posiciones
		while (i < numeroCelulas) {
			int fila = rnd1.nextInt(10) % this.filas;
			int colum = rnd2.nextInt(10) % this.columnas;

			if (superficie[fila][colum] == null) {
				// rellenamos y creamos celulas
				superficie[fila][colum] = new Celula(this.N, this.M);
				i++;

			}

		}
	}

	/**
	 * Metodo que pone a null todas las casillas de la superfice Metodo para la
	 * instrucción del usuario
	 */
	public void vaciar() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				superficie[i][j] = null;
			}
		}

	}

	/**
	 * Devuelve true si he creado la celula correctamente Busco si hay
	 * posiciones libres, si hay, creo la celula Si no, devuelvo false
	 * */
	// metodo del enunciado
	public boolean crearCelula(int f, int c) {
		// creo casilla en la posicion
		boolean crear = true;

		if (buscarPosicionLibre(f, c)) { // casilla libre
			// creo celula
			this.superficie[f][c] = new Celula(this.N, this.M);
		} else {
			crear = false;
			System.out.print("No se creó la celula en la posición indicada.\n");
		}

		return crear;

	}

	/**
	 * Si he encontrado la celula segun la posicion dado, elimino la celula y
	 * devuelvo true
	 */
	// metodo del enunciado
	public boolean eliminarCelula(int f, int c) {
		boolean eliminado = true;
		if (existePosicion(f, c)) {
			this.superficie[f][c] = null;
		} else {
			System.out
					.print("No se ha eliminado ninguna célula, la posicion no existe.");
			eliminado = false;
		}
		return eliminado;
	}

	/** Pregunto si en la posicion f y c de la superficie existe una celula */
	public boolean existePosicion(int f, int c) {
		boolean existe = false;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if ((i == f) && (j == c)) {
					existe = true;
					break;// romper
				}
			}
		}

		return existe;
	}

	/**
	 * Si la posicion f c, está vacia
	 */
	public boolean buscarPosicionLibre(int f, int c) {

		boolean libre = false;
		if (existePosicion(f, c)) {// si es verdadero, la posición es libre, ya
									// no recorreo voy directo
			if (this.superficie[f][c] == null) {
				libre = true;
			}
		}
		// la posición no existe --
		else {
			System.out.print("La posición no existe\n");
		}
		return libre;
	}

	/* METODO EVOLUCIONA */

	/** Devuelve un array de casillas, con los datos posiciones en la superficie */
	public Casilla[] encontrarCelulasSuperficie() {

		Casilla[] casillas = new Casilla[12];
		Casilla casillaConCelula = null;
		// boolean encontrado = false;
		int i = 0; // para las posiciones del array de Casillas

		for (int fm = 0; fm < this.filas; fm++) {
			for (int cm = 0; cm < this.columnas; cm++) {

				if (this.superficie[fm][cm] != null) {// hay una celula
					// buscar posiciones libres en Casilla
					// crear casilla
					casillaConCelula = new Casilla(fm, cm);
					// añado al array de casilla
					casillas[i] = casillaConCelula;
					i++;
				}

			}
		}

		return casillas;
	}

	/**
	 * Devuelve true si, hay celula en la posicion indicada, si numPasosDados es
	 * 0 Si numPasosDados es < 0 creo una nueva celula en la posicion a poner
	 * */
	public boolean moverCelula(int filaEliminar, int columEliminar,
			int filPoner, int colPoner) {

		boolean movido = true;
		int numPasosDados;
		if (movido) {
			// muevo la celula con los valores modificados, la obtengo con los
			// valores a eliminar
			Celula celula = this.superficie[filaEliminar][columEliminar];

			if (celula.equals("null")) {
				System.out
						.println("Problema con la posicion de las celulas en la superficie ");
				movido = false;
			} else {
				// modifico los valores
				numPasosDados = celula.getPasosDados() - 1;// modifico la celula
				// modifico la celula
				if (numPasosDados >= 0) {
					celula.setPasosDados(numPasosDados); // modifico
					this.superficie[filPoner][colPoner] = new Celula(
							celula.getPasosSinMover(), celula.getPasosDados());

				}

				if (this.superficie[filaEliminar][columEliminar] == null) {
					movido = false;
					System.out
							.println("Problema con eliminar la posición a la hora de mover ");
				} else {
					this.superficie[filaEliminar][columEliminar] = null;
				}

			}

		}
		return movido;
	}

	/*
	 * Se reproduce cuando, los pasosDados, son 0, creo una celula hija y celula
	 * padre en las posiciones correspondientes
	 */
	public boolean seRepreoduce(int filaHija, int columHija, int filPadre,
			int colPadre) {

		boolean nace = false;
		Celula celula = this.superficie[filaHija][columHija];

		int pasosDados = celula.getPasosDados();
		if (pasosDados == 0) {
			// cuando se mueve ya se elimina
			this.eliminarCelula(filaHija, columHija);
			// creo con nuevos valores
			this.crearCelula(filaHija, columHija);
			// pongo celula en la posicion aleatoria
			this.crearCelula(filPadre, colPadre);
			nace = true;
		}

		return nace;

	}

	/** Metodo que se utiliza para saber, si en este paso, nace nueva celula */
	public boolean validarReproduccion(int filaRepr, int columRepr) {

		boolean validar = false;
		Celula celula = this.superficie[filaRepr][columRepr];
		int pasosDados = celula.getPasosDados();

		if (pasosDados == 0) {
			// se tiene que reproducir
			validar = true;
		}
		return validar;
	}

	/**
	 * Consulta los maximos pasos sin mover de la celula en esa posicion, si
	 * PASOS SIN MOVER es 0, modifico los datos de la celula
	 * */
	public boolean validarPasosSinMover(int filaMov, int columMov) {
		boolean sinMov = true;

		Celula celula = this.superficie[filaMov][columMov];
		int mov = (celula.getPasosSinMover()) - 1;

		if (celula.equals("null")) {
			System.out
					.println("Problema con la posicion de las celulas en la superficie ");
			// sinMov= false;
		} else {
			if (mov == 0) {
				sinMov = true; // me quedo en la misma posicion, edito los
								// pasosMov
				celula.setPasosSinMover(mov);
				this.superficie[filaMov][columMov] = new Celula(mov,
						celula.getPasosDados());

			} else if (mov < 0)
				// elimino por inactividad

				sinMov = false;

		}
		return sinMov;
	}

	/** le preparamos para morir en la siguiente iteración */
	public boolean matarCelula(int filMatar, int colMatar) {
		boolean matar = true;
		int pasosSinMover;

		Celula celula = this.superficie[filMatar][colMatar];

		if (celula.equals("null")) {
			System.out
					.println("Problema con la posicion de las celulas en la superficie ");
			matar = false;
		} else {
			pasosSinMover = celula.getPasosSinMover() - 1;
			// editar los pasos sin mover
			celula.setPasosSinMover(pasosSinMover);
			matar = true;
		}

		return matar;
	}

	/*** Eliminar celula con las posiciones indicadas */
	public boolean seMuere(int filMuere, int colMuere) {
		this.superficie[filMuere][colMuere] = null;
		return true;

	}

	public String toString() {
		StringBuffer cadena = new StringBuffer();
		String vacio = " - ";
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (superficie[i][j] != null)
					cadena.append(superficie[i][j]);
				else
					cadena.append(vacio);
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
