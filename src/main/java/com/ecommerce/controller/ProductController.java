package com.ecommerce.controller;



import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.Product;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("ListProduct")
@CrossOrigin("*")
public class ProductController {

	@Autowired ProductService productService;
	
	@GetMapping
	public ResponseEntity<?> getAllProduct(Pageable pageable,@RequestParam(required = false) String search ) {
		
		Page<Product> products = productService.getListProduct(pageable,search);
		Page<ProductDto> dtos = products.map(new Function<Product, ProductDto>() {
			@Override
			public ProductDto apply(Product product)
			{
			ProductDto dto = new ProductDto(product.getId(),product.getCategory(),product.getProductName(),product.getImage(),product.getPrice());
			return dto;
		}
		}) ;

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Integer id){
		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneProduct(@PathVariable("id") Integer id)
	{
		productService.deleteOne(id);
		return new ResponseEntity<>("Delete Successfully!", HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto )
	{
		 productService.createNewProduct(productDto);
		 return new ResponseEntity<>("Create successfully!",HttpStatus.CREATED);
	}
}
