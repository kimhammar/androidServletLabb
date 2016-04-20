package domain;

import java.util.List;

public class Student {
	private String name;
	private int id;
	List<Course> courses;

	public Student(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void addCourse(Course c) {
		courses.add(c);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
