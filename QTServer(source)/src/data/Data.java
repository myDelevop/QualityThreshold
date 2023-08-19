package data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import mining.ClusterSet;
import database.*;
import database.TableSchema.Column;

/**
 * Classe Data
 */
public class Data implements Serializable {

	// private Object data [][]; //matrice nXm di tipo Objectdove ogni riga
	// modella una transazione
	/** Cardinalità dell'insieme delle transazioni */
	private int numberOfExamples;

	/**
	 * un vettore degli attributi in ciascuna tupla (schema della tabella di
	 * dati)
	 */
	List<Attribute> attributeSet;

	/** Lista di esempi */
	private List<Example> data = new ArrayList<Example>();

	/**
	 * Istanzio un nuovo Data
	 * 
	 * Si connette al DB e costruisce i set degli attributi
	 *
	 * @param nome
	 *            tabella
	 * @throws EmptyDatasetException
	 *             the empty dataset exception
	 * @throws EmptySetException
	 *             the empty set exception
	 * @throws SQLException
	 *             the SQL exception
	 * @throws NoValueException
	 *             the no value exception
	 */
	public Data(String table) throws EmptyDatasetException, EmptySetException,
			NoValueException, InexistentTableException, SQLException {
		try {
			data = new ArrayList<Example>();
			DbAccess db = new DbAccess();

			try {
				DbAccess.initConnection();
			} catch (DatabaseConnectionException e) {
				e.getMessage();
				e.printStackTrace();
			}

			TableData tableData = new TableData(db);
			TableSchema tableSchema = new TableSchema(db, table);

			data = tableData.getDistinctTransazioni(table);
			numberOfExamples = tableData.getDistinctTransazioni(table).size();
			attributeSet = new LinkedList<>();

			for (int i = 0; i < tableSchema.getNumberOfAttributes(); i++) {
				TreeSet<String> treeStrings = new TreeSet<String>();
				Column col = tableSchema.getColumn(i);

				// Continuo
				if (tableSchema.getColumn(i).isNumber()) {
					ContinuousAttribute contAttribute = new ContinuousAttribute(
							tableSchema.getColumn(i).getColumnName(), i,
							(double) tableData.getAggregateColumnValue(table,
									col, QUERY_TYPE.MIN),
							(double) tableData.getAggregateColumnValue(table,
									col, QUERY_TYPE.MAX));
					attributeSet.add(contAttribute);

				} else if (!tableSchema.getColumn(i).isNumber()) { // Discreto
					Set<Object> values = tableData.getDistinctColumnValues(
							table, col);

					for (Object val : values) {
						treeStrings.add(val.toString());
					}

					DiscreteAttribute discAttribute = new DiscreteAttribute(
							tableSchema.getColumn(i).getColumnName(), i,
							treeStrings);

					attributeSet.add(discAttribute);

				}
			}
		} catch (EmptySetException | NoValueException e) {
			e.getMessage();
		} catch (InexistentTableException e) {
			throw new InexistentTableException();
		} catch (SQLException e) {
			e.getMessage();
		}

		if (numberOfExamples == 0)
			throw new EmptyDatasetException();

	}

	/**
	 * Restituisce la cardinalità dell'insieme di transazioni
	 *
	 */
	public int getNumberOfExamples() {
		return numberOfExamples;
	}

	/**
	 * Prende la cardinalità dell'insieme degli attributi
	 *
	 */
	public int getNumberOfAttributes() {
		return attributeSet.size();
	}

	/**
	 * Prende lo schema dei dati
	 *
	 */
	List<Attribute> getAttributeSchema() {
		return attributeSet;
	}

	/**
	 * Restituisce il valore assunto in data in posizione
	 * [exampleIndex][attributeIndex]
	 *
	 */
	public Object getAttributeValue(int exampleIndex, int attributeIndex) {
		// return data[exampleIndex][attributeIndex];
		return data.get(exampleIndex).get(attributeIndex);
	}

	/**
	 * ToString del DataSet
	 */
	@Override
	public String toString() {
		String schema = new String();
		int i = 0;
		schema = attributeSet.toString() + "\n";

		for (Example d1 : data) {
			schema += i + ":" + d1.toString() + "\n";
			i++;
		}
		return schema;
	}

	/**
	 * Costruisce l'itemSet in base al tipo di Attributo
	 *
	 * @param index
	 *            Numero identificativo
	 * @return Le Tuple costruite
	 */
	public Tuple getItemSet(int index) {
		Tuple tuple = new Tuple(attributeSet.size());

		for (int i = 0; i < attributeSet.size(); i++) {
			if (attributeSet.get(i) instanceof ContinuousAttribute)
				tuple.add(
						new ContinuousItem((ContinuousAttribute) attributeSet
								.get(i), (Double) getAttributeValue(index, i)),
						i);
			else if (attributeSet.get(i) instanceof DiscreteAttribute)
				tuple.add(
						new DiscreteItem((DiscreteAttribute) attributeSet
								.get(i), (String) getAttributeValue(index, i)),
						i);
		}

		return tuple;
	}
}
