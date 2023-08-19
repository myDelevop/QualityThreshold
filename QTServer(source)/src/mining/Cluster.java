package mining;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import data.Data;
import data.Tuple;
import data.TypeDistanceException;

// TODO: Auto-generated Javadoc
/**
 * Classe Cluster.
 */
class Cluster implements Iterable<Integer>, Comparable<Cluster>, Serializable {

	/** Centroide. */
	private Tuple centroid;

	/** Set di interi. */
	private Set<Integer> clusteredData = new HashSet<Integer>();

	/**
	 * Istanzia un nuovo cluster.
	 */
	Cluster() {
	}

	/**
	 * Istanzia un nuovo Cluster.
	 *
	 * @param centroid
	 *            il centroide
	 */
	Cluster(Tuple centroid) {
		this.centroid = centroid;
		clusteredData = new HashSet<Integer>();

	}

	/**
	 *
	 * @return il centroide
	 */
	Tuple getCentroid() {
		return centroid;
	}

	// return true if the tuple is changing cluster
	/**
	 * Adds the data.
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	boolean addData(int id) {
		return clusteredData.add(id);

	}

	/**
	 * verifica se una transazione è clusterizzata nell'array corrente
	 *
	 * @param id
	 *            the id
	 * @return true, if successful
	 */
	boolean contain(int id) {
		return clusteredData.contains(id);
	}

	//
	/**
	 * rimuove la tupla che ha cambiato il cluster
	 *
	 * @param id
	 *            the id
	 */
	void removeTuple(int id) {
		clusteredData.remove(id);

	}

	/**
	 *
	 * @return la dimensione del Set
	 */
	int getSize() {
		return clusteredData.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<Integer> iterator() {
		return clusteredData.iterator();
	}

	/**
	 * Stampa il centroide
	 */
	public String toString() {
		String str = "Centroid=(";
		for (int i = 0; i < centroid.getLength(); i++)
			str += " " + centroid.get(i);
		str += " )";
		return str;

	}

	/**
	 * Stampa gli Example relativi al centroide
	 *
	 * @param data
	 *            Data
	 * @param typeDistance
	 *            tipo di distanza
	 * @return gli Example relativi al centroide
	 */
	public String toString(Data data, String typeDistance)
			throws TypeDistanceException {
		String str = "\nExamples:\n";

		try {
			for (Integer i : clusteredData) {
				str += "[";
				for (int j = 0; j < data.getNumberOfAttributes(); j++) {
					str += data.getAttributeValue(i, j) + " ";
				}

				str += "] dist="
						+ getCentroid().getDistance(data.getItemSet(i),
								getCentroid(), typeDistance) + "\n";
			}
			str += "\nAvgDistance="
					+ getCentroid().avgDistance(data, getCentroid(),
							clusteredData, typeDistance);
		} catch (TypeDistanceException e) {
			throw new TypeDistanceException();
		}

		return str;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Cluster a) {
		// TODO Auto-generated method stub
		// DEVE COMPARARE DUE CLUSTER IN BASSE ALLA POPOLOSITA', RESTITUENDO -1
		// O +1

		if (Integer.compare(clusteredData.size(), a.getSize()) <= 0)
			return -1;
		else
			return 1;

	}
}
