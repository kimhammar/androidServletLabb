package formatservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import dbminer.MySQLMiner;

@WebServlet("/xmlformat")
public class XMLFormat extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public XMLFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/xml; charset=UTF-8");
//		StreamResult result =  new StreamResult(response.getWriter());
		PrintWriter out = response.getWriter();
		String idRequest = request.getParameter("id");
		int amountOfStudents = getAmountOfStudents();
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		
		try {
//			xml.append(getAllStudents());
			if (idRequest.equals("all")) xml.append(getAllStudents());
			else if (Integer.parseInt(idRequest) <= amountOfStudents) xml.append(getCourses(Integer.parseInt(idRequest)));
			else xml.append("<ERROR>INVALID REQUEST</ERROR>");
			out.println(xml);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
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

	public Document loadXML() throws Exception{
	   DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
	   DocumentBuilder bldr = fctr.newDocumentBuilder();
	   InputSource insrc = new InputSource(new StringReader(getCourses(2)));
	   return bldr.parse(insrc);
	}

	public String getCourses(int id) throws SQLException, ParserConfigurationException, TransformerException{
		StringBuilder sb = new StringBuilder();
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
			sb.append("<STUDENT name=\"" + name + "\" id=\"" + id + "\">\n");
			while (rs.next()) {
				sb.append("\t<COURSE>" + rs.getString("course_code") + "</COURSE>\n");
			}
			sb.append("</STUDENT>\n");
		} catch (SQLException e) {
			// TODO generate no such column exception
			System.out.println("No such column");
			e.printStackTrace();
		}

		return sb.toString();
	}

	
	
	public String getAllStudents() throws SQLException, ParserConfigurationException, TransformerException {
		StringBuilder sb = new StringBuilder();
		ResultSet rs = MySQLMiner.getInstance().extractData("select * from students");
		// int coulmnCount = rs.getMetaData().getColumnCount();
		try {
			
			sb.append("<STUDENTS>\n");
			while (rs.next()) {
				sb.append("\t<STUDENT id=\"" + rs.getInt("id") + "\">\n");
				sb.append("\t\t<NAME>" + rs.getString("name") + "</NAME>\n");
				sb.append("\t</STUDENT>\n");
				// for(int i = 0; i < coulmnCount; i++) {
				// sb.append(rs.getObject(i + 1).toString());
				// }
			}
			sb.append("</STUDENTS>\n");
		} catch (SQLException e) {
			// TODO generate no such column exception
			System.out.println("No such column");
			e.printStackTrace();
		}
		System.out.println(sb.toString());
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
