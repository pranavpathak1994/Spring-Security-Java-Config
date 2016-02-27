package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.security.entity.Users;
import com.security.service.UserService;

/**
 * UserController
 * 
 * @author Pranav
 *
 */

@Controller
public class UserController {
	
	
	@Autowired 
	private UserService userService;
	

	/**
	 * Load login page
	 * @return
	 */
	
	@RequestMapping(value="/")
	public String getLoginPage(){
		
		return "Login";
	}
	
	
	/**
	 * Load Access Denied Page
	 * @return
	 */
	
	
	@RequestMapping(value="/403")
	public String getAccessDeniedPage(){
		
		return "403";
	}
	
	
	/**
	 * Load Registration Page
	 * @return
	 */
	
	@RequestMapping(value="/registration.html")
	public String getRegistrationPage(Model model){
		
		model.addAttribute("register", new Users());
		return "Registration";
	}
	
	/**
	 * Load Welcome Page
	 * @return
	 */
	
	@RequestMapping(value="/home/welcome.html")
	public String getWelcomePage(Model model){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user=(User) auth.getPrincipal();
		
		Users users= userService.getByEmail(user.getUsername());
		
		model.addAttribute("firstName", users.getFirstName());
		model.addAttribute("lastName", users.getLastName());
		model.addAttribute("authority", user.getAuthorities());
		model.addAttribute("email", user.getUsername());
		return "Welcome";
	}
	
	/**
	 * Save User
	 * @param user
	 * @return
	 */
	
	@RequestMapping(value="/register.do",method=RequestMethod.POST)
	public String register(@ModelAttribute(value="register") Users user){
		System.out.println(user.toString());
		userService.saveUser(user);
		
		return "redirect:/";
	}
	
	
}

