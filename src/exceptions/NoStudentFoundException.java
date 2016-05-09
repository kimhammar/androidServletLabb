package exceptions;

/**
 * This exception will be thrown when a student cannot be found
 * @author Kim Hammar
 */
public class NoStudentFoundException extends Exception {
	public NoStudentFoundException(String msg) {
	    super(msg);
	  }
}
