package com.app.MyBackEndProject;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.MyBackEndProject.DAO.CartLinesDAO;
import com.app.MyBackEndProject.DAO.ProductDAO;
import com.app.MyBackEndProject.Modal.CartLines;
import com.app.MyBackEndProject.Modal.Product;

public class CartLineTest 
{
	private static AnnotationConfigApplicationContext context;
	static Product product;
	static ProductDAO productDAO;
	static CartLines cartlines;
	static CartLinesDAO cartlinesDAO;
	
	@BeforeClass
	public static void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.app.MyBackEndProject");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAO");
		cartlinesDAO = (CartLinesDAO)context.getBean("cartlinesDAO");
	}
	
	@Test
	public void testInsert()
	{
		product = productDAO.getProduct(1);
		
		cartlines = new CartLines();
		
		cartlines.setProduct(product);
		cartlines.setCartId(1);
		cartlines.setProductCount(5);
		cartlines.setBuyingPrice(product.getUnitPrice());
		cartlines.setTotal(cartlines.getBuyingPrice() * cartlines.getProductCount());
		cartlines.setAvailable(true);
		
		assertEquals("Error adding cartline" , true , cartlinesDAO.add(cartlines));
	}
}
