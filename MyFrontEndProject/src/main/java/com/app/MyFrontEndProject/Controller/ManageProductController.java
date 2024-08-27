package com.app.MyFrontEndProject.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.MyBackEndProject.DAO.CategoryDAO;
import com.app.MyBackEndProject.DAO.ProductDAO;
import com.app.MyBackEndProject.DAO.UserDAO;
import com.app.MyBackEndProject.Modal.Category;
import com.app.MyBackEndProject.Modal.Product;
import com.app.MyBackEndProject.Modal.User;
import com.app.MyFrontEndProject.util.FileUpload;
import com.app.MyFrontEndProject.validator.ProductValidation;


@Controller
@RequestMapping(value={"/manage"})
public class ManageProductController 
{
	@Autowired
	CategoryDAO categoryDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value={"/{id}/product"})
	public ModelAndView editProduct(@PathVariable("id") int p_id)
	{
		ModelAndView mv = new ModelAndView("index");
		
		mv.addObject("newProduct" , productDAO.getProduct(p_id));
		
		mv.addObject("userclickmanageproduct" , true);
		return mv;
	}
	
	@RequestMapping(value={"/product"})
	public ModelAndView manageProduct(@RequestParam(name="operation", required=false) String operation)
	{
		ModelAndView mv = new ModelAndView("index");
		
		mv.addObject("newProduct" , new Product());
		
		mv.addObject("userclickmanageproduct" , true);
		
		if(operation != null) 
		{
			if(operation.equals("product"))
			{
				mv.addObject("message", "Product added successfully!");
			}
			if(operation.equals("category"))
			{
				mv.addObject("message", "Category added successfully!");
			}
		}
		return mv;	
	}
	
	@RequestMapping(value={"/add/product"})
	public String addProduct(@Valid @ModelAttribute("newProduct") Product p,BindingResult results , ModelMap model,HttpServletRequest request)
	{
		if(p.getId() == 0)
		{
			new ProductValidation().validate(p,results);
		}
		else
		{
			
			if(!(p.getFile().getOriginalFilename().equals("") || p.getFile() == null))
			{
				new ProductValidation().validate(p,results);
			}
			
		}
		
		
		if(results.hasErrors()) 
		{
			
			model.addAttribute("userclickmanageproduct",true);
			return "index";
		}
		
		
		else
		{
			
			if(p.getId() == 0)
			{
				p.setActive(true);
				productDAO.insert(p);
			}
			else
				productDAO.update(p);
			
			if(! p.getFile().getOriginalFilename().equals("") )
			{
				FileUpload.uploadFile(request, p.getFile(), p.getCode()); 
			 }
			
			return "redirect:/manage/product?operation=product";
		}
			
		
	}
	
	@RequestMapping(value={"/add/category"})
	public String addCategory(@ModelAttribute("category") Category c)
	{
		c.setActive(true);
		categoryDAO.insert(c);
		
		return "redirect:/manage/product?operation=category";
	}
	
	@ModelAttribute("categorylist") 
	public List<Category> modelCategoryList() 
	{
		return categoryDAO.categoryList();
	}
	@ModelAttribute("supplierlist") 
	public List<User> modelSupplierList() 
	{
		return userDAO.getSupplierList();
	}
	
	@ModelAttribute("category") 
	public Category modelCategory() 
	{
		return new Category();
	}
	
	@RequestMapping(value = "/product/{id}/activation", method= {RequestMethod.POST})
	@ResponseBody
	public String handleProductAvtivation(@PathVariable int id)
	{
		System.out.println("Entered Activation method");
		Product product = productDAO.getProduct(id);
		
		boolean  isActive= product.isActive();
		System.out.println("Product : " + product.isActive());
		product.setActive(!isActive);
		System.out.println("Product : " + product.isActive());
		productDAO.update(product);	
		
		
		return (isActive)? 
				"Successfully Deactivated the product with id : " +product.getId()
				: "Successfully Activated the product with id : " +product.getId();
				
	}

}

