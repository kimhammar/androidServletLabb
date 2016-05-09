package domain;


/**
 * This class represents a course
 * @author Kim Hammar
 */
public class Course {
	private String course;
	
	//Constructor
	public Course(String course) {
		this.course = course;
	}
	
	//Method for returning the course name
	public String getCourse() {
		return course;
	}
}
