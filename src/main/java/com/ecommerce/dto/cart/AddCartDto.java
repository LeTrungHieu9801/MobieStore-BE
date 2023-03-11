package com.ecommerce.dto.cart;

import lombok.NonNull;

public class AddCartDto {

//	private Integer id;
	private @NonNull Integer productId;
	private @NonNull Integer qty;
	
	public AddCartDto() {
		
	}
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
}
