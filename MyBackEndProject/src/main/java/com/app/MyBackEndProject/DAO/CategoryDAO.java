package com.app.MyBackEndProject.DAO;

import java.util.List;

import com.app.MyBackEndProject.Modal.Category;

public interface CategoryDAO 
{
	public boolean insert(Category category);
	public boolean update(Category category);
	public boolean delete(Category category);
	public Category getCategory(int id);
	public List<Category> categoryList();

}