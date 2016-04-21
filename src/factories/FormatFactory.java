package factories;

import exceptions.NoSuchFormatException;
import formats.GroundFormat;
import formats.JSONFormat;
import formats.XMLFormat;

public class FormatFactory {
	
	public static GroundFormat getFormat(String format) throws NoSuchFormatException {
		if(format.toLowerCase().equals("xml"))
			return (GroundFormat) new XMLFormat();
		else if(format.toLowerCase().equals("json"))
			return(GroundFormat) new JSONFormat();
		else
			throw new NoSuchFormatException("Format not supported");
	}

}
