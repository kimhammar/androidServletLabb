package factories;

import java.util.List;

import domain.Course;
import domain.Student;

public interface StudentStorage {
	public abstract List<Student> getList();
	public abstract List<Course> getCourses(int id);

}
