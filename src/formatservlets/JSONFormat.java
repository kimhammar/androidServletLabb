package formatservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import dbminer.MySQLMiner;

/**
 * Servlet implementation class JSONFormat
 */

@WebServlet("/jsonformat")
public class JSONFormat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public JSONFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json; charset=UTF-8");
		
		String idRequest = request.getParameter("id");
		int amountOfStudents = getAmountOfStudents();
		PrintWriter out = response.getWriter();
		StringBuilder json = new StringBuilder();
		try {
			if(idRequest.equals("all")) json.append(getAllStudents());
			else if (Integer.parseInt(idRequest) <= amountOfStudents)
				json.append(getCourses(Integer.parseInt(idRequest)));
			else json.append("{invalid parameter}");
			out.println(json);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getCourses(int id) throws SQLException, ParserConfigurationException, TransformerException{
		StringBuilder sb = new StringBuilder("{\n");
		ResultSet rs;
		String courseQuery = "SELECT course_code " + 
				"FROM courses as c " +
				"JOIN registrations as r " +
				"ON c.id = r.course_id " +
				"JOIN students as s " +
				"ON r.student_id = s.id " +
				"WHERE s.id = " + id;		
		try {
			
			rs = MySQLMiner.getInstance().extractData("select name from students where id=" + id);
			rs.next();
			String name = rs.getString("name");
			
			rs = MySQLMiner.getInstance().extractData(courseQuery);
			sb.append("\t\"studentName\":\"" + name + "\",\n");
			sb.append("\t\"studentID\":\"" + id + "\",\n");
			sb.append("\t\"courses\":[\n");
			while (rs.next()) {
				sb.append("\t\t{\n");
				sb.append("\t\t\t\"courseCode\":\"" + rs.getString("course_code") + "\"\n");
				sb.append("\t\t}");
				if(!rs.isLast()) sb.append(",");
				sb.append("\n");
			}
			sb.append("\t]\n");
			sb.append("}");
		} catch (SQLException e) {
			e.printStackTrace();
			return sb.append("bad argument: no such id}").toString();
		}

		return sb.toString();
	}
	
	private int getAmountOfStudents() {
		ResultSet rs = MySQLMiner.getInstance().extractData("select count(id) from students");
		try {
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
//	{
//	    "studentName":"Beata Bengtsson",
//	    "studentID":"2",
//	    "courses":[
//	        {
//	            "courseCode":"TIG058"
//	        },
//	        {
//	            "courseCode":"TIG059"
//	        }
//	    ]
//	}

	public String getAllStudents() {
		String query = "select * from students";
		ResultSet rs = MySQLMiner.getInstance().extractData(query);
		StringBuilder sb = new StringBuilder("{\n\t\"students\":[\n");
		try {
			while (rs.next()) {
				sb.append("\t\t{\n");
				sb.append("\t\t\t\"studentName\":\"" + rs.getString("name") + ",\n" + 
				"\t\t\t\"studentID\":" + rs.getInt("id") + "\n");
				sb.append("\t\t}");
				if(!rs.isLast()) sb.append(",");
				sb.append("\n");
			}
			sb.append("\t]\n}");
		} catch (SQLException e) {
			// TODO generate no such column exception
			e.printStackTrace();
			return "{no such table in database}";
		}
		return sb.toString();
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
