package database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Classe Example.
 */
public class Example implements Comparable<Example>, Serializable {

	/** Lista di oggetti. */
	private List<Object> example = new ArrayList<Object>();

	/**
	 * Aggiunge l'oggetto alla lista
	 *
	 * @param o
	 *            object
	 */
	public void add(Object o) {
		example.add(o);
	}

	/**
	 * Restituisce l'oggetto in posizione i della lista.
	 *
	 * @param i
	 *            posizione
	 * @return the object
	 */
	public Object get(int i) {
		return example.get(i);
	}

	/**
	 * Confronta gli oggetti dell'Example passato come parametro, con tutti gli
	 * oggetti della lista.
	 * 
	 * @return 1 se gli oggetti sono diversi, 0 altrimenti
	 */
	public int compareTo(Example ex) {

		int i = 0;
		for (Object o : ex.example) {
			if (!o.equals(this.example.get(i)))
				return ((Comparable) o).compareTo(example.get(i));
			i++;
		}
		return 0;
	}

	/**
	 * Stampa la lista
	 */
	public String toString() {
		String str = "";
		for (Object o : example)
			str += o.toString() + " ";
		return str;
	}

}