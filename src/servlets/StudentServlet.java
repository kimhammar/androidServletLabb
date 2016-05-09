package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factories.FormatFactory;
import formats.Format;
import formats.FormatNotSupportedException;
import formats.IdNotFoundException;
import storages.NoSuchStorageException;

/**
 * Servlet which takes two arguments, format and id, and formats 
 * a string to be presented
 * @author Kim Hammar
 */
@WebServlet("/secretstudents")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentServlet() {
		super();
	}

	/**
	 * When this method is called the servlet will generate a response and 
	 * print it out
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String format = request.getParameter("format");				
		PrintWriter out = response.getWriter();		
		String result = "";
		try {
			result = getOutput(format, id);
			response.setContentType(getContentType(format));
		} catch (IdNotFoundException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (FormatNotSupportedException e) {
			result = e.getMessage();
			e.printStackTrace();
		} catch (NoSuchStorageException e) {
			result = e.getMessage();
			e.printStackTrace();
		}

		out.println(result);
		out.close();
	}
	/**
	 * Gets the content type
	 * @param format This is the requested format
	 * @return A string for the chosen content type
	 * @throws FormatNotSupportedException
	 * @throws NoSuchStorageException
	 */
	public String getContentType(String format) throws FormatNotSupportedException, NoSuchStorageException{
		return FormatFactory.getFormat(format).getContentType();
	}
	
	/**
	 * Takes a format type and an id number as arguments and creates output.
	 * If argument "id" equals "all" all students will be returned with name and ID.
	 * If argument "id" equals a number the student with same id will be returned
	 * and his name, id and courses he studies will be returned.
	 * @param argFormat Requested output format
	 * @param argId Requested student id
	 * @return Output string depending on the given arguments
	 * @throws IdNotFoundException
	 * @throws FormatNotSupportedException
	 * @throws NoSuchStorageException
	 */
	public String getOutput(String argFormat, String argId) throws IdNotFoundException, FormatNotSupportedException, NoSuchStorageException{
		StringBuilder result = new StringBuilder("");
			Format format = FormatFactory.getFormat(argFormat);
			if (argId.toLowerCase().equals("all"))
				result.append(format.fetchStudents());
			else 
				result.append(format.fetchCourses(argId));
			return result.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
