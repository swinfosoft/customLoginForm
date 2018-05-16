package com.jilit.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	//method to render home page
	@RequestMapping(value="/adder",
			method=RequestMethod.GET)
	public String index()
	{
		SecurityContext context=
				SecurityContextHolder.getContext();
		String userName=
				context.getAuthentication()
						.getName();
		String role=context.getAuthentication()
				           .getAuthorities().toString();
		System.out.println("User "+userName
				+" is accessing this url having"
				+role+" as role.");
		return "index";
	}
	
	@RequestMapping(value="/login", 
			method=RequestMethod.GET)
	public String login()
	{
		return "login";
	}
	
	
	//method to process add request
		@RequestMapping(value="/adder",
				method=RequestMethod.POST)
		public ModelAndView add(
			@RequestParam("num1")	int x, 
			@RequestParam("num2") int y)
		{
			int z=x+y;
			ModelAndView mav=new ModelAndView();
			mav.setViewName("result");
			mav.addObject("sum",z);
			return mav;
		}
	
}
