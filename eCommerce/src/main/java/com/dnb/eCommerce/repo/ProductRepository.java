package com.dnb.eCommerce.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.eCommerce.dto.Product;

/*
 *CRUD operations in an e-commerce application on the Product entity
 */

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
	
	//Query:  Select * from Product where productName=?
	List<Product> findByProductName(String productName);
	
}
