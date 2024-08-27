package com.app.MyBackEndProject;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.MyBackEndProject.DAO.ProductDAO;
import com.app.MyBackEndProject.Modal.Product;

public class ProductTest 
{

	private static AnnotationConfigApplicationContext context;
	static Product product;
	static ProductDAO productDAO;	
	
	@BeforeClass
	public static void init() 
	{
		context = new AnnotationConfigApplicationContext();
		context.scan("com.app.MyBackEndProject");
		context.refresh();
		
		productDAO = (ProductDAO)context.getBean("productDAO");
	}
	
	@Test
	public void testInsert()
	{
		product = new Product();
	
		product.setName("SamsungGalaxy s23 Ultra");
		product.setBrand("Samsung");
		product.setDescription("Pro camera system\r\n" + 
				"\r\n" + 
				"120MP Main | Ultra Wide | Telephoto\r\n" + 
				"\r\n" + 
				"Super-high-resolution photos\r\n" + 
				"(50MP and 48MP)\r\n" + 
				"\r\n" + 
				"Next-generation portraits with Focus and Depth Contro");
		product.setUnitPrice(89499.00);
		product.setActive(true);
		product.setCategoryId(1);
		product.setSupplierId(2);
		product.setQuantity(10);
		
		assertEquals("Error" , true , productDAO.insert(product));
	}
	
	//@Test
	public void testGetProduct()
	{
		product = productDAO.getProduct(1);
		
		assertEquals("Error" , "PRDf311b4016544", product.getCode());
	}
	
	//@Test
	public void testActiveProduct()
	{
		assertEquals("Error" ,3, productDAO.listActiveProducts().size());
	}
	
	//@Test
	public void testActiveCategoryProduct()
	{
		assertEquals("Error" ,2, productDAO.listActiveProductsByCategory(5).size());
	}
	
}
