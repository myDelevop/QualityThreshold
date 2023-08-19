package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Classe TableSchema.
 */
public class TableSchema {

	/** Database. */
	DbAccess db;

	/**
	 * Inner_class column
	 */
	public class Column {

		/** The name. */
		private String name;

		/** The type. */
		private String type;

		/**
		 * Instantiates a new column.
		 *
		 * @param name
		 *            the name
		 * @param type
		 *            the type
		 */
		Column(String name, String type) {
			this.name = name;
			this.type = type;
		}

		/**
		 * Gets the column name.
		 *
		 * @return the column name
		 */
		public String getColumnName() {
			return name;
		}

		/**
		 * Checks if is number.
		 *
		 * @return true, if is number
		 */
		public boolean isNumber() {
			return type.equals("number");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return name + ":" + type;
		}
	}

	/** Schema della tabella. */
	List<Column> tableSchema = new ArrayList<Column>();

	/**
	 * Instantiates a new table schema.
	 *
	 * @param db
	 *            the db
	 * @param tableName
	 *            the table name
	 * @throws SQLException
	 *             the SQL exception
	 */
	public TableSchema(DbAccess db, String tableName) throws SQLException {
		this.db = db;
		HashMap<String, String> mapSQL_JAVATypes = new HashMap<String, String>();
		// http://java.sun.com/j2se/1.3/docs/guide/jdbc/getstart/mapping.html
		mapSQL_JAVATypes.put("CHAR", "string");
		mapSQL_JAVATypes.put("VARCHAR", "string");
		mapSQL_JAVATypes.put("LONGVARCHAR", "string");
		mapSQL_JAVATypes.put("BIT", "string");
		mapSQL_JAVATypes.put("SHORT", "number");
		mapSQL_JAVATypes.put("INT", "number");
		mapSQL_JAVATypes.put("LONG", "number");
		mapSQL_JAVATypes.put("FLOAT", "number");
		mapSQL_JAVATypes.put("DOUBLE", "number");

		try {
			Connection con = db.getConnection();
			DatabaseMetaData meta = con.getMetaData();
			ResultSet res = meta.getColumns(null, null, tableName, null);

			while (res.next()) {

				if (mapSQL_JAVATypes.containsKey(res.getString("TYPE_NAME")))
					tableSchema.add(new Column(res.getString("COLUMN_NAME"),
							mapSQL_JAVATypes.get(res.getString("TYPE_NAME"))));

			}
			res.close();
		} catch (SQLException e) {
			throw e;
		}

	}

	/**
	 * Gets the number of attributes.
	 *
	 * @return the number of attributes
	 */
	public int getNumberOfAttributes() {
		return tableSchema.size();
	}

	/**
	 * Gets the column.
	 *
	 * @param index
	 *            the index
	 * @return the column
	 */
	public Column getColumn(int index) {
		return tableSchema.get(index);
	}

}
