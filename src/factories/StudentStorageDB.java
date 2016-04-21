package factories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dbminer.DBUtils;
import domain.Course;
import domain.Student;
import exceptions.NoNameFoundException;
import interfaces.StudentStorage;

public class StudentStorageDB implements StudentStorage {

	public List<Student> getStudentList() {
		List<Student> returnList = new ArrayList<>();
		ResultSet rs = DBUtils.getInstance().extractData("select * from students");
		try {
			while (rs.next()) {
				returnList.add(new Student(rs.getString("name"), rs.getInt("id")));
			}
		} catch (SQLException e) {
			// TODO generate no such column exception
			System.out.println("No such column");
			e.printStackTrace();
		}
		return returnList;
	}

	@Override
	public List<Course> getCourseList(int id) {
		List<Course> returnList = new ArrayList<>();
		String courseQuery = "SELECT course_code " + "FROM courses as c " + "JOIN registrations as r "
				+ "ON c.id = r.course_id " + "JOIN students as s " + "ON r.student_id = s.id " + "WHERE s.id = " + id;
		try {

			ResultSet rs = DBUtils.getInstance().extractData(courseQuery);
			while (rs.next()) {
				returnList.add(new Course(rs.getString("course_code")));
			}
		} catch (SQLException e) {
			// TODO generate no such column exception
			System.out.println("No such column");
			e.printStackTrace();
		}
		System.out.println(returnList);
		return returnList;
	}
	
	public String getStudentName(int id) {
		ResultSet rs = DBUtils.getInstance().extractData("select name from students where id=" + id);
		try {
			rs.next();
			return rs.getString("name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


}
