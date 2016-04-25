package formats;

import interfaces.Format;
import interfaces.StudentStorage;
import storages.StudentStorageDB;
/**
 * Base class for all Formaters 
 * @author oki
 *
 */


public abstract class GroundFormatDB implements Format{
	protected StudentStorage storage = new StudentStorageDB();

}
