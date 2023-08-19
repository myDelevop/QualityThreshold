package database;

import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * Classe InexistentTableException che estende la classe SQLException. Ritorna
 * messaggio di errore se la tabella non esiste
 */
public class InexistentTableException extends SQLException {

	/**
	 * Istanzia una nuova InexistentTableException.
	 */
	public InexistentTableException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String error = "Tabella inesistente nel Database";
		return error;
	}
}
