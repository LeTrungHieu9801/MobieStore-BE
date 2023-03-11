package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.HotProduct;

public interface HotProductRepository extends JpaRepository<HotProduct, Integer> {

}
