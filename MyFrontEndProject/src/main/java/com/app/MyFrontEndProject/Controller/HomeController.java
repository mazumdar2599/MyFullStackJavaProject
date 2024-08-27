package com.app.MyFrontEndProject.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.MyBackEndProject.DAO.CategoryDAO;

@Controller
public class HomeController 
{
	@Autowired
	CategoryDAO categoryDAO;

	@RequestMapping(value={"/","/home","/index"})
	public ModelAndView index()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - Home");
		mv.addObject("userclickhome" , true);
		mv.addObject("categoryList" , categoryDAO.categoryList());
		return mv;
	}
	
	@RequestMapping(value="/about")
	public ModelAndView about()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - About Us");
		mv.addObject("userclickaboutus" , true);
		return mv;
	}
	
	@RequestMapping(value="/register")
	public ModelAndView register()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - About Us");
		mv.addObject("userclickregister" , true);
		return mv;
	}
	
	@RequestMapping(value="/contact")
	public ModelAndView contact()
	{
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("title" , "Online Shopping - Contact Us");
		mv.addObject("userclickcontactus" , true);
		return mv;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,@RequestParam(name="logout", required = false)	String logout)
	{
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("title" , "Online Shopping - LOGIN");
		
		if(error!= null) 
		{
			mv.addObject("message", "Username and Password is invalid!");
		}
		
		if(logout!= null) 
		{
			mv.addObject("logout", "You have successfully logged out.");
		}
		return mv;
	}
	
	@RequestMapping(value = "/custom-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    
	    return "redirect:/login?logout";
	}
	
}
