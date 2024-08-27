package com.app.MyBackEndProject.DAOIMPL;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.MyBackEndProject.DAO.CartLinesDAO;
import com.app.MyBackEndProject.Modal.Cart;
import com.app.MyBackEndProject.Modal.CartLines;
import com.app.MyBackEndProject.Modal.OrderDetails;


@Repository("cartlinesDAO")
@Transactional
public class CartLinesDAOIMPL implements CartLinesDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean add(CartLines cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLines cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(CartLines cartLine) {	
		try {			
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}


	@Override
	public List<CartLines> list(int cartId) {
		String query = "FROM CartLines WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLines.class)
									.setParameter("cartId", cartId)
										.getResultList();		
	}

	@Override
	public CartLines get(int id) {		
		return sessionFactory.getCurrentSession().get(CartLines.class, Integer.valueOf(id));
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<CartLines> listAvailable(int cartId) {
		String query = "FROM CartLines WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLines.class)
									.setParameter("cartId", cartId)
									.setParameter("available", true)
										.getResultList();
	}
	
	@Override
	public CartLines getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLines WHERE cartId = :cartId AND product.id = :productId";
		try {
			
			return sessionFactory.getCurrentSession()
									.createQuery(query,CartLines.class)
										.setParameter("cartId", cartId)
										.setParameter("productId", productId)
											.getSingleResult();
			
		}
		catch(Exception ex) 
		{
			return null;	
		}
		
	}
	
	@Override
	public boolean addOrderDetail(OrderDetails orderDetail) {
		try {			
			sessionFactory.getCurrentSession().persist(orderDetail);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
}
