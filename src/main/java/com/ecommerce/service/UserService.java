package com.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import javax.mail.AuthenticationFailedException;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ecommerce.dto.ReponseDto;
import com.ecommerce.dto.user.SignInDto;
import com.ecommerce.dto.user.SignInReposeDto;
import com.ecommerce.dto.user.SignupDto;
import com.ecommerce.entity.AuthenticationToken;
import com.ecommerce.entity.User;
import com.ecommerce.exceptions.CustomException;
import com.ecommerce.repository.UserRepository;


@Service

public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TokenService tokenService;
	
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	@Transactional	
	public ReponseDto signup(SignupDto signupDto) throws CustomException {
		
		if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail())))
		{
			throw new CustomException("user already present");
		}

//		 hash the password
		String encryptedpassword = signupDto.getPassword();
		try {
			 encryptedpassword = hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		User user = new User(signupDto.getFirstName(),signupDto.getLastName(),signupDto.getEmail(),encryptedpassword);
		userRepository.save(user);
//		create the Token
		final AuthenticationToken authenticationToken =  new AuthenticationToken(user);
		tokenService.saveConfirmationToken(authenticationToken);
		
		ReponseDto reponseDto = new ReponseDto("success", "message");
		return reponseDto;
	}
	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter
				.printHexBinary(digest).toUpperCase();
			return hash;
		}

	public SignInReposeDto singIn(SignInDto signInDto) throws AuthenticationFailedException {
		
		User user = userRepository.findByEmail(signInDto.getEmail());
		if(Objects.isNull(user))
		{
			throw new AuthenticationFailedException("user is not valid");
		}
		try {
			if(!user.getPassword().equals(hashPassword(signInDto.getPassword())))
			{
				throw new AuthenticationFailedException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		AuthenticationToken token = tokenService.getToken(user);
		
		if(Objects.isNull(token))
		{
			throw new CustomException("token is not present" );
		}
		SignInReposeDto signInReposeDto = new SignInReposeDto();
		signInReposeDto.setStatus("success");
		signInReposeDto.setToken(token.getToken());
		signInReposeDto.setUser(user);
		return signInReposeDto;
	}

}
