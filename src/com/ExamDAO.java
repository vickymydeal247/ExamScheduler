package com;

import java.util.List;

import javax.sql.DataSource;

public interface ExamDAO 
{
	// for showing all the courses in drop down 
	public List<Course> showCourse();
	
    // for saving the record in Data Source
	public void setDataSource(DataSource ds);
	
	// for  saving details of exam in database
	public void setExam(Exam exam);
	
	// search exams on the basis of courses and city
	public List<Exam> getExam(int courseId, String city);
		
	// for saving user details in the database
	public void setUser(String userName, String password, String address);
	
	// for saving exam and user details
	public void registerExam(int examId, String userName, String city, String address);
	
	// for login to the page
	public int login(String userName, String password);
	
	// for showing java courses
	public List<Exam> getExam(int courseId);
	
	// for creating course
	public void createCourse(String courseName);
	
}
