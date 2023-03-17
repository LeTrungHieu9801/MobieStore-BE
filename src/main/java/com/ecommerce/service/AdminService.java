package com.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Objects;

import javax.mail.AuthenticationFailedException;

import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.UserAdmin;
import com.ecommerce.dto.AdminDto;
import com.ecommerce.dto.user.SignInDto;
import com.ecommerce.repository.AdminRepository;



@Service
public class AdminService {

	@Autowired
	AdminRepository userRepository;
		
	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter
				.printHexBinary(digest).toUpperCase();
			return hash;
		}

	public AdminDto singIn(SignInDto signInDto) throws AuthenticationFailedException {
		
		UserAdmin user = userRepository.findByEmail(signInDto.getEmail());
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
		AdminDto dto = new AdminDto();
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		
		return dto;
	}

}
