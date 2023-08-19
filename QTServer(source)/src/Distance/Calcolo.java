package Distance;

// TODO: Auto-generated Javadoc
/**
 * Classe Calcolo.
 */
public class Calcolo {

	/** Inizializza i di tipo ImplDistance. */
	static ImplDistance i = new ImplDistance();

	/**
	 * Restituisce l interfaccia per il calcolo della distanza euclidea
	 *
	 * @return distanza
	 */
	public static IntDistanceEuclidea distanceE() {
		return i;
	}

	/**
	 * Restituisce l interfaccia per il calcolo della distanza Edit
	 *
	 * @return distanza
	 */
	public static IntDistanceEdit distanceEdit() {
		return i;
	}

}
