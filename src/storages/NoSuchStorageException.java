package storages;

/**
 * This exception should be thrown if the storage asked for
 * can't be found
 * @author Kim Hammar
 */
public class NoSuchStorageException extends Exception {
	public NoSuchStorageException(String msg){
		super(msg);
	}

}
