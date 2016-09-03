<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">

 <head>
  <meta charset="UTF-8"> 
  <title>Create Course</title>	
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
                <%String userName =(String)session.getAttribute("userName");
                     %>
                <a class="navbar-brand" href="userHome">Home Page Of 
                       <% out.print(userName); %>
                </a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right"> 
                    <li>
                        <a href="userExamSearch">Search Exam</a>
                    </li> 
                    <li>
                        <a href="addUser">Add User</a>
                    </li>
                    <li>
                        <a href="logout">Logout</a>
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
    <form method="POST" action="/ExamScheduler/createCourse">
       <div class="form-group">
             <label for="courseName">Course Name :</label>
             <input name="courseName" type="text" class="form-control" id="courseName" required>
       </div>
       <div>
             <label for="coursePicture">Course Picture:</label>
             <input name ="coursePicture" type="image" class="form-control" id="coursePicture"> 
       </div>
     <button type="submit" class="btn btn-default">Submit</button>
    </form>
   </div>
  </div>
  
  <jsp:include page="footer.jsp" />
  
  <jsp:include page="javaScript.jsp" />
  	
 </body>
</html>