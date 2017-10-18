package org.neric.regents.controller;

import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;


public abstract class AbstractController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles()
	{
		return userProfileService.findAll();
	}

	@ModelAttribute("loggedinuser")
	protected User loggedInUser()
	{
		return userService.findByUsername(getPrincipal());
	}

	@ModelAttribute("loggedinusername")
	protected String loggedInUserName()
	{
		return getPrincipal();
	}


	protected boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	protected String getPrincipal()
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
}