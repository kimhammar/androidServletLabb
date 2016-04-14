package factories;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormatFactory
 */
@WebServlet("/formatfactory")
public class FormatFactory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FormatFactory() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String chosenFormat = "http://localhost:8080/labb01/" + request.getParameter("chosenFormat");
		redirect(chosenFormat, request, response);

	}

	// Redirects to the next page with the chosen format for grabbing data
	private void redirect(String chosenFormat, HttpServletRequest request, HttpServletResponse response) {
		try {
			response.sendRedirect("http://localhost:8080/labb01/jsonformat");
			// response.sendRedirect(chosenFormat + "hehj");
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
