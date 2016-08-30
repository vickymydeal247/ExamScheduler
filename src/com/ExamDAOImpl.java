package com;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ExamDAOImpl implements ExamDAO
{

	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
		jdbcTemplateObject = new JdbcTemplate(ds);
	}

	public void setExam(Exam exam) {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO Exam (CourseId, ExamName, ExamDate, Fees)"+ " VALUES (?, ?, ?, ?)";
            
		jdbcTemplateObject.update(query, exam.getCourseId(), exam.getExamName(), exam.getDate(), exam.getFees());
		
		       query = "SELECT ExamId FROM Exam WHERE ExamName = '" + exam.getExamName() + "'";
		       int examId = jdbcTemplateObject.query(query, new ResultSetExtractor<Integer>() {
		    	   
		           public Integer extractData(ResultSet rs) throws SQLException,
		                   DataAccessException {
		               if (rs.next()) {
		                   int examId = rs.getInt(1);
		                   return examId;
		               }
		    
		               return null;
		           }
		    
		       });
		
		for(String city : exam.getCity())
		{
		       query = "INSERT INTO Exam_City (ExamId, City, Seats) VALUES(?,?,?)";
		       
		       jdbcTemplateObject.update(query, examId, city, exam.getSeats());
		}
	}

	public List<Exam> getExam(int courseId, String city) {
		// TODO Auto-generated method stub
		 
		String query = "select CourseId, Exam.ExamId, ExamName, ExamDate, Fees, City from Exam, Exam_City where Exam.ExamId=Exam_City.ExamId and CourseId = ? and City = ? and Seats >= 1 and ExamDate > ?";
  
		List<Exam> examList = new ArrayList<Exam>();  
        
        ExamSearch examSearch = new ExamSearch(); 
        examSearch.setCourseId(courseId);
        examSearch.setCity(city); 
 
        Date d = new Date();
                   examList  = jdbcTemplateObject.query(query, new Object[]{courseId, city, d}, new ExamMapper());
          
        return     examList;
    }
		 
	public List<Course> showCourse() {
		// TODO Auto-generated method stub
		 
		String query = "select * from Course";
         
		List<Course> course = new ArrayList<Course>();
		
		             course = jdbcTemplateObject.query(query, new CourseMapper());
		
		return course;
	}

	public void setUser(String userName, String password, String address) {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO user (UserName, password, address)" + " VALUES (?, ?, ?)";
		
		jdbcTemplateObject.update(query, userName, password, address);
	}

	public void registerExam(int examId, String userName, String city, String address) {
		// TODO Auto-generated method stub
		
           String query = "INSERT INTO User_Exam(ExamId, UserName, City, Address)" + " VALUES (?, ?, ?, ?)";
		
		          jdbcTemplateObject.update(query, examId, userName, city, address);
		
		          query = "UPDATE Exam_City SET Seats = Seats - 1 WHERE ExamId = ? AND City = ?";
		          
		          jdbcTemplateObject.update(query, examId, city);
		
	}

	public int login(String userName, String password) {
		// TODO Auto-generated method stub
		
         String query = "SELECT COUNT(*) FROM User WHERE UserName = '" + userName + "' AND Password = '" + password + "'";
		
		 int v = jdbcTemplateObject.query(query, new ResultSetExtractor<Integer>() {
			 
		        public Integer extractData(ResultSet rs) throws SQLException,
		                DataAccessException {
		            if (rs.next()) {
		                int c = rs.getInt(1);
		                return c;
		            } 
		            return 0;
		        } 
		    });
		return v;
	}

	public List<Exam> getExam(int courseId) {
		// TODO Auto-generated method stub
		 
		String query = "select CourseId, Exam.ExamId, ExamName, ExamDate,  Fees, City from Exam, Exam_City where Exam.ExamId=Exam_City.ExamId and CourseId = ? and Seats >= 1 and ExamDate > ?";
		  
		List<Exam> examList = new ArrayList<Exam>();  
        
        ExamSearch examSearch = new ExamSearch(); 
        examSearch.setCourseId(courseId); 
        
        Date d = new Date();
        
                   examList  = jdbcTemplateObject.query(query, new Object[]{courseId, d}, new ExamMapper());
          
        return     examList;
		
	}

	public void createCourse(String courseName) {
		// TODO Auto-generated method stub
		
		String query = "INSERT INTO Course (Name)"+ " VALUES (?)";
        
		jdbcTemplateObject.update(query, courseName);
		
	}

}
