package com.dnb.eCommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.eCommerce.dto.Product;
import com.dnb.eCommerce.exceptions.IdNotFoundException;
import com.dnb.eCommerce.exceptions.NotUniqueNameException;
import com.dnb.eCommerce.repo.ProductRepository;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product createProduct(Product product) {
		
		/*
		 * TODO Creating new product listing
		 */
		
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(String productId) {

		/*
		 * TODO Retrieve product details by providing its unique ID
		 */
		
		return productRepository.findById(productId);
	}

	@Override
	public Iterable<Product> getAllProducts() {

		/*
		 * TODO Retrieve a list of all available products
		 */
		
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Product product, String productId) throws NotUniqueNameException, IdNotFoundException {

		/*
		 * TODO Update product details based on its unique ID
		 */
		
		/*
		 * Business validations:
		 * 
		 * Ensure that the product with the given ID exists. 
		 * Ensure that the updated product name, if provided, is unique (unless it's the same product being updated)
		 * 
		 * 
		 */		
		
		if (productRepository.existsById(productId)) {
			Optional<Product> temporary = productRepository.findById(productId);
			String prevName = temporary.get().getProductName();
			
			if (prevName.equals(product.getProductName())) {
				return productRepository.save(product);
			}
			
			else {
				
				if (productRepository.findByProductName(product.getProductName()).isEmpty()) {
					return productRepository.save(product);
				} 
				
				else {
					throw new NotUniqueNameException("productName already exists");
				}
				
			}
		} 
		
		else {
			throw new IdNotFoundException("ProductId not found");
		}
	}

	@Override
	public boolean deleteProductById(String productId) throws IdNotFoundException {

		/*
		 * TODO Delete a product by providing its unique ID
		 */
		
		if (productRepository.existsById(productId)) {
			productRepository.deleteById(productId);
			if (productRepository.existsById(productId)) {
				return false;
			} else
				return true;
		} else {
			throw new IdNotFoundException("ProductId not found");
		}
	}

	@Override
	public boolean productExistsById(String productId) {
		
		if (productRepository.existsById(productId)) {
			return true;
		} else {
			return false;
		}
	}

}
