<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns:mso="urn:schemas-microsoft-com:office:office" xmlns:msdt="uuid:C2F41010-65B3-11d1-A29F-00AA00C14882">
 
 <head>
  <meta charset="UTF-8"> 
  <title>Register For Examination</title>	
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
                        <a href="examSearch">Search Exam</a>
                    </li>  
                    <li>
                        <a href="login">Login</a>
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
    <form method="POST" action="/ExamScheduler/register">
     <div class="form-group">
       <label for="examId">Exam Id:</label>
       <input name="examId" type="number" class="form-control" id="examId" value='<%=Integer.parseInt(request.getParameter("id"))%>'> 
     </div>
     <div class="form-group">
       <label for="city">City:</label>
       <input name="city" type="text" class="form-control" id="city" value='<%=request.getParameter("city")%>'> 
     </div>
     <div class="form-group">
       <label for="email">Email address:</label>
       <input name="userName" type="email" class="form-control" id="email">
     </div>  
     <div class="form-group">
       <label for="address">Address</label>
       <textarea name="address"class="form-control" rows="5" id="address"></textarea>
     </div>
     <button type="submit" class="btn btn-default">Submit</button>
    </form>
   </div>
  </div>
  
  <jsp:include page="footer.jsp" />
  
  <jsp:include page="javaScript.jsp" />
  	
 </body>
</html>