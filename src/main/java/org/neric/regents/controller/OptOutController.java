package org.neric.regents.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletContextAttributeListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.neric.regents.controller.AbstractController;
import org.neric.regents.converture.DistrictEditor;
import org.neric.regents.converture.OptionPrintEditor;
import org.neric.regents.converture.SchoolEditor;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.OptOut;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.OrderFormDocument;
import org.neric.regents.model.OrderFormExam;
import org.neric.regents.model.School;
import org.neric.regents.model.SelectedDistrict;
import org.neric.regents.model.SelectedDocument;
import org.neric.regents.model.SelectedExam;
import org.neric.regents.model.User;
import org.neric.regents.model.UserDistrict;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptOutService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderFormService;
import org.neric.regents.service.OrderService;
import org.neric.regents.service.SchoolService;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
import org.neric.regents.wizard.XForm2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
					model.addAttribute("error_message", "It appears you are not administering this Regents period.");
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
		model.addAttribute("returnLink", "/notadministration");
		model.addAttribute("returnLinkText", "Not Administering");
		return "message/success";
	}
	
	@RequestMapping(value = { "notadministration/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteOptOut(@PathVariable String uuid, ModelMap model)
	{
		optOutService.deleteByUUID(uuid);
		
		// redirect to some sweet spot called outputs
		return "redirect:/notadministration";
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