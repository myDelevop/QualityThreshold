package mining;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

import data.EmptyDatasetException;
import data.TypeDistanceException;
import data.Data;

// TODO: Auto-generated Javadoc
/**
 * Classe QTMiner implementa l'algoritmo QT.
 */
public class QTMiner implements Serializable {

	/** Set di cluster. */
	ClusterSet C;

	/** Raggio. */
	double radius;

	/** Tipo di distanza */
	String typedistance;

	/**
	 * Ritorna il tipo di distanza
	 *
	 * @return tipo di distanza
	 */
	public String getTypedistance() {
		return this.typedistance;
	}

	/**
	 * Istanzia un nuovo QTMiner caricando in memoria i centroidi dal file.
	 *
	 * @param fileName
	 *            nome file
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException
	 *             the class not found exception
	 */
	public QTMiner(String fileName) throws FileNotFoundException, IOException,
			ClassNotFoundException {
		FileInputStream inFile = new FileInputStream(fileName);
		ObjectInputStream inStream = new ObjectInputStream(inFile);
		ClusterSet C2 = (ClusterSet) inStream.readObject();

		C = C2;
		inStream.close();
	}

	/**
	 * Crea l'oggetto array riferito da C, inizializza radius e typedistance con
	 * i parametri passati come input
	 *
	 * @param radius
	 *            the radius
	 * @param typedistance
	 *            the typedistance
	 */
	public QTMiner(double radius, String typedistance) {
		C = new ClusterSet();
		this.radius = radius;
		this.typedistance = typedistance;
	}

	/**
	 * Salva in un file di nome filename i centroidi.
	 *
	 * @param fileName
	 *            nome file
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void salva(String fileName) throws FileNotFoundException,
			IOException {
		FileOutputStream outFile = new FileOutputStream(fileName);
		ObjectOutputStream outStream = new ObjectOutputStream(outFile);

		outStream.writeObject(C);
		outStream.close();

	}

	/**
	 * Restituisce il Set di Cluster.
	 *
	 * @return Set di cluster
	 */
	public ClusterSet getC() {
		return C;
	}

	/**
	 * Esegue l’algoritmo QT dell pseudo codice:
	 * 
	 * 1. Costruisce un cluster per ciascuna tupla non ancora clusterizzata,
	 * includendo nel cluster i punti (non ancora clusterizzati in alcun altro
	 * cluster) che ricadano nel vicinato sferico della tuple avente raggio
	 * radius 2. Salva il candidato cluster più popoloso e rimuove tutti punti
	 * di tale cluster dall'elenco delle tuple ancora da clusterizzare 3.
	 * Ritorna al passo 1 finchè ci sono ancora tuple da assegnare ad un cluster
	 *
	 * @param data
	 *            the data
	 * @return the int
	 * @throws ClusteringRadiusException
	 *             the clustering radius exception
	 * @throws EmptyDatasetException
	 *             the empty dataset exception
	 */
	public int compute(Data data) throws ClusteringRadiusException,
			EmptyDatasetException, TypeDistanceException {
		int numclusters = 0;
		int countClustered = 0;
		boolean isClustered[] = new boolean[data.getNumberOfExamples()];
		for (int i = 0; i < isClustered.length; i++)
			isClustered[i] = false;

		while (countClustered != data.getNumberOfExamples()) {
			// Ricerca cluster più popoloso
			try {
				Cluster c = buildCandidateCluster(data, isClustered);
				C.add(c);
				numclusters++;
				// Rimuovo tuple clusterizzate da dataset
				Iterator<Integer> clusteredTupleId = c.iterator();

				// Sostituto
				while (clusteredTupleId.hasNext()) {
					isClustered[clusteredTupleId.next()] = true;
				}

				countClustered += c.getSize();
			} catch (TypeDistanceException e) {
				throw new TypeDistanceException();
			}

		}

		if (numclusters == 1) {
			throw new ClusteringRadiusException();
		} else if (numclusters == 0)
			throw new EmptyDatasetException();

		return numclusters;
	}

	/**
	 * Costruisce un cluster per ciascuna tupla di data non ancora clusterizzata
	 * in un cluster di C e restituisce il clustr candidato più popoloso
	 *
	 * @param data
	 *            the data
	 * @param isClustered
	 *            the is clustered
	 * @return the cluster
	 */
	Cluster buildCandidateCluster(Data data, boolean isClustered[])
			throws TypeDistanceException {

		Cluster candidato = new Cluster();
		ClusterSet candidati = new ClusterSet();

		for (int i = 0; i < isClustered.length; i++) {
			if (isClustered[i] == false) {
				candidato = new Cluster(data.getItemSet(i));

				for (int j = 0; j < isClustered.length; j++) {
					try {
						if (isClustered[j] == false
								&& ((candidato.getCentroid().getDistance(
										candidato.getCentroid(),
										data.getItemSet(j), getTypedistance())) <= radius)) {
							candidato.addData(j);
						}
					} catch (TypeDistanceException e) {
						throw new TypeDistanceException();
					}

				}
				candidati.add(candidato);
			}
		}

		for (Cluster cluster1 : candidati) {

			int compare = candidato.compareTo(cluster1);

			if (compare == -1) {
				candidato = cluster1;
			}

		}

		return candidato;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		return getC().toString();
	}

}