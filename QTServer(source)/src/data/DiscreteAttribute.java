package data;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Classe DiscreteAttribute.
 */
public class DiscreteAttribute extends Attribute implements Iterable<String> {

	/**
	 * array di oggetti String, uno per ciascun valore del dominio discreto. I
	 * valori del dominio sono memorizzati in values seguendo un ordine
	 * lessicografico.
	 */
	private TreeSet<String> values;

	/**
	 * Istanzia un nuovo attributo discreto
	 *
	 * @param name
	 *            Nome attributo
	 * @param index
	 *            Identificativo numerico
	 * @param values
	 *            Valore
	 */
	public DiscreteAttribute(String name, int index, TreeSet<String> values) {
		super(name, index);
		this.values = values;
	}

	/**
	 * Restituisce la grandezza dei valori nel TreeSet
	 *
	 */
	public int getNumberOfDistinctValues() {
		return values.size();
	}

	/**
	 * Restituisce il valore di un attributo
	 *
	 */
	public TreeSet<String> getValues() {
		return this.values;
	}

	/**
	 * Restituisce un iteratore di stringhe
	 */
	public Iterator<String> iterator() {
		return values.iterator();
	}

}
