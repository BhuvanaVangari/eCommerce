package com.dnb.eCommerce.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.dnb.eCommerce.utils.CustomIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data					//Equivalent to @Getter @Setter 
@EqualsAndHashCode
@NoArgsConstructor		//
@ToString
@Entity					//Specifies that the class is an entity
public class Product {
	
	
	/*
	 * Product entity fields: 
	 * 
	 * productId 
	 * productName 
	 * productDescription 
	 * prodPrice
	 * prodExpiry 
	 * prodCategory
	 * 
	 */
	
	
	/* BusinessValidation 
	 * 
	 * - Ensure that the product name is unique.
     * - Validate that the price is non-negative.
     * - Check that the product description is not empty.
	 * 
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "product_seq")
	@GenericGenerator(name = "product_seq", strategy = "com.dnb.eCommerce.utils.CustomIdGenerator",
	parameters =  {@Parameter(name=CustomIdGenerator.INCREMENT_PARAM,value="50"),
			@Parameter(name=CustomIdGenerator.NUMBER_FORMAT_PARAMETER,value="%05d"),
			@Parameter(name=CustomIdGenerator.VALUE_PREFIX_PARAMETER,value="Pro_")}
			)
	private String productId;
	@Column(unique=true)
	private String productName;
	@NotBlank(message = "Product description should not be blank")
	private String productDescription;
	@Min(value = 0, message = "Price should not be negative")
	private float prodPrice;
	private LocalDate prodExpiry;
	private String prodCategory;
}
