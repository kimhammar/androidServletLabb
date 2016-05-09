package storages;

/**
 * Returns a new instance of type of storage asked for
 * @author Kim Hammar
 */

public class StorageFactory {
	
	public static StudentStorage getStudentStorage(String storage) throws NoSuchStorageException {
		if(storage.equals("database"))
			return new StudentStorageDB();
		else if(storage.equals("cloud"))
			return new StudentStorageCloud();
		else throw new NoSuchStorageException("Storage type not supported");
	}

}
