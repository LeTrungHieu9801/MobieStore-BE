package com.ecommerce.dto;

public class ProductDto {

	private Integer id;
	private String category;
	private String productName;
	private String image;
	 private int price;
	 
	 
	public ProductDto() {
	
	}

	public ProductDto(Integer id, String category, String productName, String image, int price) {
		this.id = id;
		this.category = category;
		this.productName = productName;
		this.image = image;
		this.price = price;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	 
}
