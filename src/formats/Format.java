package formats;

/**
 * This class represents what a class need to implement to 
 * be able to present output data
 * @author Kim Hammar
 */
public interface Format {
	
	/**
	 * Requests all students from the chosen storage and returns them as a formated string
	 * @return Output string with all students formated for the response
	 */
	public abstract String fetchStudents();
	
	/**
	 * Returns a formated string with student name, student id and courses
	 * @param id The selected id of requested student
	 * @return Output string with courses formated for the response
	 * @throws IdNotFoundException
	 */
	public abstract String fetchCourses(String id) throws IdNotFoundException;
	/**
	 * Returns the content type of the chosen format
	 * @return A string which can be set as content type on the response
	 */
	public abstract String getContentType();
	/**
	 * Returns a string with an error message when a student Id cant be found in the database
	 * @return An error message in the chosen format
	 */
	public abstract String getIdErrorMessage();
}
