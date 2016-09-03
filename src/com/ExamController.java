package com;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExamController 
{

	@Autowired
	private ExamDAOImpl examDAOImpl;
	
	
	/*@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}*/
	
	// index page
	@RequestMapping(value = {"/" ,"/index"}, method = RequestMethod.GET)
	public String course(ModelMap model) {
		
		  List<Course> courseList = new ArrayList<Course>();
		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("courseList", courseList);
		
	      return "user/index";
	   }
	
	// mapping for non login users
	
	// search exam
	@RequestMapping(value = "/examSearch", method = RequestMethod.GET)
	public String examSearch(ModelMap model) {
		 
		  List<Course> courseList = new ArrayList<Course>();
		
		               courseList = examDAOImpl.showCourse();
		
		  model.addAttribute("courseList", courseList);
	      return "user/examSearch";
	   }
	
	
	// mapping for about page  
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about() {
	      return "user/about";
	   }
	
	// mapping for contact page
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
	      return "user/contact";
	   }
	
	// mapping for course exam showing on home page
	@RequestMapping(value = "/courseExam", method = RequestMethod.GET)
	public String courseExam(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpServletRequest request) {
		
		  int courseId = Integer.parseInt(request.getParameter("id")); 
		  
		  List<Exam>  listExam = new ArrayList<Exam>();
			 
			          listExam = examDAOImpl.getExam(courseId); 
			
			model.addAttribute("listExam", listExam);
		  
	      return "user/courseExam";
	   }
	
	// register
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
	      return "user/register";
	   }
	
	// search result
	@RequestMapping(value = "/searchResult", method = RequestMethod.POST)
	public String examSearchResult(@ModelAttribute("SpringWeb")ExamSearch examSearch, 
			   ModelMap model, HttpServletRequest request) {  
		
		List<Exam>  listExam      = new ArrayList<Exam>();
		
		int courseId      = Integer.parseInt(request.getParameter("courseId")); 
		String city       = (String)(request.getParameter("city")); 
		
		listExam              = examDAOImpl.getExam(courseId, city); 
		
		model.addAttribute("listExam", listExam);
		model.addAttribute("city", city);
	     
		return "user/searchResult";
	}
	
	// register value processing
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute("SpringWeb")ExamSearch examSearch, 
			   ModelMap model, HttpServletRequest request) { 
		
		int examId      =  Integer.parseInt(request.getParameter("examId")); 
		String userName =  request.getParameter("userName"); 
		String city     = request.getParameter("city");  
		String address  = request.getParameter("address");     
		examDAOImpl.registerExam(examId, userName, city, address);
		 
		return "index";
	}
	
	// login
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public String login() {
		      return "user/login";
		   }
	
	// value processing for login
	@RequestMapping(value = "/userHome", method = RequestMethod.POST)
	public String login(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpServletRequest request) {   
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password"); 
		
		int v = examDAOImpl.login(userName, password); 
		 
		
		if(v>0)
		{
		    List<Course> courseList = new ArrayList<Course>();
			
                        courseList = examDAOImpl.showCourse(); 
                        
                        HttpSession session=request.getSession();  
                        session.setAttribute("userName",userName);

            model.addAttribute("courseList", courseList);
			return("loginUser/userHome");
		}
		else
		{
			return("user/login");
		}
	}
	
	// mapping for login users
	
	// home page
	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userHome(ModelMap model) {
		
		  List<Course> courseList = new ArrayList<Course>();
		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("courseList", courseList);
		
	      return "loginUser/userHome";
	   }
	
	// search exam
		@RequestMapping(value = "/userExamSearch", method = RequestMethod.GET)
		public String userExamSearch(ModelMap model) {
			 
			  List<Course> courseList = new ArrayList<Course>();
			
			               courseList = examDAOImpl.showCourse();
			
			  model.addAttribute("courseList", courseList);
		      return "loginUser/userExamSearch";
		   }
	
	// course exam showing on home page
	@RequestMapping(value = "/userCourseExam", method = RequestMethod.GET)
	public String userCourseExam(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpServletRequest request) {
		
		  int courseId = Integer.parseInt(((ServletRequest) request).getParameter("id")); 
		  
		  List<Exam>  listExam = new ArrayList<Exam>();
			 
			          listExam = examDAOImpl.getExam(courseId); 
			
			model.addAttribute("listExam", listExam);
		  
	      return "loginUser/userCourseExam";
	   }
	
	// add user
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String addUser() {
	      return "loginUser/addUser";
	   }
	
	// add exam
	@RequestMapping(value = "/addExam", method = RequestMethod.GET)
	public String addExam(ModelMap model) {
		
		  List<Course> courseList = new ArrayList<Course>();
		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("loginUser/courseList", courseList);
		
	      return "loginUser/addExam";
	   }
	
	// add create course
	@RequestMapping(value = "/createCourse", method = RequestMethod.GET)
	public String createCourse() {
		      return "loginUser/createCourse";
		   }
	
	// user register page
	@RequestMapping(value = "/userRegister", method = RequestMethod.GET)
	public String userRegister() {
	      return "loginUser/userRegister";
	   }
	
	// logout
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, ModelMap model) {
		  
		  System.out.println("hi");
		  HttpSession session = request.getSession();  
		  System.out.println("hello");
          session.invalidate();
          System.out.println("vicky");
          
          List<Course> courseList = new ArrayList<Course>();
  		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("courseList", courseList);
          
	      return "user/logout"; 
	   }
	
	// search result
	@RequestMapping(value = "/userSearchResult", method = RequestMethod.POST)
	public String userSearchResult(@ModelAttribute("SpringWeb")ExamSearch examSearch, 
			   ModelMap model, HttpServletRequest request) {  
		
		List<Exam>  listExam      = new ArrayList<Exam>();
		
		int courseId      = Integer.parseInt(request.getParameter("courseId")); 
		String city       = (String)(request.getParameter("city")); 
		
		listExam              = examDAOImpl.getExam(courseId, city); 
		
		model.addAttribute("listExam", listExam);
		model.addAttribute("city", city);
	     
		return "loginUser/userSearchResult";
	}
	
	// add user
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpServletRequest request) {   
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address  = request.getParameter("address");
		
		examDAOImpl.setUser(userName, password, address); 
		
		return "redirect:/";
	}
	
	// add exam
	@RequestMapping(value = "/addExam", method = RequestMethod.POST)
	public String addExam(@ModelAttribute("SpringWeb")Exam exam, 
			   ModelMap model, HttpServletRequest request) {   
	
		exam.setCourseId(Integer.parseInt(request.getParameter("courseId")));
		exam.setExamName(request.getParameter("examName"));
		
		String  date  = request.getParameter("d");  
		Date d = Date.valueOf(date); 
	    exam.setDate(d);
	    
		int id = Integer.parseInt(request.getParameter("fees"));
		exam.setFees(id); 
		
		exam.setSeats(Integer.parseInt(request.getParameter("seats")));
		 
		exam.setCity(request.getParameterValues("city")); 
		
		examDAOImpl.setExam(exam); 
		
		return "redirect:/";
	}
	
	// user register process data
	@RequestMapping(value = "/userRegister", method = RequestMethod.POST)
	public String userRegister(@ModelAttribute("SpringWeb")ExamSearch examSearch, 
			   ModelMap model, HttpServletRequest request) { 
		
		int examId      =  Integer.parseInt(request.getParameter("examId")); 
		String userName =  request.getParameter("userName"); 
		String city     = request.getParameter("city");  
		String address  = request.getParameter("address");    
		examDAOImpl.registerExam(examId, userName, city, address);
		 
		return "loginUser/userHome";
	}
	
	// create course process data
	@RequestMapping(value = "/createCourse", method = RequestMethod.POST)
	public String createCourse(@ModelAttribute("SpringWeb")Course course, 
			   ModelMap model, HttpServletRequest request) {  
		
		String courseName = request.getParameter("courseName");
		
	    examDAOImpl.createCourse(courseName);
		
		return "loginUser/createCourse";
		
	}
	
}
