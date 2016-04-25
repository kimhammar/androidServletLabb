package factories;

import exceptions.FormatNotSupportedException;
import formats.GroundFormatDB;
import formats.JSONFormat;
import formats.XMLFormat;

public class FormatFactory {
	
	
	/*
	 * Returns a new Formater for the chosen format
	 */
	public static GroundFormatDB getFormat(String format) throws FormatNotSupportedException {
		if(format.toLowerCase().equals("xml"))
			return (GroundFormatDB) new XMLFormat();
		else if(format.toLowerCase().equals("json"))
			return(GroundFormatDB) new JSONFormat();
		else
			throw new FormatNotSupportedException("Format not supported");
	}

}
