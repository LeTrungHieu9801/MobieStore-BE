package com.ecommerce.service;

import java.util.Objects;

import javax.mail.AuthenticationFailedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.admin.UserAdmin;
import com.ecommerce.entity.AuthenticationToken;
import com.ecommerce.entity.User;
import com.ecommerce.repository.AuthenticationTokenRepository;

@Service
public class TokenService {

	@Autowired AuthenticationTokenRepository tokenRepository;
	public void saveConfirmationToken(AuthenticationToken authenticationToken)
	{
		tokenRepository.save(authenticationToken);
	}
	public AuthenticationToken getToken(User user) {
		return tokenRepository.findByUser(user);
	
	}

	
	public User getUser(String token) 
	{
		final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if(Objects.isNull(authenticationToken))
		{
			return null;
		}
		return authenticationToken.getUser();
	}
	
	public void authenticates(String token) throws AuthenticationFailedException
	{
		if(Objects.isNull(token))
		{
			throw new AuthenticationFailedException("Token not present");
		}
		
		if(Objects.isNull(getUser(token)))
		{
			throw new AuthenticationFailedException("Token not valid");
		}
	}
}
