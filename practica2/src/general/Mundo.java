package general;

public class Mundo {

	private Superficie superficie;
	private int filas = 3;
	private int columnas = 4;
	private final int NUM_CELULAS = 6;

	/**
	 * Constructor sin paámetros, crea la superficie e inicia con el MAX DE
	 * CELULAS
	 */
	public Mundo() {
		// Superficie superficie = new Superficie(3,4); //enunciado
		this.superficie = new Superficie(filas, columnas);
		// inicializa
		this.superficie.iniciar(NUM_CELULAS);

	}

	/**
	 * Crea la celula en la posicion x e y, indicada, si es valida la posicion
	 * devuelve true
	 * */
	public boolean crearCelula(int f, int c) {

		boolean creado = this.superficie.crearCelula(f, c);
		// obtener f y c - ver si esa posicion está libre
		return creado;
	}

	/**
	 * Elimina posicion, busca al posición y si existe la elimina de la
	 * superficie
	 */
	public boolean eliminarCelula(int f, int c) {
		boolean eliminado = this.superficie.eliminarCelula(f, c);
		return eliminado;
	}

	/**
	 * Método que llama el controlador, cuando el usuario llamo a la función
	 * moverCelula con los datos de la Casilla Seleccionada //controlar los
	 * movimientos para la celula //Obtengo casiilas libres correspondiente a la
	 * celula - ahora tengo //Decidi a qué casilla tengo se mueve //Seleccionar
	 * casilla a mover aleatoria con random //En la casilla selecciona, creo la
	 * celula y en la casilla anterior, la elimino, o a NULL Si tengo celulas
	 * libre y no me puedo mover, modifica MAX PASOS MOVER Si puedo mover y
	 * reproducir, creo hija y padre
	 */
	public void evolucina() {

		// para validad posiciones y obtener casillas libres segun la posicion
		// de la celula en la superficie
		Casilla[] casillaLibrePorCelula = new Casilla[12];
		// aqui tengo todas las casillas que estan ocupadas en la superficie
		Casilla[] casillasOcupadas = this.superficie
				.encontrarCelulasSuperficie();
		// recorro el array para buscar los posiciones libres para cada celula
		// int longOcupadas = numCasillasOcupadas(casillasOcupadas);
		int numCasillaLibrePorCelula = 0;
		int posCasillaSelecciona = 0;
		boolean seReproduce = true;

		// int maxCelulaTablero=12;

		for (int i = 0; i < NUM_CELULAS; i++) {

			// obtengo las casillas libres

			casillaLibrePorCelula = this.buscarPosicionesLibres(
					casillasOcupadas[i].getFilas(),
					casillasOcupadas[i].getColumnas());
			numCasillaLibrePorCelula = this
					.numCasillasOcupadas(casillaLibrePorCelula);

			// No hay casillas libres y no se puede reproducir
			if (numCasillaLibrePorCelula == 0
					&& this.superficie.validarReproduccion(
							casillasOcupadas[i].getFilas(),
							casillasOcupadas[i].getColumnas())) {

				this.superficie.seMuere(casillasOcupadas[i].getFilas(),
						casillasOcupadas[i].getColumnas());
				System.out.print("Muere la celula de la casilla, ("
						+ casillasOcupadas[i].getFilas() + ","
						+ casillasOcupadas[i].getColumnas()
						+ ") por no poder reproducirse.\n");

			}

			// No tiene casillas libres y aún tengo oportunidad de movimiento
			else if (numCasillaLibrePorCelula == 0
					&& !this.superficie.validarPasosSinMover(
							casillasOcupadas[i].getFilas(),
							casillasOcupadas[i].getColumnas())) {
				this.superficie.seMuere(casillasOcupadas[i].getFilas(),
						casillasOcupadas[i].getColumnas());
				System.out.print("Muere la celula de la casilla, ("
						+ casillasOcupadas[i].getFilas() + ","
						+ casillasOcupadas[i].getColumnas()
						+ ") por inactividad.\n");

			}

			// si tengo posiciones libres para la celula i
			if (numCasillaLibrePorCelula > 0) {

				// obtengo las casillas libres para la celula i
				numCasillaLibrePorCelula = this
						.numCasillasOcupadas(casillaLibrePorCelula);

				// obtengo la posicion aleatoria a donde me voy a mover
				posCasillaSelecciona = seleccionarCasilla(numCasillaLibrePorCelula);

				// acceder a la posicion de la casillaSelecciona para obtener la
				// Casilla
				// voy a obtener la fila y colum de la casilla aleatoria
				// seleccionada

				Casilla casillaAleatroia = obtenerCasillaAleatoria(
						casillaLibrePorCelula, posCasillaSelecciona);

				// se reproduce
				seReproduce = this.superficie.seRepreoduce(
						casillasOcupadas[i].getFilas(),
						casillasOcupadas[i].getColumnas(),
						casillaAleatroia.getFilas(),
						casillaAleatroia.getColumnas());

				if (seReproduce) {// tengo que repruducirme
					System.out.println("Movimiento de ("
							+ casillasOcupadas[i].getFilas() + ","
							+ casillasOcupadas[i].getColumnas() + ")" + " a ("
							+ casillaAleatroia.getFilas() + ","
							+ casillaAleatroia.getColumnas() + ")\n");
					System.out.println("Nace nueva celula en ("
							+ casillasOcupadas[i].getFilas() + ","
							+ casillasOcupadas[i].getColumnas() + ")"
							+ " cuyo padre a sido ("
							+ casillaAleatroia.getFilas() + ","
							+ casillaAleatroia.getColumnas() + ")\n");

					if (numCasillaLibrePorCelula == 0) {
						this.superficie.seMuere(casillasOcupadas[i].getFilas(),
								casillasOcupadas[i].getColumnas());
						System.out.print("Muere la celula de la casilla, ("
								+ casillasOcupadas[i].getFilas() + ","
								+ casillasOcupadas[i].getColumnas()
								+ " por no poder reproducirse.)\n");
					}
				} else { // nose reproduce
					boolean movido = this.superficie.moverCelula(
							casillasOcupadas[i].getFilas(),
							casillasOcupadas[i].getColumnas(),
							casillaAleatroia.getFilas(),
							casillaAleatroia.getColumnas());
					// consultos solo los movimientos
					if (movido) {
						/** no se movido pero tiene casillas libres */
						System.out.println("Movimiento de ("
								+ casillasOcupadas[i].getFilas() + ","
								+ casillasOcupadas[i].getColumnas() + ")"
								+ " a (" + casillaAleatroia.getFilas() + ","
								+ casillaAleatroia.getColumnas() + ")\n");
					}

					else {
						this.superficie.seMuere(casillasOcupadas[i].getFilas(),
								casillasOcupadas[i].getColumnas());
						System.out.print("Muere la celula de la casilla, ("
								+ casillasOcupadas[i].getFilas() + ","
								+ casillasOcupadas[i].getColumnas()
								+ ") por inactividad.\n");

					}

				}// else movido

			}

			//System.out.println(this.superficie.toString());

		}
	}

