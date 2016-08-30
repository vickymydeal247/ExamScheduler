package com;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class CourseMapper implements RowMapper<Course>
{

	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		 
		Course course = new Course();
		
		course.setCourseId(Integer.parseInt(rs.getString(1)));
		course.setCourseName(rs.getString(2));
		course.setImage(rs.getString(3));
		
		return course;
		
	}

}
 