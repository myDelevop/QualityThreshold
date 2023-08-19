package data;

// TODO: Auto-generated Javadoc
/**
 * Classe RadiusException che gestisce l'eccezzione in caso di raggio <=0.
 */
public class RadiusException extends Exception {

	/**
	 * Istanzia un nuovo RadiusException.
	 */
	public RadiusException() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		String error = "Il radius deve essere >0";
		return error;
	}

}
