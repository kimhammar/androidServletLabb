package toDelete;

import exceptions.FormatNotSupportedException;

public class Trollol {
	
	public static String getFormatUrl(String format) throws FormatNotSupportedException {
		String url = "http://localhost:8080/androidServletLabb/";
		String urlEnd = "format";
		if (format.equals("xml")) {
			return url + format + urlEnd;
		} else if (format.equals("json")) {
			return url + format + urlEnd;
		}
		throw new FormatNotSupportedException("Format not supported");
	}

}
