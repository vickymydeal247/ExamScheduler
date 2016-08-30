package com;

import java.sql.Blob;

public class Course 
{

	private int courseId;
	private String courseName;
	private String image;
	private Blob content;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
    public String getImage()
    {
    	return image;
    }
	public void setImage(String image)
	{
		this.image = image;
	}
	public Blob getContent()
	{
		return content;
	}
	public void setContent(Blob content)
	{
	    this.content = content; 
	}
}
