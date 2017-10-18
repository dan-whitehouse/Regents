package org.neric.regents.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.neric.regents.controller.AbstractController;
import org.neric.regents.converture.DistrictEditor;
import org.neric.regents.model.*;
import org.neric.regents.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class DashboardController extends AbstractController {

	@Autowired
	UserService userService;

	@Autowired
	DistrictService districtService;

	@Autowired
	SchoolService schoolService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderFormService orderFormService;

	@Autowired
	OptOutService optOutService;

	@Autowired
	DistrictEditor districtEditor;

	@ModelAttribute("districtsByUser")
	public List<District> populateDistrictsByUser()
	{
		Set<UserDistrict> userDistricts = loggedInUser().getUserDistricts();
		List<District> districts = new ArrayList<>();
		for(UserDistrict ud : userDistricts)
		{
			districts.add(ud.getDistrict());
		}
		return districts;
	}

	@RequestMapping(value = { "/dev/dashboard" }, method = RequestMethod.GET)
	public String listOptOutsByUser(ModelMap model) 
	{

		int userCount = userService.count();
		int districtCount = districtService.count();
		int schoolCount = schoolService.count();
		int orderCount = orderService.count();
		int activeOrdersCount, activeOrdersUniqueDistrictCount, activeNotAdministering, activeNeither;

		OrderForm of = orderFormService.getActiveOrderForm();
		if(of != null)
		{
			activeOrdersCount = orderService.countByActiveOrderForm(of.getId());
			activeOrdersUniqueDistrictCount = orderService.countByActiveOrderFormUniqueDistrict(of.getId());
			activeNotAdministering = optOutService.countByActiveOrderForm(of.getId());
			activeNeither = (districtCount - activeOrdersUniqueDistrictCount - activeNotAdministering);

			List<Order> orders = orderService.findAllOrdersByActiveOrderForm(of.getId());
			Map<Date, Long> counted = orders.stream().limit(5).collect(Collectors.groupingBy(Order::getOrderDate, Collectors.counting()));
			model.addAttribute("orders", counted);
		}
		else
		{
			activeOrdersCount = 0;
			activeOrdersUniqueDistrictCount = 0;
			activeNotAdministering = 0;
			activeNeither = (districtCount - activeOrdersUniqueDistrictCount - activeNotAdministering);
		}

		model.addAttribute("userCount", userCount);
		model.addAttribute("districtCount", districtCount);
		model.addAttribute("schoolCount", schoolCount);
		model.addAttribute("orderCount", orderCount);

		model.addAttribute("activeOrderCount", activeOrdersCount);
		model.addAttribute("activeOrdersUniqueDistrictCount", activeOrdersUniqueDistrictCount);
		model.addAttribute("activeNotAdministering", activeNotAdministering);
		model.addAttribute("activeUndecided", activeNeither);

		model.addAttribute("activeOrderCountPercent", getPercentage(districtCount, activeOrdersUniqueDistrictCount));
		model.addAttribute("activeNotAdministeringPercent", getPercentage(districtCount, activeNotAdministering));
		model.addAttribute("activeUndecidedPercent", getPercentage(districtCount, activeNeither));

		return "dashboard";
	}

	@RequestMapping(value = { "/dev/dashboard/report/aop/undecided" }, method = RequestMethod.GET)
	public String listUndecided(ModelMap model)
	{
		OrderForm of = orderFormService.getActiveOrderForm();
		List<District> districts = districtService.findAllUndecidedDistrictsByActiveOrderForm(of.getUuid());
		model.addAttribute("districts", districts);
		return "districts";
	}

	@RequestMapping(value = { "/dev/dashboard/report/aop/ordered" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model)
	{
		OrderForm of = orderFormService.getActiveOrderForm();
		List<District> districts = districtService.findAllOrderedDistrictsByActiveOrderForm(of.getUuid());
		model.addAttribute("districts", districts);
		return "districts";
	}

	@RequestMapping(value = { "/dev/dashboard/report/aop/na" }, method = RequestMethod.GET)
	public String listNotAdministering(ModelMap model)
	{
		OrderForm of = orderFormService.getActiveOrderForm();
		List<District> districts = districtService.findAllNADistrictsByActiveOrderForm(of.getUuid());
		model.addAttribute("districts", districts);
		return "districts";
	}

	private String getPercentage(int total, int found)
	{
		float percent = (found * 100.0f) / total;
		return String.format("%.02f", percent) + "%";
	}



}