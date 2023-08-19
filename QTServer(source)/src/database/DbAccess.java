package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * Classe DbAccess.
 */
public class DbAccess {

	/** The driver class name. */
	private static String DRIVER_CLASS_NAME = "org.gjt.mm.mysql.Driver";

	/** The Constant DBMS. */
	private static final String DBMS = "jdbc:mysql";

	/** The Constant SERVER. */
	private static final String SERVER = "localhost";

	/** The Constant DATABASE. */
	private static final String DATABASE = "MapDB";

	/** The Constant PORT. */
	private static final int PORT = 3306;

	/** The Constant USER_ID. */
	private static final String USER_ID = "MapUser";

	/** The Constant PASSWORD. */
	private static final String PASSWORD = "map";

	/** The static connection */
	private static Connection conn;

	/**
	 * Restituisce la connessione.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		return conn;
	}

	/**
	 * Chiude la connessione.
	 */
	public static void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Inizia la connessione.
	 *
	 * @throws DatabaseConnectionException
	 *             the database connection exception
	 */
	public static void initConnection() throws DatabaseConnectionException {
		String connectionString = DBMS + "://" + SERVER + ":" + PORT + "/"
				+ DATABASE;
		try {
			Class.forName(DRIVER_CLASS_NAME).newInstance();
		} catch (Exception ex) {
			System.out.println("Impossibile trovare il Driver: "
					+ DRIVER_CLASS_NAME);
		}

		try {
			conn = DriverManager.getConnection(connectionString, USER_ID,
					PASSWORD);
			if (conn == null) {
				throw new DatabaseConnectionException();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
