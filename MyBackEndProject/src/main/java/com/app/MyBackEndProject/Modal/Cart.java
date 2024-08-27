package com.app.MyBackEndProject.Modal;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.MyBackEndProject.Modal.User;

@Entity
@Table(name="Niit_Jadavpur_Cart")
public class Cart implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private double grandTotal;
	
	private int cartLines;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public double getGrandTotal() 
	{
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal) 
	{
		this.grandTotal = grandTotal;
	}

	public int getCartLines() 
	{
		return cartLines;
	}

	public void setCartLines(int cartLines) 
	{
		this.cartLines = cartLines;
	}
	
	@OneToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
