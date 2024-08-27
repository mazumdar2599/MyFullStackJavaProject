package com.app.MyBackEndProject.Modal;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Niit_Jadavpur_Product")
public class Product implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String code;
	
	@NotBlank(message="Product Name cannot be empty")
	private String name;
	
	@NotBlank(message="Brand Name cannot be empty")
	private String brand;
	
	@Size(min=5 , max=250 , message="Length of the Discription must be between 5 and 250")
	private String description;
	
	
	@Column(name = "unit_price")
	private double unitPrice;
	
	
	private int quantity;
	
	@Column(name = "is_active")
	private boolean active;
	
	@Column(name = "category_id")
	private int categoryId;
	
	@JsonIgnore
	@Column(name = "supplier_id")
	private int supplierId;
	
	
	@Transient
	private MultipartFile file;

	@Column(name = "purchase_count")
	private int purchases;
	
	public int getPurchases() 
	{
		return purchases;
	}

	public void setPurchases(int purchases) 
	{
		this.purchases = purchases;
	}



	public Product() 
	{	
		code = "PRD" + UUID.randomUUID().toString().substring(24);
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public double getUnitPrice() {
		return unitPrice;
	}



	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public int getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public int getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}



	public MultipartFile getFile() {
		return file;
	}



	public void setFile(MultipartFile file) {
		this.file = file;
	}


}
