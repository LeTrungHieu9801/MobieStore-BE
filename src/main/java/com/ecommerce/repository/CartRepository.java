package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	public List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
	public boolean existsByProductId(Integer productId);
	public Cart findByProduct(Product product);
	public void deleteByProductId(Integer productId);
	
}
