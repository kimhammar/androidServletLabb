package dbminer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static DBUtils instance = null;
	private static Connection con;

	private DBUtils() {
	}

	// Singleton
	public static DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
			init();
		}
		return instance;
	}
	
	
	/*
	 * Method which will return a new ResultSet for every query sent
	 */
	public synchronized ResultSet extractData(String query) {
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

	// Establishes connection to MySQL db
	private static void init() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidlabb", "root", "Losen123");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt establish db connection");
			// TODO Make own exception
		}
	}

}
