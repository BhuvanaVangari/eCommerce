package com.dnb.eCommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.eCommerce.dto.Product;
import com.dnb.eCommerce.exceptions.IdNotFoundException;
import com.dnb.eCommerce.exceptions.InvalidIdException;
import com.dnb.eCommerce.exceptions.NotUniqueNameException;
import com.dnb.eCommerce.payload.request.ProductRequest;
import com.dnb.eCommerce.service.ProductService;
import com.dnb.eCommerce.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ProductController {

	/*
	 * REST API Endpoints for CRUD Operations
	 * 
	 * 
	 * **1. Create a Product** 
	 * 		- Endpoint: POST /api/products 
	 * 
	 * **2. Retrieve All Products** 
	 * 		- Endpoint: GET /api/products 
	 * 
	 * **3. Retrieve a Product by ID** 
	 * 		- Endpoint: GET /api/products/{productId} 
	 * 
	 * **4. Update a Product** 
	 * 		- Endpoint: PUT/api/products/{productId} 
	 * 
	 * **5. Delete a Product** 
	 * 		- Endpoint: DELETE/api/products/{productId}
	 * 
	 */
	
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RequestToEntityMapper mapper;
	
	@PostMapping("/products")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest productRequest){
		Product product =mapper.getProductEntityObject(productRequest);
		Product product2 = productService.createProduct(product);
		return new ResponseEntity(product2, HttpStatus.CREATED);
	}

	@GetMapping("/products")
	public ResponseEntity<?> getAllProducts() {
		Iterable<Product> list = productService.getAllProducts();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<?> getProductById(@PathVariable("productId") String productId) throws InvalidIdException{
		Optional<Product> optional=productService.getProductById(productId);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			throw new InvalidIdException("Account id is not valid");
		}
		
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<?> updateProduct(@PathVariable String productId,@Valid @RequestBody ProductRequest productRequest) throws IdNotFoundException, NotUniqueNameException{
		if(productService.productExistsById(productId)) {
			Product product=mapper.getProductEntityObject(productRequest) ;
			product.setProductId(productId);
			return ResponseEntity.ok(productService.updateProduct(product, productId));
		}
		else {
			throw new IdNotFoundException("productId does not exist");
		}
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProductById(@PathVariable("productId") String productId) throws IdNotFoundException{
		if(productService.productExistsById(productId)) {
			productService.deleteProductById(productId);
			return (ResponseEntity<?>)ResponseEntity.noContent().build();
		}
		else {
			throw new IdNotFoundException("productId does not exist");
		}
	}
	
}
