package org.neric.regents.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.neric.regents.model.District;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
import org.neric.regents.test.UserPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;



@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	DistrictService districtService;
	
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {

		model.addAttribute("loggedinuser", getPrincipal());
		return "home";
	}

	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() 
	{
		if (isCurrentAuthenticationAnonymous()) 
		{
			return "login";
	    } 
		else 
		{
	    	return "redirect:/";  
	    }
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}
	
	
	
	@RequestMapping(value = { "/profile/{username}" }, method = RequestMethod.GET)
	public String showUser(@PathVariable String username, ModelMap model) {

		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "profile";
	}
	
	/* PASSWORD RESET */
	@RequestMapping(value = { "/changePassword" }, method = RequestMethod.GET)
	public String editUserPassword(ModelMap model) 
	{
		User user = userService.findByUsername(getPrincipal());
		UserPassword userPassword = new UserPassword(user.getId());
		model.addAttribute("userPassword", userPassword);
		model.addAttribute("loggedinuser", getPrincipal());
		return "changePassword";
	}
	
	@RequestMapping(value = { "/changePassword" }, method = RequestMethod.POST)
	public String updateUserPassword(@Valid UserPassword userPassword, BindingResult result, ModelMap model) 
	{
		System.err.println("!!!!!!!!!");
		
		if (result.hasErrors()) 
		{
			System.err.println("I HAVE ERRRORS...AHHHHHH!!!!!!!!!!");
			for(ObjectError error : result.getAllErrors())
			{
				System.err.println(error.getObjectName() + " | " + error.getCode() + " | " + error.getDefaultMessage());
			}
			return "changePassword";
		}
		
		userService.updatePassword(userPassword);

		model.addAttribute("success", "User: " + getPrincipal() +  "'s password was updated successfully");
		model.addAttribute("returnLink", "/");
		model.addAttribute("returnLinkText", "Users");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}


	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() 
	{
		return userProfileService.findAll();
	}
	
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal()
	{
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}


}