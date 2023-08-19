package data;

import java.io.Serializable;

/**
 * Classe Item.
 */
public abstract class Item implements Serializable {

	/** Dichiarazione elementi di Item */
	Attribute attribute;

	Object value;

	/**
	 * Istanzia un nuovo Item
	 *
	 * @param attribute
	 *            Attributo
	 * @param value
	 *            Valore
	 */
	public Item(Attribute attribute, Object value) {
		this.attribute = attribute;
		this.value = value;
	}

	/**
	 * Restituisce l'attributo
	 *
	 * @return the attribute
	 */
	public Attribute getAttribute() {
		return this.attribute;
	}

	/**
	 * Restituisce il valore corrispondente all'attributo
	 *
	 * @return the value
	 */
	public Object getValue() {
		return this.value;

	}

	/**
	 * Stampa il valore ottenuto
	 */
	public String toString() {
		if (value instanceof Double)
			return ((Double) value).toString();
		else
			return (String) value;
	}

	// abstract double distance(Object a);

}
