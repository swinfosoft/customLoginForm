package com.jilit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jilit.services.CustomUserDetailsService;

@EnableWebSecurity
public class CustomSecurityConfiguration 
		extends WebSecurityConfigurerAdapter 
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	//Method to created MyAuthenticationFilter
	@Bean
	public MyAuthenticationFilter myAuthenticationFilter()
	{
		return new MyAuthenticationFilter();
	}
	
	
	
	
	//In Memory Authentication
	/*@Override
	protected void configure(
			AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder=passwordEncoder();
		
		
		//enabling the InMemoryAuthenticationProvider
		//withUser neeraj & password 1234
		auth.inMemoryAuthentication()
	    .passwordEncoder(encoder)
		.withUser("neeraj@yahoo.com")
		.password(encoder.encode("1234"))
		.roles("USER");
			       
	}*/

	//JDBCAuthenticationProvider
	/*@Override
	protected void configure(
			AuthenticationManagerBuilder auth) throws Exception {
		
		PasswordEncoder encoder=passwordEncoder();
		//Jdbc Authentication	
				auth.jdbcAuthentication()
				       .dataSource(dataSource)
				       .passwordEncoder(encoder)
				       .usersByUsernameQuery("select mailId, password, isActive from UserMaster where mailId=?")
				       .authoritiesByUsernameQuery("select username, role from User_Roles where username=?");
	}	*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.cors().disable()
		    .csrf().disable()
		 	.addFilterBefore(
		 			myAuthenticationFilter(),
		 			UsernamePasswordAuthenticationFilter.class)
		 	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	//DaoAuthenticationProvider
	@Override
	protected void configure(
			AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		
			       
	}


	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			    .antMatchers("webjars/**")
			    	.permitAll()
			    .antMatchers("adder/**")
					.authenticated()
					.and()
			.formLogin()
				.loginPage("/login")
					.permitAll()
					.and()
			.logout().permitAll();		
	}*/
	

}
