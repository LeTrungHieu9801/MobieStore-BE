package com.ecommerce.controller;

import java.util.List;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ReponseDto;
import com.ecommerce.dto.user.SignInDto;
import com.ecommerce.dto.user.SignInReposeDto;
import com.ecommerce.dto.user.SignupDto;
import com.ecommerce.entity.User;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping(value ="user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping()
	public ResponseEntity<?> getAll()
	{
		List<User> users = userService.getAllUsers();
		
		return new ResponseEntity<>(users , HttpStatus.OK);
	}
	
	//SignUp
	@PostMapping("/signup")
	public ReponseDto signup(@RequestBody SignupDto signupDto)
	{
		return userService.signup(signupDto);
	}

	
	//SignIn
	@PostMapping("/signin")
	public SignInReposeDto SignIn(@RequestBody SignInDto signInDto) throws AuthenticationFailedException
	{
		return userService.singIn(signInDto);
	}
}
