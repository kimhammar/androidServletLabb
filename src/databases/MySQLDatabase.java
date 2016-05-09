package databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import settings.Settings;
/**
 * This class represents a MySQL database
 * @author Kim Hammar
 */
public class MySQLDatabase implements Database{
	private static Connection con;
	//Register driver to driver manager
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//Constructor
	public MySQLDatabase() {
		init();
	}
	
	/**
	 * Returns a ResultSet with the data asked for
	 */
	public ResultSet extractData(String query) {
		ResultSet rs = null;
		Statement stmt;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Bad query");
			// TODO Make own exception
			e.printStackTrace();
		}
		return rs;
	}

	// Establishes connection to a MySQL database
	private static void init() {
		try {
			con = DriverManager.getConnection(Settings.MYSQL_DATABASE, Settings.MYSQL_USER, Settings.MYSQL_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt establish db connection");
		}
	}

}
