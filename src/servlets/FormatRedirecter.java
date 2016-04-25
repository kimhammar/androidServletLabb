package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.FormatNotSupportedException;
import factories.FormatFactory;
import formats.GroundFormatDB;
import interfaces.Format;

@WebServlet("/formatredirecter")
public class FormatRedirecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FormatRedirecter() {
		super();
	}


	/*
	 * Will return format and data based on the request parameters "format" and "id"
	 * (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		try {
			String result;
			GroundFormatDB format = FormatFactory.getFormat(request.getParameter("format"));
			if (request.getParameter("id").toLowerCase().equals("all"))
				result = format.fetchStudents();
			else
				result = format.fetchCourses(Integer.parseInt(request.getParameter("id")));
			response.setContentType(format.getContentType());
			out.println(result);
			out.close();

		} catch (FormatNotSupportedException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
