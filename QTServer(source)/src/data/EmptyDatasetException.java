package data;

/**
 * Classe EmptyDatasetException.
 */
public class EmptyDatasetException extends Exception {

	/**
	 * Istanzio una nuova EmptyDataSetException
	 */
	public EmptyDatasetException() {
	}

	/**
	 * In caso di eccezione, restituisce la stringa di errore
	 */
	@Override
	public String getMessage() {
		String errore = "Il Dataset è vuoto!";
		return errore;
	}
}
