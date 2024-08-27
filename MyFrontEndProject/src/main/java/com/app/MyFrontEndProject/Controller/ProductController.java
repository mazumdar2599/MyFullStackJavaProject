package com.app.MyFrontEndProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.MyBackEndProject.DAO.CategoryDAO;
import com.app.MyBackEndProject.DAO.ProductDAO;

@Controller
@RequestMapping(value="/product")
public class ProductController 
{
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@RequestMapping(value="/view/all/product")
	public ModelAndView viewAllProduct()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - All Products");
		mv.addObject("userclickallproducts" , true);
		mv.addObject("categoryList" , categoryDAO.categoryList());
		return mv;
	}

	@RequestMapping(value="/view/category/{id}/product")
	public ModelAndView viewCategoryProduct(@PathVariable("id") int c_id)
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - Category Products");
		mv.addObject("userclickcategoryproducts" , true);
		mv.addObject("categoryList" , categoryDAO.categoryList());
		mv.addObject("category" , categoryDAO.getCategory(c_id));
		return mv;
	}
	
	@RequestMapping(value="/view/single/{id}/product")
	public ModelAndView viewSingleProduct(@PathVariable("id") int p_id)
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - Category Products");
		mv.addObject("userclicksingleproduct" , true);
		mv.addObject("categoryList" , categoryDAO.categoryList());
		mv.addObject("product" ,productDAO.getProduct(p_id) );
		return mv;
	}
}

