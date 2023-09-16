package com.dnb.eCommerce.payload.request;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

	@Column(unique=true)
	private String productName;
	@NotBlank(message = "Product description should not be blank")
	private String productDescription;
	@Min(value = 0, message = "Price should not be negative")
	private float prodPrice;
	private LocalDate prodExpiry;
	private String prodCategory;
}
