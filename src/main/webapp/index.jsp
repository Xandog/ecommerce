<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.ecommerce.connection.DBConnection" %>
<%@ page import="com.ecommerce.model.*" %>
<%@ page import="com.ecommerce.dao.*" %>
<%@ page import="java.util.List" %>

<%
	User auth = (User) request.getSession().getAttribute("auth");
	if(auth != null) {
		request.setAttribute("auth", auth);
	}
	
	ProductDao pd = new ProductDao(DBConnection.getConnection());
	List<Product> productList = pd.getAllProducts();
%>

<!DOCTYPE html>
<html>
	<head>
		<%@include file="includes/head.jsp"%>
		<title>E-Commerce</title>
	</head>
	<body>
		<%@include file="includes/navbar.jsp" %>
		<div class="container">
			<div class="card-header my-3">All Products</div>
			<div class="row">
		<%
			if(!productList.isEmpty()) {
				for(Product p : productList) {
		%>		
					
					<div class="col-md-3">
						<div class="card w-100" style="width: 18rem;">
							<img class="card-img-top" src="<%= p.getImage() %>" alt="Card image cap"/>
							<div class="card-body">
								<h5 class="card-title"><%= p.getName() %></h5>
								<h6 class="price">Price: $<%= p.getPrice() %></h6>
								<h6 class="category">Category: <%= p.getCategory() %></h6>
								<div class="mt-3 d-flex justify-content-between">
									<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Add to Cart</a>
									<a href="#" class="btn btn-primary">Buy Now</a>
								</div>
							</div>
						</div>
					</div>
		<%
				}
			}
		%>
			</div>
		</div>
		<%@include file="includes/footer.jsp"%>
	</body>
</html>
