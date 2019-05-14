<%@ page language="java"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!--    c标签要使用,那么就必须要有它 -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set scope="page" var="url"
    value="${pageContext.request.contextPath }"></c:set>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>新增用户</title>
<head>	
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page"> 
 

</head>

<body>

<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
    </div>
    <ul class="nav navbar-nav">
      <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">Movie-Admin
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="queryAll">See Movie</a></li>
            <li><a href="AddMovie.jsp">Add Movie</a></li>
          </ul>
      </li>
      <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">User-Admin
          <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="/queryALLuser">Manage Users</a></li>
            
          </ul>
      </li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
</ul>
  </div>
</nav>


<div  class = "container-fluid">
    <div class="row">
      <div class="col-md-3"></div>
      <div class="col-md-6">
            <h1>Movie Management</h1>
            <h2>Movies</h2>
            <a href="queryAll"><button type="button" class="btn btn-lg	">Show all Movies</button></a><br><br>
            <a href="AddMovie.jsp"><button type="button" class="btn btn-lg	">Add an Movie</button></a>
            <h2>Users</h2>
            <a href="allUser"><button type="button" class="btn btn-lg	">Show All users</button>      
      </div>
      <div class="col-md-3"></div>
    </div>
 </div>

  <!--  
     <div align="center"style="width: 400px; position: relative;left:450px;">
        <form action="${url}/queryAll" method="post"> 
             <input type="submit" value="View all Movies"/> <br/>
            <table border="1"  cellspacing="0"> 
                 <thead>
                  <tr><td>ID</td><td>Moviename</td><td>Movieicon</td><td>date</td><td>Type</td><td>Director</td><td>Actors</td><td>isshow</td><td>price</td><td>rank</td></tr>
                 
                 </thead>
                 <tbody>
         <c:forEach items="${list}" var="list">
         <tr>
                <td>${list.getId() }</td>
                <td>${list.moviename}</td>
                <td>${list.movieicon }</td>
                <td>${list.datestr }</td> 
                <td>${list.type }</td>
                <td>${list.director }</td> 
                <td>${list.actors }</td>                
                <td>${list.isshow }</td> 
                <td>${list.price }</td> 
                <td>${list.rank }</td>                                                         
         </tr>
         </c:forEach>
                 </tbody>
            </table>
            <hr />
        </form>
    </div>
 -->

</body>
</html>