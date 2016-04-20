package factories;

import exceptions.NoSuchFormatException;

public class FormatFactory {
	  public static String getFormatUrl(String format) throws NoSuchFormatException {
		  String url = "http://localhost:8080/androidServletLabb/";
		  String urlEnd = "format";
		    if(format.equals("xml")) {
		    	return url+format + urlEnd;
		    } else if (format.equals("json")) {
		    	return url+format + urlEnd;
		    }
		    throw new NoSuchFormatException("Format not supported");
		  }

}
