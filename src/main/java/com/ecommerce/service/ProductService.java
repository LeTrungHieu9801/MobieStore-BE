package com.ecommerce.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.exceptions.ProductNotExistsException;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.specification.ProductSpecification;



@Service
public class ProductService {

	@Autowired ProductRepository productRepository;
	
	@SuppressWarnings("deprecation")
	public Page<Product> getListProduct(Pageable pageable,String search)
	{
		Specification<Product> where = null;
		if(!StringUtils.isEmpty(search))
		{
			ProductSpecification nameSpecification = new ProductSpecification("productName","LIKE",search);
			ProductSpecification categorySpecification = new ProductSpecification("category","LIKE",search);
			where = Specification.where(nameSpecification).or(categorySpecification);
		}
		return productRepository.findAll(where,pageable);
	}
	
	public Product getProductById(Integer id)
	{
		 Optional<Product> optionProduct = productRepository.findById(id);
		 if(optionProduct.isEmpty())
		 {
			 throw new ProductNotExistsException("product is valid"+id);
		 }
		 return optionProduct.get();
	}
	
	public void addProduct(Product product)
	{
		productRepository.save(product);
	}

	public void deleteOne(Integer id) {
		productRepository.deleteById(id);
	}

	public void createNewProduct( ProductDto productDto) {
		if(productRepository.existsByProductName(productDto.getProductName()) == false)
		{
			Product product = new Product();
			product.setCategory(productDto.getCategory());
			product.setDescription(productDto.getDescription());
			product.setProductName(productDto.getProductName());
			product.setImage(productDto.getImage());
			product.setPrice(productDto.getPrice());
			productRepository.save(product);

		} else {
			throw new ProductNotExistsException("product is valid");
		}
	}
}
