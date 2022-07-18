package com.ecommerce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Product;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<Product> getAllProducts() {
		List<Product> productList = new ArrayList<Product>();
		
		try {
			query = "SELECT * FROM products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setPrice(rs.getString("price"));
				product.setImage(rs.getString("image"));
				
				productList.add(product);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return productList;
	}
}
