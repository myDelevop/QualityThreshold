package database;

// TODO: Auto-generated Javadoc
/**
 * Classe EmptySetException che estende la classe Exception.
 */
public class EmptySetException extends Exception {

	/**
	 * Istanzia EmptySetException.
	 */
	public EmptySetException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String errore = "empty resultset!";
		return errore;
	}

}
