package com.jilit.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jilit.entities.UserToken;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class TokenController {

	//method to render home page
	@RequestMapping(value="/tokens",
			method=RequestMethod.GET)
	public String home()
	{
		
		return "home is inovked.";
	}
	
	@RequestMapping(value="/tokens", 
			method=RequestMethod.POST,
			consumes="application/json")
	public String generateToken(
			@RequestBody UserToken user)
	{
		//username & password will be 
		//authenticated using the 
		//UserDetailsService if the authentication
		//is successful, a JWT Token is generated
		String token = Jwts.builder()
				  .setSubject(user.getUsername())
				  .signWith(SignatureAlgorithm.HS512, "training@jilit")
				  .compact();
		return token;
	}
	
	
	
}
