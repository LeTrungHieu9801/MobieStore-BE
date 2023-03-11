package com.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.cart.AddCartDto;
import com.ecommerce.dto.cart.CartDto;
import com.ecommerce.dto.cart.CartItemDto;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.User;
import com.ecommerce.repository.CartRepository;

@Service
public class CartService {

	@Autowired ProductService productService;
	@Autowired CartRepository cartRepository;
	@Transactional
	public void addToCart(AddCartDto addtocartDto,User user)
	{
//		if(user == null)
		// validate product id is valid
		Product product = productService.getProductById(addtocartDto.getProductId());
		
		//save the cart
		Cart cart = new Cart();
			cart.setProduct(product);
			cart.setUser(user);
			if(cartRepository.existsByProductId(addtocartDto.getProductId()) == false)
			{
				cart.setQty(1);
			}
			else {
				cart.setQty(cartRepository.findByProduct(product).getQty() +1);
			}
			cart.setCreatedDate(new Date());
			if(cartRepository.existsByProductId(addtocartDto.getProductId()) == true)
			{
				cartRepository.deleteByProductId(addtocartDto.getProductId());
				cartRepository.save(cart);	
			}
			else
			{
				cartRepository.save(cart);	
			}
	}
	
	public CartDto ListCartItems(User user) 
	{
		List<Cart> carts = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItems = new ArrayList<>();
		double totalCost=0;
		for (Cart cart : carts) {
			CartItemDto cartItemDto = new CartItemDto();
			cartItemDto.setId(cart.getId());
			cartItemDto.setProduct(cart.getProduct());
			cartItemDto.setQty(cart.getQty());
			totalCost += cartItemDto.getQty()*cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		CartDto cartDto = new CartDto();
		cartDto.setCartItems(cartItems);
		cartDto.setTotalCost(totalCost);
		return  cartDto;
	}
	// xoa 1 phan tu
	public void deleteOne(Integer id)
	{
		cartRepository.deleteById(id);
	}
	//xoa nhieu phan tu
	public void deleteAllItems(List<Integer> ids)
	{
		cartRepository.deleteAllById(ids);;
	}
}
