<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product Edition | Aswesome App</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	
	<div class = "pt-5 container">
			<h2>Product ${product.id}</h2>
			<hr>
		<div class = " row justify-content-center">

			<div class = "col-md-8">
				 <form:form action = "${pageContext.request.contextPath}/products" method="POST" modelAttribute="product">
				
					<div class = "form-group">
					  <label for="name">Name</label>
					  <form:input path="name" class = "form-control" />
					</div>
				
					<div class = "form-group">
						<label for="price">Price</label>
						<form:input path="price" class = "form-control"/>
					</div>
				
					<div class="form-group">
						<label for="quantity">Quantity</label>
						<form:input path="quantity" class = "form-control"/>
					</div>
					
					<div class="form-group">
						<label for="quantity">Height</label>
						<form:input path="productSize.height" class = "form-control"/>
					</div>
					
					<div class="form-group">
						<label for="quantity">Width</label>
						<form:input path="productSize.width" class = "form-control"/>
					</div>
					
					<div class="form-group">
						<label for="quantity">Weight</label>
						<form:input path="productSize.weight" class = "form-control"/>
					</div>
					
					<div class="form-group">
					<label for="manufacturer">Manufacturer</label> <br>
						<form:select class = "form-control" path="manufacturer"
						 items="${manufacturers}" itemLabel="name" itemValue="id"/>
					</div>
					
					<div class="form-group">
					<label for="tag">Tag</label> <br>
					<form:select class = "form-control" path="tags" items="${tags}"
					 itemLabel="name" itemValue="id"/>
					</div>
					
					<form:hidden path="id" />
				
					<a class = "btn btn-info" href = "${pageContext.request.contextPath}/products" >Back to list</a>
					<button class = "btn btn-primary" type = "submit" >Save</button>
				</form:form>
        
			</div>
		</div>
	</div>
	

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>