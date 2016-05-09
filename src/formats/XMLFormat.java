package formats;

import java.util.List;
import java.util.stream.Collectors;

import domain.Course;
import domain.Student;
import settings.Settings;
import storages.NoSuchStorageException;
import storages.StorageFactory;
import storages.StudentStorage;
import storages.StudentStorageDB;

/**
 * This class is used for formating XML output
 * @author Kim Hammar
 *
 */
public class XMLFormat implements Format{
	StudentStorage storage;
	public XMLFormat() throws NoSuchStorageException {
		storage = StorageFactory.getStudentStorage(Settings.STORAGE_TYPE);
	}
	StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");

	/*
	 * Returns a string with student name, id and courses in XML format
	 */
	public String fetchCourses(String studentId) throws IdNotFoundException{
		int id = Integer.parseInt(studentId);
		String name = storage.getStudentName(id);
		if(name == null) throw new IdNotFoundException(getIdErrorMessage());
		List<Course> list = storage.getCourseList(id);
		
		return xml + "<STUDENT name=\"" + name + "\" id=\"" + id + "\">\n" + 
				list.stream()
					.map(XMLFormat::courseToXML)
					.collect(Collectors.joining())
				+ "</STUDENT>\n";
	}
	
	/*
	 * Returns a string with all students in the database in XML format
	 */
	public String fetchStudents() {
		List<Student> list = storage.getStudentList();
		return xml +"<STUDENTS>\n" +
				list.stream()
					.map(XMLFormat::studentToXML)
					.collect(Collectors.joining()) +
				"</STUDENTS>\n";
	}
	
	public static String courseToXML(Course c) {
		return "\t<COURSE>" + c.getCourse() +"</COURSE>\n";
	}
	
	
	public static String studentToXML(Student s) {
		return "\t<STUDENT id=\"" + s.getId() + "\">\n" +
		"\t\t<NAME>" + s.getName() + "</NAME>\n" +
		"\t</STUDENT>\n";
	}


	@Override
	public String getContentType() {
		return "application/xml";
	}

	@Override
	public String getIdErrorMessage() {
		return xml + "<ERROR>NO SUCH ID</ERROR>";
	}


}
