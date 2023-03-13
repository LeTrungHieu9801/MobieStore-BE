package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Payment;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	public Page<Payment> findByUser(Pageable pageable, User user);
	public Payment findByProduct(Product product);
	public boolean existsByProduct(Product product);
	public void deleteByProduct(Product product);
	
}
