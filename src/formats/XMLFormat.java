package formats;

import java.util.List;
import java.util.stream.Collectors;

import domain.Course;
import domain.Student;
import interfaces.Format;
import interfaces.StudentStorage;
import storages.StudentStorageDB;

public class XMLFormat extends GroundFormatDB{
	StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");


	public String fetchCourses(int id) {
		String name = storage.getStudentName(id);
		List<Course> list = storage.getCourseList(id);
		
		return xml + "<STUDENT name=\"" + name + "\" id=\"" + id + "\">\n" + 
				list.stream()
					.map(XMLFormat::courseToXML)
					.collect(Collectors.joining())
				+ "</STUDENT>\n";
	}
	
	
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

}
