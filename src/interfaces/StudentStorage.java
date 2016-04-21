package interfaces;

import java.util.List;

import domain.Course;
import domain.Student;

public interface StudentStorage {
	public abstract List<Student> getStudentList();
	public abstract List<Course> getCourseList(int id);
	public abstract String getStudentName(int id);

}
