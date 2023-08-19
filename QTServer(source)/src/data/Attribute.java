package data;

import java.io.Serializable;

/**
 * Classe astratta Attribute
 */
public abstract class Attribute implements Serializable {

	/** Elementi attributo */
	/** Nome dell'attributo */
	protected String name;

	/** Identificativo numerico dell'attributo */
	protected int index;

	/**
	 * Costruttore Attribute
	 * 
	 * Istanzio un nuovo Attribute
	 *
	 * @param Nome
	 *            attributo
	 * @param Identificativo
	 *            numerico
	 */
	public Attribute(String name, int index) {
		this.name = name;
		this.index = index;
	} //

	/**
	 * Prendi nome dell'attributo
	 *
	 * @return the name
	 */
	String getName() {
		return name;
	} // nome dell'attributo

	/**
	 * Prendi Identificativo numerico dell'attributo
	 *
	 * @return the index
	 */
	int getIndex() {
		return index;
	} // identificativo numerico dell'attributo

	/**
	 * Metodo ToString
	 * 
	 * Torna una stringa rappresentate lo stato dell'oggetto
	 */
	public String toString() {
		return name;
	} // string rappresentante lo stato dell'oggetto
}
