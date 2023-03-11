package com.ecommerce.dto.cart;

import com.ecommerce.entity.Product;

public class CartItemDto {
	private Integer id;
	private Product product;
	private Integer qty;
	
	public CartItemDto() {

	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	public CartItemDto(Integer id, Product product, Integer qty) {
		this.id = id;
		this.product = product;
		this.qty = qty;
	}
	
	
	
	
}
