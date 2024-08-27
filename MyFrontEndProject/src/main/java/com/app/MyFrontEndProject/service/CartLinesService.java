package com.app.MyFrontEndProject.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.MyBackEndProject.DAO.CartLinesDAO;
import com.app.MyBackEndProject.DAO.ProductDAO;
import com.app.MyBackEndProject.Modal.Cart;
import com.app.MyBackEndProject.Modal.CartLines;
import com.app.MyBackEndProject.Modal.Product;
import com.app.MyFrontEndProject.model.UserModel;



@Service("cartlinesService")
public class CartLinesService {
	@Autowired
	private HttpSession session;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	CartLinesDAO cartlinesDAO;

	private Cart getCart()
	{
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}

	public String addCartLineProduct(int p_Id) {
		Cart cart = this.getCart();
		CartLines ct = cartlinesDAO.getByCartAndProduct(cart.getId(), p_Id);

		if (ct == null) {
			ct = new CartLines();
			Product p = productDAO.getProduct(p_Id);

			ct.setAvailable(true);
			ct.setBuyingPrice(p.getUnitPrice());
			ct.setCartId(cart.getId());
			ct.setProduct(p);
			ct.setProductCount(1);
			ct.setTotal(ct.getBuyingPrice() * ct.getProductCount());

			cartlinesDAO.add(ct);

			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + ct.getTotal());

			cartlinesDAO.updateCart(cart);
		} 
		else 
		{
			ct.setProductCount(ct.getProductCount() + 1);
			ct.setTotal(ct.getBuyingPrice() + ct.getTotal());
			cart.setGrandTotal(cart.getGrandTotal() + ct.getBuyingPrice());
			
			cartlinesDAO.update(ct);
			cartlinesDAO.updateCart(cart);
			
		}

		return "result=added";

	}
	
	public List<CartLines> getCartLines() {
		Cart cart = this.getCart();
		return cartlinesDAO.list(cart.getId());
	}
	
	
	public String removeCartLine(int c_id) 
	{
		Cart cart = this.getCart();
		CartLines ct = cartlinesDAO.get(c_id);
		cart.setGrandTotal(cart.getGrandTotal() - ct.getTotal());
		cart.setCartLines(cart.getCartLines() - 1);
		
		 cartlinesDAO.remove(ct);
		 cartlinesDAO.updateCart(cart);
		 
		 return "result=deleted" ;
		 
	}
	
	public String updateCartLine(int c_id , int count) 
	{
		Cart cart = this.getCart();
		CartLines ct = cartlinesDAO.get(c_id);
		
		if(count > ct.getProduct().getQuantity())
		{
			return "result=maxcountreach";
		}
		if(count < 1)
		{
			return "result=mincountreach";
		}
		
		double og = ct.getTotal();
		
		ct.setProductCount(count);
		ct.setBuyingPrice(ct.getProduct().getUnitPrice());
		
		ct.setTotal(count * ct.getBuyingPrice());
		
		cart.setGrandTotal(cart.getGrandTotal() - og +  ct.getTotal());
		
		 cartlinesDAO.update(ct);
		 cartlinesDAO.updateCart(cart);
		 
		 return "result=updated" ;
		 
	}
	
	public String validateCartLine() 
	{
		Cart cart = this.getCart();
		
		List<CartLines> cartLines = cartlinesDAO.list(cart.getId());
		
		double grandTotal = 0.0;
		int lineCount = 0;
		
		String response = "result=success";
		
		boolean changed = false;
		Product product = null;
		
		for(CartLines cartLine : cartLines) 
		{					
			product = cartLine.getProduct();
			changed = false;
			
			// check if the product is active or not
			// if it is not active make the availability of cartLine as false
			if((!product.isActive() && product.getQuantity() == 0) && cartLine.isAvailable()) {
				cartLine.setAvailable(false);
				changed = true;
			}			
			// check if the cartLine is not available
			// check whether the product is active and has at least one quantity available
			if((product.isActive() && product.getQuantity() > 0) && !(cartLine.isAvailable())) {
					cartLine.setAvailable(true);
					changed = true;
			}
			
			// check if the buying price of product has been changed
			if(cartLine.getBuyingPrice() != product.getUnitPrice()) {
				// set the buying price to the new price
				cartLine.setBuyingPrice(product.getUnitPrice());
				// calculate and set the new total
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;				
			}
			
			// check if that much quantity of product is available or not
			if(cartLine.getProductCount() > product.getQuantity()) {
				cartLine.setProductCount(product.getQuantity());										
				cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
				changed = true;
				
			}
			
			// changes has happened
			if(changed) {				
				//update the cartLine
				cartlinesDAO.update(cartLine);
				// set the result as modified
				response = "result=modified";
			}
			
			grandTotal += cartLine.getTotal();
			lineCount++;
		}
		
		cart.setCartLines(lineCount++);
		cart.setGrandTotal(grandTotal);
		cartlinesDAO.updateCart(cart);

		return response;
	}	
}

