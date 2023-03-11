package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.HotProduct;
import com.ecommerce.service.HotProductService;
import com.ecommerce.service.ProductService;

@RestController
@RequestMapping("listHotProduct")
@CrossOrigin("*")
public class HotProductController {
	
	@Autowired
	private HotProductService hotProductService;
	
	@Autowired 
	private ProductService productService;
	
	@GetMapping()
	public List<ProductDto> getAllListHotProduct()
	{
		List<HotProduct> hotProducts = hotProductService.getListHotProduct();
		List<ProductDto> products = new ArrayList<>();
		for (HotProduct hotProduct : hotProducts) {
			ProductDto product = new ProductDto();
			product.setId(hotProduct.getId());
			product.setProductName(productService.getProductById(hotProduct.getId()).getProductName());
			product.setImage(productService.getProductById(hotProduct.getId()).getImage());
			product.setPrice(productService.getProductById(hotProduct.getId()).getPrice());
			product.setCategory(productService.getProductById(hotProduct.getId()).getCategory());
			products.add(product);
		}
		
		return products;
	}
	
}
