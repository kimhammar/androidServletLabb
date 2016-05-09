package storages;

import java.util.List;

import domain.Course;
import domain.Student;


/**
 * These methods has to be implemented for an object
 * to be able to act as a storage
 * @author Kim Hammar
 *
 */
public interface StudentStorage {
	/**
	 * Should create a List of Students from data collected 
	 * from a lower layer
	 * @return A List of all students
	 */
	public abstract List<Student> getStudentList();
	/**
	 * Should return a list of courses depending on a students ID
	 * @param id The students ID
	 * @return A list of courses which the student studies
	 */
	public abstract List<Course> getCourseList(int id);
	/**
	 * Should return a name of the Student with the ID
	 * asked for
	 * @param id The student ID 
	 * @return The student Name
	 */
	public abstract String getStudentName(int id);

}
