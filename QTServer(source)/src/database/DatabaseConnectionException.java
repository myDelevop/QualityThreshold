package database;

// TODO: Auto-generated Javadoc
/**
 * Classe DatabaseConnectionException che estende la classe Exception
 */
public class DatabaseConnectionException extends Exception {

	/**
	 * Istanziazione della eccezzione.
	 */
	public DatabaseConnectionException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String errore = "Connessione al database fallita!";
		return errore;
	}

}
