package databases;

import java.sql.ResultSet;


/**
 * This interface is used to prove an object can act
 * as a database
 * @author Kim Hammar
 *
 */
public interface Database {
	public ResultSet extractData(String query);

}
