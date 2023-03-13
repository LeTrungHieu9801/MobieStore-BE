package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.admin.UserAdmin;
import com.ecommerce.entity.AuthenticationToken;
import com.ecommerce.entity.User;


public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Integer>{

	AuthenticationToken findByUser(User user);

	AuthenticationToken findByToken(String token);
	
	
}
