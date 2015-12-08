package general;

public class Celula {

	private int pasosSinMover;
	private int pasosDados;
	private final int MAX_PASOS_SIN_MOVER;
	private final int PASOS_REPRODUCCION;

	public Celula(int pasosSinMover, int pasosReproducción) {
		this.MAX_PASOS_SIN_MOVER = pasosSinMover; /* 1 */
		this.PASOS_REPRODUCCION = pasosReproducción; /* 2 */
		// inicializo a 0
		this.pasosSinMover = this.MAX_PASOS_SIN_MOVER;
		this.pasosDados = this.PASOS_REPRODUCCION;
	}

	public int getPasosSinMover() {
		return pasosSinMover;
	}

	public void setPasosSinMover(int pasosSinMover) {
		this.pasosSinMover = pasosSinMover;
	}

	public int getPasosDados() {
		return pasosDados;
	}

	public void setPasosDados(int pasosDados) {
		this.pasosDados = pasosDados;
	}

	@Override
	public String toString() {

		return "" + MAX_PASOS_SIN_MOVER + "-" + PASOS_REPRODUCCION + "  ";
	}

}
