package interfaces;

public interface Format {
	public abstract String fetchStudents();
	public abstract String fetchCourses(int id);
	public abstract String getContentType();
}
