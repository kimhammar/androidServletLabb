package interfaces;

import java.sql.ResultSet;

public interface Database {
	public ResultSet extractData(String query);

}
