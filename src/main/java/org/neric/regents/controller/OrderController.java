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
import org.neric.regents.converture.OptionPrintEditor;
import org.neric.regents.converture.OptionScanEditor;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.DocumentWrapper;
import org.neric.regents.model.Exam;
import org.neric.regents.model.ExamWrapper;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.School;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.model.Wizard;
import org.neric.regents.model.XDocumentWrapper;
import org.neric.regents.model.XExamWrapper;
import org.neric.regents.model.XForm2;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderService;
import org.neric.regents.service.SchoolService;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
import org.neric.regents.test.DateUtils;
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
	UserProfileService userProfileService;
	
	@Autowired
	OrderService orderService;

	@Autowired
	MessageSource messageSource;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(OptionPrint.class, optionPrintEditor);
		binder.registerCustomEditor(OptionScan.class, optionScanEditor);
	}

	@ModelAttribute("allExamOptions")
	public List<ExamWrapper> populateExamOptions()
	{
		List<ExamWrapper> options = new ArrayList<>();

		for (Exam e : examService.findAllExams())
		{
			options.add(new ExamWrapper(new OrderExam(e)));
		}
		return options;
	}
	
	@ModelAttribute("allDocumentOptions")
	public List<DocumentWrapper> populateDocumentOptions()
	{
		List<DocumentWrapper> options = new ArrayList<>();
		
		for(Document d : documentService.findAllDocuments())
		{
			options.add(new DocumentWrapper(new OrderDocument(d)));
		}
		
		return options;
	}

	@ModelAttribute("allPrintOptions")
	public List<OptionPrint> populatePrintOptions()
	{
		List<OptionPrint> options = optionPrintService.findAllOptionPrints();
		return options;
	}

	@ModelAttribute("allScanOptions")
	public List<OptionScan> populateScanOptions()
	{
		List<OptionScan> options = optionScanService.findAllOptionScans();
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
	
	@ModelAttribute("schoolsByDistrict")
    public List<School> populateSchoolsByDistrict() 
    {
        List<School> schools = schoolService.findAllByDistrictId(loggedInUser().getDistrict().getId());
        return schools;
    }

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String setupForm(Model model)
	{
		Wizard wizard = new Wizard();
		model.addAttribute("formWizard", wizard);
		return "order";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("formWizard") Wizard wizard, BindingResult result, SessionStatus status)
	{
		System.out.println("AHHHHHHHHHHHHHH");
		Set<ConstraintViolation<Wizard>> violations = validator.validate(wizard);
		
		for (ConstraintViolation<Wizard> violation : violations)
		{
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();

			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			result.addError(new FieldError("formWizard", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
			System.out.println(wizard.toString());
			System.out.println(result);
		}

		if (result.hasErrors())
		{
			return "error";
		}
		// Store the employee information in database
		// manager.createNewRecord(employeeVO);

		System.out.println(wizard);
		 try
	        {
	        	Order order = new Order();
	            order.setOrderDate(DateUtils.asDate(LocalDateTime.now()));
	            order.setOrderPrint(wizard.getSelectedOptionPrint());
	            order.setOrderScan(wizard.getSelectedOptionScan());
	            order.setReportToLevelOne(wizard.isReportingOption());
	            order.setOrderStatus("Processing");
	            order.setUuid(UUID.randomUUID().toString());
	            order.setUser(loggedInUser());
	            
	            for(ExamWrapper ew : wizard.getSelectedExams())
	            {
	            	if(ew.isSelected())
	            	{
	            		OrderExam oe = ew.getOrderExam();
	            		oe.setOrder(order);	
	            		order.getOrderExams().add(oe);
	            	}
	            }
	            
	            for(DocumentWrapper dw : wizard.getSelectedDocuments())
	            {
	            	if(dw.isSelected())
	            	{
	            		OrderDocument od = dw.getOrderDocument();
	            		od.setOrder(order);
	            		order.getOrderDocuments().add(od);
	            	}
	            }
	            orderService.saveOrder(order);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }

		// Mark Session Complete
		status.setComplete();
		return "redirect:order2Success";
	}
	
	@RequestMapping(value = "/order2Success", method = RequestMethod.GET)
	public String success(Model model)
	{
		model.addAttribute("returnLink", "/orders");
		model.addAttribute("returnLinkText", "Orders");
		return "success";
	}
	
	 @RequestMapping(value = { "order/{uuid}" }, method = RequestMethod.GET)
	 public String order(@PathVariable String uuid, ModelMap model) {
	
		 Order order = orderService.findByUUID(uuid);
		 model.addAttribute("order", order);
		 return "invoice";
	 }
	 
	@RequestMapping(value = { "orders" }, method = RequestMethod.GET)
	public String listOrders(ModelMap model)
	{
		List<Order> orders = orderService.findAllOrders();
		model.addAttribute("orders", orders);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orders";
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
}