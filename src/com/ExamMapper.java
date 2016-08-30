package com;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ExamMapper implements RowMapper<Exam>
{

	public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		Exam exam = new Exam();
		
		exam.setCourseId(rs.getInt(1));
		exam.setExamId(rs.getInt(2));
		exam.setExamName(rs.getString(3));
		exam.setDate(rs.getDate(4));
		exam.setFees(rs.getInt(5));
		exam.setC(rs.getString(6));
		
		return exam;
	}

}
