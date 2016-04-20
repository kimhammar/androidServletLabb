package factories;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.NoSuchFormatException;

/**
 * Servlet implementation class FormatFactory
 */
@WebServlet("/formatredirecter")
public class FormatRedirecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FormatRedirecter() {
		super();
	}

	public static void newInstance() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Creates the link to the next page depending on the requested
		// arguments
		try {
			String format = FormatFactory.getFormatUrl(request.getParameter("format"));
			redirect(format, request, response);
		} catch (NoSuchFormatException e) {
			e.printStackTrace();
		}
		// String chosenFormat = "http://localhost:8080/androidServletLabb/" +
		// request.getParameter("format") + "format"
		// + "?id=" + request.getParameter("id");
		//

	}

	// Redirects to the next page with the chosen format for grabbing data
	private void redirect(String chosenFormat, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect(chosenFormat + "?id=" + request.getParameter("id"));
		} catch (IOException e) {
			// TODO create own exception
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
