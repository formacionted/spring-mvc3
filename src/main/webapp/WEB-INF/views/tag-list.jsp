<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tag List | Awesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style type="text/css">

.badge a{ 
text-decoration:none;
transition:0.4s;
color:#ffffff;
}
a{
color:#4747ff;
transition:0.4s;
text-decoration:none;
}
a:hover{ 
opacity:0.7;
text-decoration:none;
}
</style>
</head>
<body>
		<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="/">Awesome App</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/products">Products</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/manufacturers">Manufacturers</a>
      </li>  
      <li class="nav-item active">
        <a class="nav-link" href="/tags">Tags</a>
      </li>  
    </ul>
  </div>
</nav>
	</header>
	<div class = "pt-5 container">
		
		<h1>Tag Directory</h1>
		<hr/>
		
		<p>${NOTIFICATION}</p>
		
		<p>
			<a class = "btn btn-primary" href="${pageContext.request.contextPath}/tags/empty">Add Tag</a>
			
		</p>
	
		<table class = "table table-striped table-bordered">
			
			<tr class = "thead-dark">
				<th>Name</th>
				<th>Products</th>
				<th>Actions</th>
			</tr>
			
			<c:forEach items="${tags}" var="tag">
			
				<tr>
					<td>${tag.name}</td>
					<td>
						<c:forEach items="${tag.products}" var="product">
						 <span class="badge badge-info"><a href="${pageContext.request.contextPath}/products/${product.id}">${product.name}</a></span>
						 </c:forEach>
					</td>
					<td> 
						<a class="btn btn-info" href = "${pageContext.request.contextPath}/tags/${tag.id}">Edit</a> 
						<a class="btn btn-danger" href = "${pageContext.request.contextPath}/tags/${tag.id}/delete">Delete</a> 
					</td>
				</tr>
				
			</c:forEach>
			
		</table>
		
	</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>