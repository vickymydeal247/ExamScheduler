<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <head>
  <meta charset="UTF-8"> 
  <title>Course Examination</title>	
  <jsp:include page="css.jsp" />  
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
                        <a href="user/examSearch">Search Exam</a> 
                    </li>
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
    
    <h2>Student Information</h2>
    
    <table class="table table-striped table-hover">
        <thead>
           <tr>
               <th>Exam ID</th>
               <th>Exam Name</th>
               <th>Date</th>
               <th>Fees</th> 
               <th>City</th> 
          </tr>
        </thead>  
        <tbody>
          <c:forEach var="exam" items="${listExam}" varStatus="status">
������������<tr> 
����������������<td>${exam.examId}</td>
����������������<td>${exam.examName}</td>
����������������<td>${exam.date}</td>
����������������<td>${exam.fees}</td> 
                <td>${exam.c}</td>
����������������<td>
������������������  <a href="register?id=${exam.examId}&city=${exam.c}" >Register</a> 
����������������</td> ������
����������������</tr>
�����������</c:forEach>�
         </tbody>�           
     </table>
   </div>
  </div>
  
  <jsp:include page="footer.jsp" />
  
  <jsp:include page="javaScript.jsp" />
  	
 </body>
</html>
 