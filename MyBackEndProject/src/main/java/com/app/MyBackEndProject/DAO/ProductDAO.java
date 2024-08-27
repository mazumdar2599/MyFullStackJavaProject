package com.app.MyBackEndProject.DAO;

import java.util.List;

import com.app.MyBackEndProject.Modal.Product;

public interface ProductDAO 
{
	boolean insert(Product product);
	boolean update(Product product);
	Product getProduct(int p_id);
	List<Product> listProduct();
	List<Product> listActiveProducts();	
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> topPurchasedProduct();

}