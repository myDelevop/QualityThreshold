package mining;

// TODO: Auto-generated Javadoc
/**
 * Classe ClusteringRadiusException estende la classe Exception.
 */
public class ClusteringRadiusException extends Exception {

	/**
	 * Istanzia un nuovo ClusteringRadiusException.
	 */
	public ClusteringRadiusException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String errore = "14 tuples in one cluster";
		return errore;
	}
}
