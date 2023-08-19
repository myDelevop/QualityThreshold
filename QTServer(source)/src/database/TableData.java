package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import database.TableSchema.Column;

// TODO: Auto-generated Javadoc
/**
 * Classe TableData.
 */
public class TableData {

	/** Database. */
	DbAccess db;

	/**
	 * Istanzia nuova tabella data.
	 *
	 * @param db
	 *            the Database
	 */
	public TableData(DbAccess db) {
		this.db = db;
	}

	/**
	 * Gets the distinct transazioni.
	 *
	 * @param table
	 *            nome tabella
	 * @return the distinct transazioni
	 * @throws SQLException
	 *             the SQL exception
	 * @throws EmptySetException
	 *             the empty set exception
	 * @throws InexistentTableException
	 *             the inexistent table exception
	 */
	public List<Example> getDistinctTransazioni(String table)
			throws SQLException, EmptySetException, InexistentTableException {
		LinkedList<Example> transSet = new LinkedList<Example>();
		Statement statement;
		TableSchema tSchema = new TableSchema(db, table);

		String query = "select distinct ";

		for (int i = 0; i < tSchema.getNumberOfAttributes(); i++) {
			Column c = tSchema.getColumn(i);
			if (i > 0)
				query += ",";
			query += c.getColumnName();
		}

		if (tSchema.getNumberOfAttributes() == 0) {
			throw new InexistentTableException();

		}
		query += (" FROM " + table);

		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		boolean empty = true;
		while (rs.next()) {
			empty = false;
			Example currentTuple = new Example();
			for (int i = 0; i < tSchema.getNumberOfAttributes(); i++)
				if (tSchema.getColumn(i).isNumber())
					currentTuple.add(rs.getDouble(i + 1));
				else
					currentTuple.add(rs.getString(i + 1));
			transSet.add(currentTuple);
		}
		rs.close();
		statement.close();
		if (empty)
			throw new EmptySetException();

		return transSet;

	}

	/**
	 * Gets the distinct column values.
	 *
	 * @param table
	 *            nome tabella
	 * @param column
	 *            the column
	 * @return the distinct column values
	 * @throws SQLException
	 *             the SQL exception
	 */
	public Set<Object> getDistinctColumnValues(String table, Column column)
			throws SQLException {
		Set<Object> valueSet = new TreeSet<Object>();
		Statement statement;

		String query = "select distinct ";

		query += column.getColumnName();

		query += (" FROM " + table);

		query += (" ORDER BY " + column.getColumnName());

		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		while (rs.next()) {
			if (column.isNumber())
				valueSet.add(rs.getDouble(1));
			else
				valueSet.add(rs.getString(1));

		}
		rs.close();
		statement.close();

		return valueSet;

	}

	/**
	 * Gets the aggregate column value.
	 *
	 * @param table
	 *            nome tabella
	 * @param column
	 *            the column
	 * @param aggregate
	 *            the aggregate
	 * @return the aggregate column value
	 * @throws SQLException
	 *             the SQL exception
	 * @throws NoValueException
	 *             the no value exception
	 */
	public Object getAggregateColumnValue(String table, Column column,
			QUERY_TYPE aggregate) throws SQLException, NoValueException {
		Statement statement;
		Object value = null;
		String aggregateOp = "";

		String query = "select ";
		if (aggregate == QUERY_TYPE.MAX)
			aggregateOp += "max";
		else
			aggregateOp += "min";
		query += aggregateOp + "(" + column.getColumnName() + ") FROM " + table;

		statement = DbAccess.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(query);
		if (rs.next()) {
			if (column.isNumber())
				value = rs.getDouble(1);
			else
				value = rs.getString(1);

		}
		rs.close();
		statement.close();
		if (value == null)
			throw new NoValueException("No " + aggregateOp + " on "
					+ column.getColumnName());

		return value;

	}

}
