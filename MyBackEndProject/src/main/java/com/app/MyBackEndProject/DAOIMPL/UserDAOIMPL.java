package com.app.MyBackEndProject.DAOIMPL;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.MyBackEndProject.DAO.UserDAO;
import com.app.MyBackEndProject.Modal.Address;
import com.app.MyBackEndProject.Modal.User;

@Transactional
@Repository("userDAO")
public class UserDAOIMPL implements UserDAO 
{
	@Autowired
	SessionFactory sessionFactory;
	
	public boolean insert(User user) 
	{
		try
		{
			sessionFactory.getCurrentSession().persist(user);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	public User getUser(String email) 
	{
	
		try
		{
			String selectActiveCategory = "FROM User WHERE email = :email";
			
			Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
					
			query.setParameter("email", email);
							
			return (User) query.getSingleResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<User> getSupplierList() 
	{
		try
		{
			String selectuser = "FROM User WHERE role = :role and enabled = :enabled";
			
			Query query = sessionFactory.getCurrentSession().createQuery(selectuser);
					
			query.setParameter("role", "SUPPLIER");
			query.setParameter("enabled", true);
							
			return query.getResultList();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public boolean insertAddress(Address address) 
	{
		try
		{
			sessionFactory.getCurrentSession().persist(address);
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Address> getShippingAddress(int id) 
	{
		try
		{
			String selectuser = "FROM Address WHERE userId = :userID and shipping = :shipping";
			
			Query query = sessionFactory.getCurrentSession().createQuery(selectuser);
					
			query.setParameter("userID", id);
			query.setParameter("shipping", true);
							
			return query.getResultList();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public Address getBillingAddress(int id) 
	{
		try
		{
			String selectuser = "FROM Address WHERE userId = :userID and billing = :billing";
			
			Query query = sessionFactory.getCurrentSession().createQuery(selectuser);
					
			query.setParameter("userID", id);
			query.setParameter("billing", true);
							
			return (Address) query.getSingleResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Address getAddress(int addressid) 
	{
		try
		{
			String selectuser = "FROM Address WHERE id = :addressID";
			
			Query query = sessionFactory.getCurrentSession().createQuery(selectuser);
					
			query.setParameter("addressID", addressid);
							
			return (Address) query.getSingleResult();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}


}