package com.app.MyFrontEndProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.MyBackEndProject.DAO.CartLinesDAO;
import com.app.MyFrontEndProject.service.CartLinesService;

@Controller
@RequestMapping(value="/cart")
public class CartController 
{

	@Autowired
	CartLinesDAO cartlinesDAO;
	
	@Autowired
	CartLinesService cartlinesService;
	
	
	@RequestMapping(value="/show")
	public ModelAndView showCart(@RequestParam(name = "result", required = false) String result)
	{
		ModelAndView mv = new ModelAndView("index");
		
		mv.addObject("userclickshowcart",true);
		mv.addObject("title" , "Online Shopping - User Cart");
		if(result != null)
		{
				switch(result) 
				{
					case "added":
						mv.addObject("message", "CartLine has been successfully added!");
						break;
					case "deleted":
						mv.addObject("message", "CartLine has been successfully removed!");
						break;
					case "updated":
						mv.addObject("message", "CartLine has been successfully updated!");
						break;
					case "maxcountreach":
						mv.addObject("message", "Quantity Cannot be greater than the available product quantity!");
						break;
					case "mincountreach":
						mv.addObject("message", "Quantity Cannot be less than 1!");
						break;
					case "modified":
						mv.addObject("message", "One or more items inside cart has been modified!");
						break;
				}
		}
		
		mv.addObject("cartlines" , cartlinesService.getCartLines() );
		return mv;
	}
	
	
	@RequestMapping(value="/add/{id}/product")
	public String addCartProduct(@PathVariable("id") int p_id)
	{
		String result = cartlinesService.addCartLineProduct(p_id);
		
		return "redirect:/cart/show?"+result;
	}
	
	@RequestMapping("/{cartLineId}/remove")
	public String removeCartLine(@PathVariable int cartLineId) {
		String response = cartlinesService.removeCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCartLine(@PathVariable int cartLineId , @RequestParam(name = "count", required = false) int count) {
		String response = cartlinesService.updateCartLine(cartLineId , count);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping("/validate")
	public String validateCart() 
	{
		String response = cartlinesService.validateCartLine();
		
		if(!response.equals("result=success"))
		{
			return "redirect:/cart/show?"+response;
		}
		else 
		{
			return "redirect:/cart/checkout";
		}
	}

}

