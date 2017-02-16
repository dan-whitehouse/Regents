package org.neric.regents.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.model.Wizard;
import org.neric.regents.model.XExamWrapper;
import org.neric.regents.model.XForm2;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderService;
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
	UserService userService;

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

		Set<ConstraintViolation<Wizard>> violations = validator.validate(wizard);

		for (ConstraintViolation<Wizard> violation : violations)
		{
			String propertyPath = violation.getPropertyPath().toString();
			String message = violation.getMessage();

			// Add JSR-303 errors to BindingResult
			// This allows Spring to display them in view via a FieldError
			result.addError(new FieldError("formWizard", propertyPath, "Invalid " + propertyPath + "(" + message + ")"));
		}

		if (result.hasErrors())
		{
			return "xorder2Errors";
		}
		// Store the employee information in database
		// manager.createNewRecord(employeeVO);

		System.out.println(wizard);
		System.out.println(wizard.getSelectedOptionPrint().getName());
		System.out.println(wizard.getSelectedOptionScan().getName());

		for (ExamWrapper e : wizard.getSelectedExams())
		{
			if (e.isSelected())
			{
				System.out.println(e.getOrderExam().getExam().getId() + " - " + e.getOrderExam().getExamAmount());
			}
		}
		
		for(DocumentWrapper d : wizard.getSelectedDocuments())
		{
			if(d.isSelected())
			{
				System.out.println(d.getOrderDocument().getDocument().getId() + " - " + d.getOrderDocument().getDocumentAmount());
			}
		}

		// Mark Session Complete
		status.setComplete();
		return "redirect:poop";
	}
	
	@RequestMapping(value = "/poop", method = RequestMethod.GET)
	public String success(Model model)
	{
		return "poop";
	}
	
	 @RequestMapping(value = { "order-{uuid}" }, method = RequestMethod.GET)
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

	// @Autowired
	// ExamService examService;
	//
	//
	// @Autowired
	// OptionScanService optionScanService;
	//
	// @Autowired
	// OptionPrintService optionPrintService;
	//
	// @Autowired
	// DistrictService districtService;
//	
	// //model populated by the method below
	// //moved as we also need it populated on POST
	// @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	// public String order()
	// {
	// return "order";
	// }
	//
	// //handles for submit
	// //model atribute is automatically populated by the framework
	// @RequestMapping(value = { "/order" }, method = RequestMethod.POST)
	// public String order(@ModelAttribute("wizard") Wizard orderForm)
	// {
	//
	// for(ExamWrapper s : orderForm.getAllAvailableExams() )
	// {
	// if(s.isSelected())
	// {
	// System.err.println(s.getOrderExam().getExam().getName());
	// System.err.println(s.getOrderExam().getExamAmount());
	// }
	//
	// if(s.isPasSelected())
	// {
	// System.err.println(s.getOrderExam().getPearsonAnswerSheet());
	// }
	// }
	//
	// for(DocumentWrapper d : orderForm.getAllAvailableDocuments())
	// {
	// if(d.isSelected())
	// {
	// System.err.println(d.getOrderDocument().getDocument().getName());
	// System.err.println(d.getOrderDocument().getDocumentAmount());
	// }
	// }
	//
	// return "nextView";
	// }
	//
	// //on get populates the initial model for display
	// //on post create an instance which the form params will be bound to
	// @ModelAttribute("wizard")
	// public Wizard getOrderForm()
	// {
	// Wizard orderForm = new Wizard();
	// List<Exam> exams = examService.findAllExams();
	// List<Document> documents = documentService.findAllDocuments();
	// List<OptionScan> optionScans = optionScanService.findAllOptionScans();
	// List<OptionPrint> optionPrints =
	// optionPrintService.findAllOptionPrints();
	//
	// for(Exam exam : exams)
	// {
	// orderForm.getAllAvailableExams().add(new ExamWrapper(new
	// OrderExam(exam)));
	// }
	//
	// for(Document document : documents)
	// {
	// orderForm.getAllAvailableDocuments().add(new DocumentWrapper(new
	// OrderDocument(document)));
	// }
	//
	// return orderForm;
	// }
	//
	// @RequestMapping(value = { "order" }, method = RequestMethod.GET)
	// public String newOrder(String username, ModelMap model) {
	//
	// Order order = new Order();
	// List<Exam> exams = examService.findAllExams();
	// List<Document> documents = documentService.findAllDocuments();
	// List<OptionScan> optionScans = optionScanService.findAllOptionScans();
	// List<OptionPrint> optionPrints =
	// optionPrintService.findAllOptionPrints();
	// List<District> districts = districtService.findAllDistricts();
	//
	// model.addAttribute("order", order);
	// model.addAttribute("exams", exams); // exams (left) references (${exams}
	// on jsp page)
	// model.addAttribute("documents", documents);
	// model.addAttribute("optionScans", optionScans);
	// model.addAttribute("optionPrints", optionPrints);
	// model.addAttribute("loggedindistrict", getLeaCurrentUser());
	// model.addAttribute("loggedinuser", getPrincipal());
	// return "order"; // jsp page reference
	// }
	//
	// @RequestMapping(value = { "order" }, method = RequestMethod.POST)
	// public String saveOrder(@Valid Order order, BindingResult result,
	// ModelMap model)
	// {
	// if (result.hasErrors())
	// {
	// return "order";
	// }
	//
	// orderService.saveOrder(order);
	// model.addAttribute("success", "Order: " + order.getUuid() + " was
	// submitted successfully!");
	// model.addAttribute("loggedinuser", getPrincipal());
	//
	// return "orderSuccess"; // jsp page reference
	// }
	//
	//
	//
	// private String getLeaCurrentUser()
	// {
	// String lea = null;
	// String username = getPrincipal();
	// List<User> users = userService.findAllUsers();
	//
	// for(User u : users)
	// {
	// if(StringUtils.equalsIgnoreCase(username, u.getUsername()))
	// {
	// lea = u.getDistrict();
	// }
	// }
	//
	// return lea;
	// }

}