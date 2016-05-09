package formats;
/**
 * This exception will be thrown when a format cannot be found
 */
public class FormatNotSupportedException extends Exception{
	public FormatNotSupportedException(String msg) {
		    super(msg);
		  }
}
