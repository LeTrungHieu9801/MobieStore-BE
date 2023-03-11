package com.ecommerce.controller;



import java.util.List;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.common.ApiResponse;
import com.ecommerce.dto.cart.AddCartDto;
import com.ecommerce.dto.cart.CartDto;
import com.ecommerce.entity.User;
import com.ecommerce.service.CartService;
import com.ecommerce.service.TokenService;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

	@Autowired CartService cartService;
	@Autowired TokenService tokenService;
	
	// add to cart Api
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddCartDto addCartDto , @RequestParam("token") String token) throws AuthenticationFailedException
	{
		// authenticate the token 
		tokenService.authenticates(token);
		
		// find the user
		User user = tokenService.getUser(token);
		
		cartService.addToCart(addCartDto,user);
		return new ResponseEntity<>(new ApiResponse(true, "Add to Cart"), HttpStatus.OK);	
	}
	// Get all Cart items for a user
	@GetMapping("/listCart")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) throws AuthenticationFailedException
	{
		// authenticate the token 
		tokenService.authenticates(token);
		
		// find the user
		User user = tokenService.getUser(token);
		// get List cart items
		CartDto cartDto = cartService.ListCartItems(user);
		return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
	}
	// xoa 1 phan tu trong gio hang
		@DeleteMapping(value = "/{id}")
		public ResponseEntity<?> deleteOne(@PathVariable("id") int id)
		{
			cartService.deleteOne(id);
				
			
			return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
		}
	// xoa nhieu phan tu trong gio
		
		@DeleteMapping
		public ResponseEntity<?> deleteAllCartItems(@RequestParam("ids") List<Integer> ids)
		{
			cartService.deleteAllItems(ids);
			return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
		}
}
