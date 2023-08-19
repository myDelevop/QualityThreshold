package data;

// TODO: Auto-generated Javadoc
/**
 * Classe TypeDistanceException. Per gestire l'eccezzione in caso il tipo di
 * distanza non è supportato
 */
public class TypeDistanceException extends Exception {

	/**
	 * Istanzia un nuovo tipo di TypeDistanceException.
	 */
	public TypeDistanceException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String error = "Tipo di distanza incompatibile (scegliere edit o euclidea)";
		return error;
	}

}
