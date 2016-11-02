package org.neric.regents.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.neric.regents.model.Order;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	MessageSource messageSource;

    	
	@RequestMapping(value = { "/order-{id}" }, method = RequestMethod.GET)
	public String order(@PathVariable int id, ModelMap model) {

		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		return "order";
	}
	
	@RequestMapping(value = { "/orders" }, method = RequestMethod.GET)
	public String listOrders(ModelMap model) {

		List<Order> orders = orderService.findAllOrders();
		model.addAttribute("orders", orders);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orders";
	}
	
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
	


}