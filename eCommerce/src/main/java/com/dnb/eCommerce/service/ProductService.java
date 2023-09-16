package com.dnb.eCommerce.service;

import java.util.Optional;

import com.dnb.eCommerce.dto.Product;
import com.dnb.eCommerce.exceptions.IdNotFoundException;
import com.dnb.eCommerce.exceptions.NotUniqueNameException;

public interface ProductService {
		
	/*
	 * list of REST API endpoints for CRUD operations
	 * 
	 * **1. Create a Product**
	 * **2. Retrieve All Products**
	 * **3. Retrieve a Product by ID**
	 * **4. Update a Product**
	 * **5. Delete a Product**
	 * 
	 */
	
	
	public Product createProduct(Product product) ;
	
	public Optional<Product> getProductById(String productId);
	
	public Iterable<Product> getAllProducts();
	
	public Product updateProduct(Product product,String productId) throws NotUniqueNameException, IdNotFoundException;
	
	public boolean deleteProductById(String accountId) throws IdNotFoundException;
	
	//used in retrieve, update, delete operations
	public boolean productExistsById(String productId);
}
