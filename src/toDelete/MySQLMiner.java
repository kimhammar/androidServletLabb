package toDelete;

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
	  static {
		    try {
		      Class.forName("com.mysql.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		      e.printStackTrace();
		    }
		  }
	  
	private static MySQLMiner instance = null;
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
	
	

}