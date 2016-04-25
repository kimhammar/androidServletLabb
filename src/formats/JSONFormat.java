package formats;

import java.util.stream.Collectors;

import domain.Course;
import domain.Student;

public class JSONFormat extends GroundFormatDB{
	
	
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

	public String fetchCourses(int id) {
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

}
