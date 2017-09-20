package org.neric.regents.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.converture.DistrictEditor;
import org.neric.regents.converture.OptionPrintEditor;
import org.neric.regents.converture.OptionScanEditor;
import org.neric.regents.converture.SchoolEditor;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderContact;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.School;
import org.neric.regents.model.User;
import org.neric.regents.model.UserDistrict;
import org.neric.regents.model.UserProfile;
import org.neric.regents.model.Wizard;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderFormService;
import org.neric.regents.service.OrderService;
import org.neric.regents.service.SchoolService;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
import org.neric.regents.test.DateUtils;
import org.neric.regents.wizard.DocumentWrapper;
import org.neric.regents.wizard.ExamWrapper;
import org.neric.regents.wizard.XDocumentWrapper;
import org.neric.regents.wizard.XExamWrapper;
import org.neric.regents.wizard.XForm2;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OrderController
{
	private Validator validator;

	public OrderController()
	{
		ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Autowired
	UserService userService;

	@Autowired
	SchoolService schoolService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderFormService orderFormService;

	@Autowired
	ExamService examService;

	@Autowired
	DocumentService documentService;

	@Autowired
	OptionPrintService optionPrintService;

	@Autowired
	OptionScanService optionScanService;

	@Autowired
	OptionPrintEditor optionPrintEditor;

	@Autowired
	OptionScanEditor optionScanEditor;
	
	@Autowired
	DistrictEditor districtEditor;
	
	@Autowired
	SchoolEditor schoolEditor;

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(OptionPrint.class, optionPrintEditor);
		binder.registerCustomEditor(OptionScan.class, optionScanEditor);
		binder.registerCustomEditor(District.class, districtEditor);
        binder.registerCustomEditor(School.class, schoolEditor);
	}

	@ModelAttribute("allExamOptions")
	public List<XExamWrapper> populateExamOptions()
	{
		List<XExamWrapper> options = new ArrayList<>();

		for (Exam e : examService.findAllActiveExams())
		{
			options.add(new XExamWrapper(new OrderExam(e)));
		}
		return options;
	}

	@ModelAttribute("allDocumentOptions")
	public List<XDocumentWrapper> populateDocumentOptions()
	{
		List<XDocumentWrapper> options = new ArrayList<>();

		for (Document e : documentService.findAllActiveDocuments())
		{
			options.add(new XDocumentWrapper(new OrderDocument(e)));
		}
		return options;
	}

	@ModelAttribute("allPrintOptions")
	public List<OptionPrint> populatePrintOptions()
	{
		List<OptionPrint> options = optionPrintService.findAllActiveOptionPrints();
		return options;
	}

	@ModelAttribute("allScanOptions")
	public List<OptionScan> populateScanOptions()
	{
		List<OptionScan> options = optionScanService.findAllActivelOptionScans();
		return options;
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
	
	
	@ModelAttribute("schoolsByDistrict")
	public List<School> populateSchoolsByDistrict()
	{
		
		Set<UserDistrict> userDistricts = loggedInUser().getUserDistricts();
		List<School> schools = new ArrayList<>();
		
		for(UserDistrict ud : userDistricts)
		{
			schools.addAll(schoolService.findAllByDistrictId(ud.getDistrict().getId()));
		}
		return schools;
	}

	@RequestMapping(value = { "/admin/orders" }, method = RequestMethod.GET)
	public String listAllOrders(ModelMap model)
	{
		List<Order> orders = orderService.findAllOrders();
		model.addAttribute("orders", orders);
		model.addAttribute("loggedinuser", getPrincipal());
		return "ordersAdmin";
	}
	
	@RequestMapping(value = { "/orders" }, method = RequestMethod.GET)
	public String listOrdersByUser(ModelMap model)
	{
		List<Order> orders = orderService.findAllOrdersByUsername(loggedInUserName());
		model.addAttribute("orders", orders);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orders";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String setupForm(Model model)
	{
		if(orderFormService.hasActiveOrderForm())
		{
			OrderForm orderForm = orderFormService.getActiveOrderForm();
			if(orderForm.getVisible())
			{
				XForm2 xForm = new XForm2();
				model.addAttribute("xForm2", xForm);
				model.addAttribute("orderForm", orderForm);
				return "order";
			}
			else
			{
				return "403";
			}	
		}
		else
		{
			return "204";
		}
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("xForm2") XForm2 xForm, BindingResult result, SessionStatus status)
	{
		Set<ConstraintViolation<XForm2>> violations = validator.validate(xForm);

		for (ConstraintViolation<XForm2> violation : violations)
		{
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();

			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			result.addError(new FieldError("xForm2", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors())
		{
			for(ObjectError x : result.getAllErrors())
			{
				System.out.println(x.getCode() + " | " + x.getObjectName() + " | " + x.getDefaultMessage());
			}
			return "error";
		}

		try
		{
			Order order = new Order();
			order.setOrderDate(DateUtils.asDate(LocalDateTime.now()));
			order.setOrderPrint(xForm.getSelectedOptionPrint());
			order.setOrderScan(xForm.getSelectedOptionScan());
			order.setReportToLevelOne(xForm.isReportingOption());
			order.setOrderStatus("Processing");
			order.setUuid(UUID.randomUUID().toString());
			order.setUser(loggedInUser());
			order.setDistrict(xForm.getDistrict());
			order.setSchool(xForm.getSchool());
			order.setSpecialRequests(xForm.getSpecialRequests());
			
			for (XExamWrapper ew : xForm.getSelectedExams())
			{
				if (ew.isSelected())
				{
					OrderExam oe = ew.getOrderExam();
					oe.setOrder(order);
					order.getOrderExams().add(oe);
				}
			}

			for (XDocumentWrapper dw : xForm.getSelectedDocuments())
			{
				if (dw.isSelected())
				{
					OrderDocument od = dw.getOrderDocument();
					od.setOrder(order);
					order.getOrderDocuments().add(od);
				}
			}
			
			OrderContact oc = xForm.getOrderContact();
			oc.setOrder(order);
			order.setOrderContact(oc);
			
			OrderForm orderForm = orderFormService.getActiveOrderForm();
			order.setOrderForm(orderForm);
			
			orderService.saveOrder(order);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Mark Session Complete
		status.setComplete();
		return "redirect:orderSuccess";
	}

	@RequestMapping(value = "/orderSuccess", method = RequestMethod.GET)
	public String success(Model model)
	{
		return "orderSuccess";
	}

	@RequestMapping(value = { "order/{uuid}" }, method = RequestMethod.GET)
	public String order(@PathVariable String uuid, ModelMap model)
	{
		Order order = orderService.findByUUID(uuid);
		if(order.getUser().getUsername().equalsIgnoreCase(getPrincipal()) || isAdmin(getPrincipal())) //IDK IF THIS IS THE BEST THING TO DO
		{
			model.addAttribute("order", order);
			return "invoice";
		}
		else
		{
			return "403";
		}
	}

	@RequestMapping(value = { "order/{uuid}/edit" }, method = RequestMethod.GET)
	public String editOrder(@PathVariable String uuid, ModelMap model)
	{
		Order order = orderService.findByUUID(uuid);
		
		if(order.getUser().getUsername().equalsIgnoreCase(getPrincipal()) || isAdmin(getPrincipal())) //IDK IF THIS IS THE BEST THING TO DO
		{
			XForm2 xForm = new XForm2();		
			xForm.setSelectedDocuments(populateDocumentOptions());
			xForm.setSelectedExams(populateExamOptions());
					
			for(XExamWrapper ew : xForm.getSelectedExams())
			{
				for(OrderExam e : order.getOrderExams())
				{
					if(e.getExam().getId() == ew.getOrderExam().getExam().getId())
					{
						ew.setSelected(true);
						ew.setOrderExam(e);
					}
				}
			}
				
			for(XDocumentWrapper dw : xForm.getSelectedDocuments())
			{
				for(OrderDocument d : order.getOrderDocuments())
				{
					if(d.getDocument().getId() == dw.getOrderDocument().getDocument().getId())
					{
						dw.setSelected(true);
						dw.setOrderDocument(d);
					}
				}
			}
	
			xForm.setSelectedOptionScan(order.getOrderScan());
			xForm.setReportingOption(order.getReportToLevelOne());
			xForm.setSelectedOptionPrint(order.getOrderPrint());
			xForm.setOrderContact(order.getOrderContact());
			xForm.setOrderForm(order.getOrderForm());
			xForm.setDistrict(order.getDistrict());
			xForm.setSchool(order.getSchool());
			xForm.setSpecialRequests(order.getSpecialRequests());
				
			model.addAttribute("xForm2", xForm);
			model.addAttribute("order", order);
			model.addAttribute("edit", true);
			
			return "createOrEditOrder";
		}
		else
		{
			return "403";
		}
	}

	@RequestMapping(value = { "order/{uuid}/edit" }, method = RequestMethod.POST)
	public String editExam(@ModelAttribute("xForm2") XForm2 xForm, BindingResult result, SessionStatus status, @PathVariable String uuid, ModelMap model)
	{
		if (result.hasErrors())
		{
			return "createOrEditOrder";
		}

		try
		{
			Order order = orderService.findByUUID(uuid);		
			order.setOrderPrint(xForm.getSelectedOptionPrint());
			order.setOrderScan(xForm.getSelectedOptionScan());
			order.setReportToLevelOne(xForm.isReportingOption());
			order.setOrderStatus("Processing");
			order.setDistrict(xForm.getDistrict());
			order.setSchool(xForm.getSchool());
			order.setSpecialRequests(xForm.getSpecialRequests());
			
			order.getOrderExams().clear();
			order.getOrderDocuments().clear();

			for (XExamWrapper ew : xForm.getSelectedExams())
			{
				if (ew.isSelected())
				{
					OrderExam oe = ew.getOrderExam();		
					oe.setOrder(order);
					order.getOrderExams().add(oe);
				}
			}

			for (XDocumentWrapper dw : xForm.getSelectedDocuments())
			{
				if (dw.isSelected())
				{
					OrderDocument od = dw.getOrderDocument();
					od.setOrder(order);
					order.getOrderDocuments().add(od);
				}
			}

			//Update OrderContact data associated to Order
			OrderContact oc = xForm.getOrderContact();
			order.getOrderContact().setName(oc.getName());
			order.getOrderContact().setTitle(oc.getTitle());
			order.getOrderContact().setEmail(oc.getEmail());
			order.getOrderContact().setAltContactInfo(oc.getAltContactInfo());
			order.getOrderContact().setPhone(oc.getPhone());
			order.getOrderContact().setOrder(order);
			

			//Attach OrderForm to Order
			OrderForm of = orderFormService.getActiveOrderForm();
			order.setOrderForm(of);
			
			orderService.updateOrder(order);
			model.addAttribute("success", "Order: " + order.getUuid() + " - " + " was updated successfully");
			model.addAttribute("returnLink", "/orders");
			model.addAttribute("returnLinkText", "Orders");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return "success";
	}

	@RequestMapping(value = { "order/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteExam(@PathVariable String uuid, ModelMap model)
	{
		orderService.deleteOrder(uuid);
		
		return "redirect:/orders";
	}
	
	@RequestMapping(value = { "order/{uuid}/complete/{isComplete}" }, method = RequestMethod.GET)
	public String updateOrderStatus(@PathVariable String uuid, @PathVariable boolean isComplete) 
	{
		orderService.updateStatus(uuid, isComplete);
		return "redirect:/orders";
	}
	

	private String getPrincipal()
	{
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
		{
			userName = ((UserDetails) principal).getUsername();
		}
		else
		{
			userName = principal.toString();
		}
		return userName;
	}
	
	public List<UserProfile> initializeProfiles() 
	{
		return userProfileService.findAll();
	}
	
	private boolean isAdmin(String username)
	{
		User user = userService.findByUsername(username);
		for(UserProfile up : user.getUserProfiles())
		{
			if("Admin".equalsIgnoreCase(up.getType()))
			{
				return true;
			}
		}
		return false;
	}
	
	
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
}