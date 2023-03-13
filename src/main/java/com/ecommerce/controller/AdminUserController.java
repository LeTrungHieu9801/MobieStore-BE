//package com.ecommerce.controller;
//
//
//import javax.mail.AuthenticationFailedException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ecommerce.dto.ReponseDto;
//import com.ecommerce.dto.user.SignInDto;
//import com.ecommerce.dto.user.SignInReposeDto;
//import com.ecommerce.dto.user.SignupDto;
//import com.ecommerce.service.AdminService;
//
//@RestController
//@RequestMapping(value ="admin")
//@CrossOrigin("*")
//public class AdminUserController {
//
//	@Autowired
//	 AdminService adminService;
//	
//
//	//SignUp
//	@PostMapping("/signup")
//	public ReponseDto signup(@RequestBody SignupDto signupDto)
//	{
//		return adminService.signup(signupDto);
//	}
//
//	
//	//SignIn
//	@PostMapping("/signin")
//	public SignInReposeDto SignIn(@RequestBody SignInDto signInDto) throws AuthenticationFailedException
//	{
//		return adminService.singIn(signInDto);
//	}
//}
