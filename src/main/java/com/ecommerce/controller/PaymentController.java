package com.ecommerce.controller;

import java.util.List;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Payment;
import com.ecommerce.entity.User;
import com.ecommerce.service.PaymentService;
import com.ecommerce.service.TokenService;

@RestController
@RequestMapping("/payment")
@CrossOrigin("*")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	@Autowired
	private TokenService tokenService;
	@GetMapping("/{cartsId}")
	public ResponseEntity<?> getListPayment(@PathVariable("cartsId") List<Integer> cartsId , @RequestParam("token") String token) throws AuthenticationFailedException
	{
		// authenticate the token 
				tokenService.authenticates(token);
				
				// find the user
				User user = tokenService.getUser(token);
				
			 paymentService.addPayment(cartsId, user);
				
				return new ResponseEntity<>(" add success!",HttpStatus.OK);
	}
	
	@GetMapping("/getListPayment")
	public ResponseEntity<?> getAllPayment(@RequestParam("token") String token , Pageable pageable) throws AuthenticationFailedException
	{
		// authenticate the token 
		tokenService.authenticates(token);
		
		// find the user
		User user = tokenService.getUser(token);
		Page<Payment> payments = paymentService.getAllPayment(pageable, user);
		return new ResponseEntity<>(payments,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOneItem(@PathVariable("id") Integer id)
	{
		paymentService.deleteOne(id);
		return new ResponseEntity<>("delete successfully",HttpStatus.OK);
	}
}
