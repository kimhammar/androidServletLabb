package formatservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		PrintWriter out = response.getWriter();
		out.println("Tjena");
		System.out.println("tja");
		java.sql.Connection myConn;
		try {
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbandroid", "root",
					"Losen123");
	
		Statement myStmt = myConn.createStatement();
		ResultSet rs = myStmt.executeQuery("select * from students");

		while (rs.next()) {
			out.println( rs.getInt("id") +
					rs.getString("name"));
		}

		System.out.println("SDS");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
