package formats;


/**
 * This exception should be thrown when a student 
 * id can't be found
 * @author Kim Hammar
 *
 */
public class IdNotFoundException extends Exception {
	public IdNotFoundException(String msg){
		super(msg);
	}

}
