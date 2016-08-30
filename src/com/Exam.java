package com;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int courseId;
	private int examId;
	private String examName;
	private Date date;
	private int fees;
	private String[] city;
	private int seats;
	private String c;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getExamId() {
		return examId;
	}
	public void setExamId(int examId) {
		this.examId = examId;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}
	public String[] getCity() {
		return city;
	}
	public void setCity(String[] city) {
		this.city = city;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getC(){
		return c;
	}
	public void setC(String c){
		this.c    = c;
	}
	
}
