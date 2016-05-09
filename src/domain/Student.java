package domain;

import java.util.List;

/**
 * This class represents a student
 * @author Kim Hammar
 */
public class Student {
	private String name;
	private int id;
	private List<Course> courses;

	public Student(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	//Method for addning a course to the student
	public void addCourse(Course c) {
		courses.add(c);
	}
	
	//Method for retrieving the students name
	public String getName() {
		return name;
	}

	//Method for retrieving the students ID
	public int getId() {
		return id;
	}

}
