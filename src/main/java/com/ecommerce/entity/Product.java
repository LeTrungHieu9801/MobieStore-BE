package com.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`product`")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L ;
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "category", length = 50, nullable = false)
    private String category;
	
	@Column(name = "productName", length = 50, nullable = false , unique = true )
    private String productName;
	
	@Column(name = "price")
    private int price;
    
	@Column(name = "`description`", length = 1000, nullable = false)
    private String description;
    
	@Column(name = "image", length = 50, nullable = false)
	private String image;
	
    
	
    public Product(String productName, int price, String image) {
        this.productName = productName;
        this.price = price;
        this.image = image;
    }
    
    public Product()
    {
    	
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
