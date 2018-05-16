package com.jilit.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class MyAuthenticationFilter 
     extends OncePerRequestFilter
{

	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain)
			throws ServletException, IOException {
		
		//Header is obtained from the request
		
		String header=request.getHeader("Authorization");
		if(header !=null)
		{
		String auth=header.substring(0,6);
		if(auth.equals("Bearer"))
		{
		header=header.substring(7);
		//parse the token
		 
		try
		{
		  Jws<Claims> claims=	Jwts.parser().setSigningKey("training@jilit").parseClaimsJws(header);
		  //use these claims to create and set Authentication object
		  
		  UserDetails details=
					org.springframework.security.core.userdetails
					.User.withUsername("Ashok")
					.password("abcd")
					.roles("USER","ADMIN").build();
					//An Authentication object is created
					UsernamePasswordAuthenticationToken token
					=new UsernamePasswordAuthenticationToken(details.getUsername(),
							null,details.getAuthorities());
					
					SecurityContext context=
							SecurityContextHolder.getContext();
					context.setAuthentication(token);
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		System.out.println("following header is received: "+header);
		
		}
		else
			System.out.println("Invalid token");
		}
		/*System.out.println(
		"MyFilter is invoked for the request "+
		request.getRequestURI());*/
		
		chain.doFilter(request, response);
		
	}

	
	
}
