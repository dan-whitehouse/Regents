package org.neric.regents.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.neric.regents.converture.DistrictEditor;
import org.neric.regents.model.*;
import org.neric.regents.service.OptOutService;
import org.neric.regents.service.OrderFormService;
import org.neric.regents.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OptOutController extends AbstractController {

	@Autowired
	OrderFormService orderFormService;

	@Autowired
	OptOutService optOutService;

	@Autowired
	DistrictEditor districtEditor;
	
	@Autowired
	OrderService orderService;
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(District.class, districtEditor);
      
	}

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
	
	/************************** ORDER OPT OUT **************************/
	@RequestMapping(value = { "/notadministrations" }, method = RequestMethod.GET)
	public String listOptOutsByUser(ModelMap model) 
	{
		List<OptOut> optOuts = optOutService.findAllOptOutsByUsername(loggedInUserName());
		model.addAttribute("optouts", optOuts);
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "optout/optouts";
	}
	
	@RequestMapping(value = { "/admin/notadministrations" }, method = RequestMethod.GET)
	public String listAllOptOuts(ModelMap model) 
	{
		List<OptOut> optOuts = optOutService.findAllOptOuts();
		model.addAttribute("optouts", optOuts);
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "optout/optouts";
	}
	
	
	@RequestMapping(value = { "/notadministration" }, method = RequestMethod.GET)
	public String createOptOutForm(Model model)
	{
		List<District> activeOptOutDistricts = new ArrayList<>();
		List<District> selectableDistricts = new ArrayList<>();
		List<District> activeOrderDistricts = new ArrayList<>();
		OrderForm orderForm = orderFormService.getActiveOrderForm();
		
		if(orderForm != null)
		{	
			if(orderForm.isExpiredPeriod())
			{
				model.addAttribute("error_message", "It appears the active Regents period has expired.");
				return "message/204";
			}
			else if(orderForm.isActivePeriod())
			{
				//Populated selectable districts with districts associated to the user.
				selectableDistricts.addAll(populateDistrictsByUser());
				
				//Add all districts currently opted out.
				List<OptOut> optOuts = optOutService.findAllActiveOptOuts(orderForm.getId());
				for(OptOut o : optOuts)
				{
					activeOptOutDistricts.add(o.getDistrict());
				}
				
				//If selectableDistricts contains an activeOptOutDistrict we need to remove it from the selectableDistricts list.
				for(District d : activeOptOutDistricts)
				{
					for (Iterator<District> iterator = selectableDistricts.iterator(); iterator.hasNext();) {
					    District district = iterator.next();
					    if (district.getUuid().equalsIgnoreCase(d.getUuid())) {
					        iterator.remove();
					    }
					}
				}
				
				//Add all districts currently ordered.
				List<Order> orders = orderService.findAllOrdersByActiveOrderForm(orderForm.getId());
				for(Order o : orders)
				{
					activeOrderDistricts.add(o.getDistrict());
				}
				
				for(District d : activeOrderDistricts)
				{
					for (Iterator<District> iterator = selectableDistricts.iterator(); iterator.hasNext();) {
					    District district = iterator.next();
					    if (district.getUuid().equalsIgnoreCase(d.getUuid())) {
					        iterator.remove();
					    }
					}
				}
				
				//Sort SelectableDistricts by Name
				Collections.sort(selectableDistricts, new Comparator<District>() {
				    public int compare(District one, District other) {
				        return one.getName().compareTo(other.getName());
				    }
				});
				
				
				if(CollectionUtils.isNotEmpty(selectableDistricts))
				{
					OptOut optOut = new OptOut();
					model.addAttribute("optout", optOut);
					model.addAttribute("selectableDistricts", selectableDistricts);
					return "optout/optout";
				}
				else if(wasOptedOutByOtherUser(optOuts))
				{
					model.addAttribute("error_message", "It appears another user may have already marked all of the districts associated with this account as 'Not Administering'.");
					return "message/204";
				}
				else
				{
					model.addAttribute("error_message", "It appears you are not administering or have already submitted an order this Regents period.");
					return "message/204";
				}
			}
			else
			{
				model.addAttribute("error_message", "");
				return "message/403"; //Not expired and not active... must be something else...
			}
		}
		else
		{
			model.addAttribute("error_message", "No active Regents period.");
			return "message/204"; //No Active OrderForm, may be null
		}
	}
	
	@RequestMapping(value = { "/notadministration" }, method = RequestMethod.POST)
	public String createOptOutForm(@Valid OptOut optOut, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "optout/optout";
		}	
		
		optOut.setUuid(UUID.randomUUID().toString());
		optOut.setOrderForm(orderFormService.getActiveOrderForm());
		optOut.setOptOutUser(loggedInUser());
		optOut.setOptOutDate(new Date());
		
		System.out.println(optOut.toString());	
		optOutService.save(optOut);
		
		model.addAttribute("success", "Non-Administration: " + optOut.getDistrict().getName() + " has been successfully submitted.");
		model.addAttribute("returnLink", "/");
		model.addAttribute("returnLinkText", "Not Administering");
		return "message/success";
	}
	
	@RequestMapping(value = { "notadministration/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteOptOut(@PathVariable String uuid, ModelMap model)
	{
		optOutService.deleteByUUID(uuid);
		
		// redirect to some sweet spot called outputs
		return "redirect:/notadministrations";
	}
	
	
	/************************** OTHER **************************/

	
	private boolean wasOptedOutByOtherUser(List<OptOut> optOuts)
	{
		boolean isOptedOut = false;
		for(OptOut o : optOuts)
		{
			//If current user does not equal the username of the person who opted out
			if(!getPrincipal().equalsIgnoreCase(o.getOptOutUser().getUsername()))
			{
				return true;
			}
		}
		return isOptedOut;
	}
}