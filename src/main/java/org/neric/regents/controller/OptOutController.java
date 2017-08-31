package org.neric.regents.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.neric.regents.model.Setting;
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
import org.neric.regents.service.SettingService;
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
public class OptOutController {	
	@Autowired
	DistrictService districtService;
	
	@Autowired
	OrderFormService orderFormService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OptOutService optOutService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
        binder.registerCustomEditor(Set.class, "userDistricts", new CustomCollectionEditor(Set.class)
        {
	          @Override
	          protected Object convertElement(Object element)
	          {
	              Integer id = null;
	
	              if(element instanceof String && !((String)element).equals("")){
	                  try{
	                      id = Integer.parseInt((String) element);
	                  }
	                  catch (NumberFormatException e) {
	                      e.printStackTrace();
	                  }
	              }
	              else if(element instanceof Integer) {
	                  id = (Integer) element;
	              }

	              return id != null ? districtService.findById(id) : null;
	          }
        });
	}
    

	@ModelAttribute("loggedinuser")
    public User loggedInUser() 
    {
		User user = userService.findByUsername(getPrincipal());
        return user;
    }
	
	@ModelAttribute("loggedinusername")
    public String loggedInUserName() 
    {
        return getPrincipal();
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
	@RequestMapping(value = { "/optouts" }, method = RequestMethod.GET)
	public String listAllOptOuts(ModelMap model) 
	{
		List<OptOut> optOuts = optOutService.findAllOptOuts();
		model.addAttribute("optouts", optOuts);
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "optOuts";
	}
	
	@RequestMapping(value = { "/optout" }, method = RequestMethod.POST)
	public String createOrderForm(@Valid OptOut optOut, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "optout";
		}		
		
		optOut.setUuid(UUID.randomUUID().toString());
		optOut.setDistrict(optOut.getDistrict());
		optOut.setOptOutUser(optOut.getOptOutUser());
		optOut.setOptOutDate(new Date());
				
		optOutService.save(optOut);
		
		model.addAttribute("success", "OptOut: " + optOut.getDistrict().getName() + " has opted out successfully");
//		model.addAttribute("returnLink", "/admin/optout");
//		model.addAttribute("returnLinkText", "Order Forms");
		return "success";
	}
	
	@RequestMapping(value = { "optout/{uuid}" }, method = RequestMethod.GET)
	public String optOut(@PathVariable String uuid, ModelMap model)
	{
		OptOut optOut = optOutService.findByUUID(uuid);
		model.addAttribute("optOut", optOut);
		// create page called optoutresponse
		return "optoutresponse";
	}
	
	@RequestMapping(value = { "optout/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteOptOut(@PathVariable String uuid, ModelMap model)
	{
		optOutService.deleteByUUID(uuid);
		
		// redirect to some sweet spot called outputs
		return "redirect:/optouts";
	}
	
	
	
	/************************** OTHER **************************/
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() 
	{
		return userProfileService.findAll();
	}
	
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
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