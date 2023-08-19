package mining;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import data.Data;
import data.TypeDistanceException;

// TODO: Auto-generated Javadoc
/**
 * Classe ClusterSet.
 */
public class ClusterSet implements Iterable<Cluster>, Serializable {

	/** Set di Cluster. */
	private Set<Cluster> C = new TreeSet<Cluster>();

	/**
	 * Istanzia un nuovo set di Cluster.
	 */
	ClusterSet() {
	};

	/**
	 * Aggiunge un nuovo Cluster al Set
	 *
	 * @param c
	 *            the c
	 */
	void add(Cluster c) {

		C.add(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = "";
		int d = 0;
		for (Cluster c : C) {
			d++;
			str += d + ":" + c.toString() + "\n";
		}

		return str;
	}

	/**
	 * Stampa il Set di cluster in base al tipo di distanza (edit o euclidea)
	 * scelto
	 *
	 * @param data
	 *            the data
	 * @param typeDistance
	 *            the type distance
	 * @return the string
	 */
	public String toString(Data data, String typeDistance)
			throws TypeDistanceException {
		String str = "";
		int i = 0;

		for (Cluster cluster : C) {
			if (cluster != null) {
				try {
					str += i + ":" + cluster.toString()
							+ cluster.toString(data, typeDistance) + "\n";
				} catch (TypeDistanceException e) {
					throw new TypeDistanceException();
				}
				i++;
			}

		}
		return str;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Cluster> iterator() {
		// TODO Auto-generated method stub

		return C.iterator();
	}
}
