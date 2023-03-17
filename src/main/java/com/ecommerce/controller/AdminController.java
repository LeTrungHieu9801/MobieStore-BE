package com.ecommerce.controller;


import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AdminDto;
import com.ecommerce.dto.user.SignInDto;
import com.ecommerce.service.AdminService;


@RestController
@RequestMapping(value ="admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	AdminService adminService;
	

	
	//SignIn
	@PostMapping("/signin")
	public AdminDto SignIn(@RequestBody SignInDto signInDto) throws AuthenticationFailedException
	{
		return adminService.singIn(signInDto);
	}
}
