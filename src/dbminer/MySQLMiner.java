package dbminer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class which establishes a connection to a MySQL database with 
 * possibility to return a ResultSet with each query sent
 */
public class MySQLMiner {
	private static MySQLMiner instance = null;
	private static Connection con;
	
	private MySQLMiner() {
	}
	
	//Singleton
	public static MySQLMiner getInstance(){
		if(instance == null) {
			instance = new MySQLMiner();
			init();
		}
		return instance;
	}

	//Establishes connection to MySQL db
	private static void init() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidlabb", "root", "Losen123");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt establish db connection");
			// TODO Make own exception
		}
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
}