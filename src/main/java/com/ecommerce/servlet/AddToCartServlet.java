package com.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ecommerce.model.Cart;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()) {
			ArrayList<Cart> cartList = new ArrayList<Cart>();
			Cart item = new Cart();
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("id"));
			
			item.setId(id);
			item.setQuantity(1);
			
			session.getAttribute("cart-list");
			ArrayList<Cart> cartListSess = (ArrayList<Cart>) session.getAttribute("cart-list");
			
			if(cartListSess == null) {
				cartList.add(item);
				session.setAttribute("cart-list", cartList);
				response.sendRedirect("index.jsp");
			} else {
				cartList = cartListSess;
				boolean alreadyAdded = false;
				
				for(Cart i : cartList) {
					if(i.getId() == id) {
						alreadyAdded = true;
						out.println("<h3 style='color:crimson; text-align:center'>Item has already been added to cart!<a href='cart.jsp'>Go to Cart</a></h3>");
					}
				}
				
				if(!alreadyAdded) {
					cartList.add(item);
					response.sendRedirect("index.jsp");
				}
			}
		}
	}

}
