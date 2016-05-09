package factories;

import formats.Format;
import formats.FormatNotSupportedException;
import formats.JSONFormat;
import formats.XMLFormat;
import storages.NoSuchStorageException;

/**
 * Factory which if possible will return the suitable object for 
 * formating into the chosen format
 * @author Kim Hammar
 *
 */
public class FormatFactory {

	
	/**
	 * If possible returns an object for the chosen format
	 * @param format The requested format
	 * @return An object ready to format 
	 * @throws FormatNotSupportedException
	 * @throws NoSuchStorageException
	 */
	public static Format getFormat(String format) throws FormatNotSupportedException, NoSuchStorageException {
		if(format.toLowerCase().equals("xml"))
			return new XMLFormat();
		else if(format.toLowerCase().equals("json"))
			return new JSONFormat();
		else
			throw new FormatNotSupportedException("Format not supported");
	}

}
