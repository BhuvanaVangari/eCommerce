package com.dnb.eCommerce.utils;

import org.springframework.stereotype.Component;

import com.dnb.eCommerce.dto.Product;
import com.dnb.eCommerce.payload.request.ProductRequest;

@Component
public class RequestToEntityMapper {
	public Product getProductEntityObject(ProductRequest productRequest) {
		Product product=new Product();
		product.setProductName(productRequest.getProductName());
		product.setProductDescription(productRequest.getProductDescription());
		product.setProdPrice(productRequest.getProdPrice());
		product.setProdExpiry(productRequest.getProdExpiry());
		product.setProdCategory(productRequest.getProdCategory());
		return product;
	}
}
