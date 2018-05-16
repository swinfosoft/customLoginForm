package com.jilit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jilit.daos.UserDao;
import com.jilit.entities.Role;
import com.jilit.entities.User;

@Service
public class CustomUserDetailsService 
implements UserDetailsService
{

	@Autowired
	UserDao dao;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		//loading the user
		List<User> list=dao.findByMailId(userName);
		if(list.isEmpty())
		{
			throw (new UsernameNotFoundException("No user found for the username "+userName));
			
		}
		else
		{
			User loaded=list.get(0);
			String roles[]=new String[loaded.getRoles().size()];
			int i=0;
			for(Role role: loaded.getRoles())
				roles[i++]=role.getRole();
			
			return org.springframework.security.core.userdetails.User.withUsername(loaded.getName())
			.password(loaded.getPassword())
			.disabled(!loaded.isActive())
			.authorities(roles)
			.build();
			
			
		}
		
	}
	
	
	
	
	
		
	
}
