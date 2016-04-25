package dbminer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfaces.Database;

public class MySQLDatabase implements Database{
	private static Connection con;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public MySQLDatabase() {
		init();
	}
	
	/*
	 * Method which will return a new ResultSet for every query sent
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidlabb", "root", "Losen123");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt establish db connection");
			// TODO Make own exception
		}
	}

}
