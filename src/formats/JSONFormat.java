package formats;

import java.util.stream.Collectors;

import domain.Course;
import domain.Student;
import settings.Settings;
import storages.NoSuchStorageException;
import storages.StorageFactory;
import storages.StudentStorage;
import storages.StudentStorageDB;

/**
 * This class is used for formating JSON output
 * @author Kim Hammar
 *
 */
public class JSONFormat implements Format{
	protected StudentStorage storage;
	
	public JSONFormat() throws NoSuchStorageException {
		storage = StorageFactory.getStudentStorage(Settings.STORAGE_TYPE);
	}
	
	
	@Override
	public String fetchStudents() {
		storage.getStudentList();
		return "{\n\t\"students\":[" +
				storage.getStudentList()
					   .stream()
					   .map(JSONFormat::studentToJSON)
					   .collect(Collectors.joining(",")) +
					   "\n\t]\n}";
	}

	public String fetchCourses(String studentId) throws IdNotFoundException {
		int id = Integer.parseInt(studentId);
		if(storage.getStudentName(id) == null) throw new IdNotFoundException(getIdErrorMessage());
		return "{\n\t\"studentName\":\"" +storage.getStudentName(id) + "\",\n" +
				"\t\"studentID\":\"" + id + "\",\n" +
				"\t\"courses\":\n\t[" + storage.getCourseList(id).stream()
				.map(JSONFormat::courseToJSON)
				.collect(Collectors.joining(",")) +
				"\n\t]\n}";
	}
	
	public static String courseToJSON(Course c) {
		return "\n\t\t{" +
				"\n\t\t\t\"courseCode\":\"" + c.getCourse() + "\"" +
				"\n\t\t}";
	}
	
	public static String studentToJSON(Student student){
		return "\n\t\t{\n" +
		"\t\t\t\"studentName\":\"" + student.getName() + "\",\n" + 
		"\t\t\t\"studentID\":" + student.getId() + "\n" +
		"\t\t}";
	}

	@Override
	public String getContentType() {
	return "application/json";
	}

	@Override
	public String getIdErrorMessage() {
		return "{ \"error\": \"id not found\"}";
	}



}