	/**
	 * Devuelve el numero de casillas ocupadas para la i casilla enviada
	 * 
	 * */

	public int numCasillasOcupadas(Casilla[] casillaLibrePorCelula) {
		int ocupadas = 0;
		for (int i = 0; i < casillaLibrePorCelula.length; i++) {
			if (casillaLibrePorCelula[i] != null) {
				ocupadas++;
			}
		}
		return ocupadas;
	}

	/**
	 * Devuelva la casilla (datos )aleatoria seleccionada Recorre con el for
	 * hasta que posCasillaSelecciona(posicion) sea igual a i
	 * 
	 * */
	public Casilla obtenerCasillaAleatoria(Casilla[] casillaLibrePorCelula,
			int posCasillaSelecciona) {

		Casilla casilla = null;
		int longitud = casillaLibrePorCelula.length;
		for (int i = 0; i < longitud; i++) {
			if (i == posCasillaSelecciona) {// obtengo posicion
				casilla = new Casilla(casillaLibrePorCelula[i].getFilas(),
						casillaLibrePorCelula[i].getColumnas());
				break;
			}
		}
		return casilla;
	}

	/**
	 * Devuelve la posicion de la casilla aleatoria elejida
	 * */
	public int seleccionarCasilla(int numOcupadas) {

		int posicionElegida = (int) (Math.random() * numOcupadas);
		return posicionElegida;
	}

	/**
	 * Devuelve un array de Casillas y busca las posiciones libres para esa
	 * casilla, con x e y
	 * */
	public Casilla[] buscarPosicionesLibres(int fil, int col) {

		Casilla[] casillaLibreEncontrada = new Casilla[9];

		int posCasilla = 0;
		// busco la posicion libre para mi celula
		for (int c = col - 1; c <= col + 1; c++) {
			for (int f = fil - 1; f <= fil + 1; f++) {
				if ((esValido(f, c))
						&& (this.superficie.buscarPosicionLibre(f, c))) {
					// creo casilla
					casillaLibreEncontrada[posCasilla] = new Casilla(f, c);
					posCasilla++;

				}

			}
		}

		return casillaLibreEncontrada;
	}

	/**
	 * Valido si la posición es válido, para los extremos, es muy importante
	 */
	public boolean esValido(int f, int c) {

		boolean posValida = false;
		if ((0 <= f) && (f < this.superficie.getFilas())) {
			if ((0 <= c) && (c < superficie.getColumnas())) {
				posValida = true;
			}
		}

		return posValida;

	}

	public String toString() {
		return "" + getSuperficie() + "";
	}

	public void vaciarMundo() {

		this.superficie.vaciar();
	}

	public Superficie getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Superficie superficie) {
		this.superficie = superficie;
	}

}
