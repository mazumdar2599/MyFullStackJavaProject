package com.app.MyBackEndProject.DAO;

import java.util.List;

import com.app.MyBackEndProject.Modal.Cart;
import com.app.MyBackEndProject.Modal.CartLines;
import com.app.MyBackEndProject.Modal.OrderDetails;

public interface CartLinesDAO 
{
	public List<CartLines> list(int cartId);
	public CartLines get(int id);	
	public boolean add(CartLines cartLine);
	public boolean update(CartLines cartLine);
	public boolean remove(CartLines cartLine);
	boolean updateCart(Cart cart);
	public List<CartLines> listAvailable(int cartId);
	public CartLines getByCartAndProduct(int cartId, int productId);
	
	boolean addOrderDetail(OrderDetails orderDetail);
}
