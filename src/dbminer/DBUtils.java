package dbminer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	//Singleton
	public static DBUtils getInstance(){
		if(instance == null) {
			instance = new DBUtils();
			init();
		}
		return instance;
	}

	
	//Establishes connection to MySQL db
	private static void init() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidlabb", "root", "4aGwhtj9");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt establish db connection");
			// TODO Make own exception
		}
	}

}
