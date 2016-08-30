package com;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.ModelAndView;

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
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView course(ModelMap model) {
		
		  List<Course> courseList = new ArrayList<Course>();
		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("courseList", courseList);
		
	      return new ModelAndView("index", "Index", new Course());
	   }
	
	// mapping for non login users
	
	// search exam
	@RequestMapping(value = "/user/examSearch", method = RequestMethod.GET)
	public ModelAndView examSearch(ModelMap model) {
		 
		  List<Course> courseList = new ArrayList<Course>();
		
		               courseList = examDAOImpl.showCourse();
		
		  model.addAttribute("courseList", courseList);
	      return new ModelAndView("user/examSearch", "Exam Search", new ExamSearch());
	   }
	
	
	// mapping for about page  
	@RequestMapping(value = "/user/about", method = RequestMethod.GET)
	public ModelAndView about() {
	      return new ModelAndView("user/about", "About", new ExamSearch());
	   }
	
	// mapping for contact page
	@RequestMapping(value = "/user/contact", method = RequestMethod.GET)
	public ModelAndView contact() {
	      return new ModelAndView("user/contact", "Contact", new ExamSearch());
	   }
	
	// mapping for course exam showing on home page
	@RequestMapping(value = "/user/courseExam", method = RequestMethod.GET)
	public ModelAndView courseExam(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpServletRequest request) {
		
		  int courseId = Integer.parseInt(request.getParameter("id")); 
		  
		  List<Exam>  listExam = new ArrayList<Exam>();
			 
			          listExam = examDAOImpl.getExam(courseId); 
			
			model.addAttribute("listExam", listExam);
		  
	      return new ModelAndView("user/courseExam", "Course Exam Id", new ExamSearch());
	   }
	
	// register
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public ModelAndView register() {
	      return new ModelAndView("user/register", "Register", new Exam());
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
		@RequestMapping(value = "/user/login", method = RequestMethod.GET)
		public ModelAndView login() {
		      return new ModelAndView("user/login", "Login", new User());
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
	@RequestMapping(value = "/loginUser/userHome", method = RequestMethod.GET)
	public ModelAndView userHome(ModelMap model) {
		
		  List<Course> courseList = new ArrayList<Course>();
		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("courseList", courseList);
		
	      return new ModelAndView("login/userHome", "User Home Page", new ExamSearch());
	   }
	
	// search exam
		@RequestMapping(value = "/loginUser/userExamSearch", method = RequestMethod.GET)
		public ModelAndView userExamSearch(ModelMap model) {
			 
			  List<Course> courseList = new ArrayList<Course>();
			
			               courseList = examDAOImpl.showCourse();
			
			  model.addAttribute("courseList", courseList);
		      return new ModelAndView("loginUser/userExamSearch", "User Exam Search", new ExamSearch());
		   }
	
	// course exam showing on home page
	@RequestMapping(value = "/loginUser/userCourseExam", method = RequestMethod.GET)
	public ModelAndView userCourseExam(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpRequest request) {
		
		  int courseId = Integer.parseInt(((ServletRequest) request).getParameter("id")); 
		  
		  List<Exam>  listExam = new ArrayList<Exam>();
			 
			          listExam = examDAOImpl.getExam(courseId); 
			
			model.addAttribute("listExam", listExam);
		  
	      return new ModelAndView("loginUser/userCourseExam", "User Course Exam", new ExamSearch());
	   }
	
	// add user
	@RequestMapping(value = "/loginUser/addUser", method = RequestMethod.GET)
	public ModelAndView addUser() {
	      return new ModelAndView("loginUser/addUser", "Add User", new ExamSearch());
	   }
	
	// add exam
	@RequestMapping(value = "/loginUser/addExam", method = RequestMethod.GET)
	public ModelAndView addExam(ModelMap model) {
		
		  List<Course> courseList = new ArrayList<Course>();
		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("loginUser/courseList", courseList);
		
	      return new ModelAndView("loginUser/addExam", "Add Exam", new Exam());
	   }
	
	// user register page
	@RequestMapping(value = "/loginUser/userRegister", method = RequestMethod.GET)
	public ModelAndView userRegister() {
	      return new ModelAndView("loginUser/userRegister", "User Register", new Exam());
	   }
	
	// logout
	@RequestMapping(value = "/user/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, ModelMap model) {
		  
		  System.out.println("hi");
		  HttpSession session = request.getSession();  
		  System.out.println("hello");
          session.invalidate();
          System.out.println("vicky");
          
          List<Course> courseList = new ArrayList<Course>();
  		
          courseList = examDAOImpl.showCourse();

          model.addAttribute("courseList", courseList);
          
	      return new ModelAndView("loginUser/logout", "Logout", new User()); 
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
	public ModelAndView addUser(@ModelAttribute("SpringWeb")User user, 
			   ModelMap model, HttpServletRequest request) {   
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String address  = request.getParameter("address");
		
		examDAOImpl.setUser(userName, password, address); 
		
		return new ModelAndView("redirect:/");
	}
	
	// add exam
	@RequestMapping(value = "/addExam", method = RequestMethod.POST)
	public ModelAndView addExam(@ModelAttribute("SpringWeb")Exam exam, 
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
		
		return new ModelAndView("redirect:/");
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
