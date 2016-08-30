<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
  
  <head> 
    <meta charset="UTF-8"> 
    <title>Search For Examination</title>	
    
    <jsp:include page="css.jsp"/>
     
  </head>
 <body>
 
 <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index">Home Page</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">  
                    <li>
                        <a href="user/login">Login</a>
                    </li>    
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
 
  <div class="container">
   <div class="bs-example">
    <br><br><br>
    <form method="POST" action="/ExamScheduler/searchResult">
      <div class="form-group">
            <label for="courseId">Course</label> 
			<select name="courseId" class="form-control" id="course" required>
               <option>Select Course</option>
               <c:forEach var="course" items="${courseList}" varStatus="status">
               <option value=${course.courseId}>${course.courseName}</option>
               </c:forEach> 
            </select>
        </div>
        <div class="form-group">
            <label for="city">Course</label> 
			<select name="city" class="form-control" id="city" required>
               <option >Select City For Your Examination Center</option>
             <option value="Chennai">Chennai</option>
             <option value="Delhi">Delhi</option>
             <option value="Bangalore">Bangalore</option>
             <option value="Mumbai">Mumbai</option>
             <option value="Varanasi">Varanasi</option>
            </select>
         </div>
        <button type="submit" class="btn btn-primary">Select</button> 
    </form>
   </div>
  </div>	
 </body>
</html>