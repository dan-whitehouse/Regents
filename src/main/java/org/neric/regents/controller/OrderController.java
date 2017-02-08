package org.neric.regents.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.ExamWrapper;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.model.Wizard;
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
	ExamService examService;
	
	@Autowired
	DocumentService documentService;
	
	@Autowired
	OptionScanService optionScanService;
	
	@Autowired
	OptionPrintService optionPrintService;
	
	@Autowired
	DistrictService districtService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "order-{uuid}" }, method = RequestMethod.GET)
	public String order(@PathVariable String uuid, ModelMap model) {

		Order order = orderService.findByUUID(uuid);
		model.addAttribute("order", order);
		return "invoice";
	}
	
	//model populated by the method below
    //moved as we also need it populated on POST
    @RequestMapping(value = { "/order" }, method = RequestMethod.GET)
    public String order() 
    {
        return "order"; 
    }
    
    //handles for submit
    //model atribute is automatically populated by the framework
    @RequestMapping(value = { "/order" }, method = RequestMethod.POST)
    public String order(@ModelAttribute("wizard") Wizard orderForm) 
    {

        for(ExamWrapper s : orderForm.getAllAvailableExams() )
        {
        	if(s.isSelected())
        	{
        		System.err.println(s.getOrderExam().getExam().getName());
        		System.err.println(s.getOrderExam().getExamAmount());
        	}
        	
        	if(s.isPasSelected())
        	{
        		System.err.println(s.getOrderExam().getPearsonAnswerSheet());
        	}
        }

        return "nextView"; 
    }

    //on get populates the initial model for display
    //on post create an instance which the form params will be bound to
    @ModelAttribute("wizard")
    public Wizard getOrderForm()
    {
    	Wizard orderForm = new Wizard();
        List<Exam> exams = examService.findAllExams();
        List<Document> documents = documentService.findAllDocuments();
        List<OptionScan> optionScans = optionScanService.findAllOptionScans();
        List<OptionPrint> optionPrints = optionPrintService.findAllOptionPrints();

        for(Exam exam : exams)
        {
            orderForm.getAllAvailableExams().add(new ExamWrapper(new OrderExam(exam)));
        }

        return orderForm;
    }
	
//	@RequestMapping(value = { "order" }, method = RequestMethod.GET)
//	public String newOrder(String username, ModelMap model) {
//
//		Order order = new Order();
//		List<Exam> exams = examService.findAllExams();
//		List<Document> documents = documentService.findAllDocuments();
//		List<OptionScan> optionScans = optionScanService.findAllOptionScans();
//		List<OptionPrint> optionPrints = optionPrintService.findAllOptionPrints();
//		List<District> districts = districtService.findAllDistricts();
//
//		model.addAttribute("order", order);
//		model.addAttribute("exams", exams); // exams (left) references (${exams} on jsp page)
//		model.addAttribute("documents", documents);
//		model.addAttribute("optionScans", optionScans);
//		model.addAttribute("optionPrints", optionPrints);
//		model.addAttribute("loggedindistrict", getLeaCurrentUser());
//		model.addAttribute("loggedinuser", getPrincipal());
//		return "order"; // jsp page reference
//	}
//	
//	@RequestMapping(value = { "order" }, method = RequestMethod.POST)
//	public String saveOrder(@Valid Order order, BindingResult result, ModelMap model) 
//	{
//		if (result.hasErrors()) 
//		{
//			return "order";
//		}
//		
//		orderService.saveOrder(order);
//		model.addAttribute("success", "Order: " + order.getUuid() + " was submitted successfully!");
//		model.addAttribute("loggedinuser", getPrincipal());
//		
//		return "orderSuccess"; // jsp page reference
//	}

	@RequestMapping(value = { "orders" }, method = RequestMethod.GET)
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
	
	private String getLeaCurrentUser()
	{
		String lea = null;
		String username = getPrincipal();
		List<User> users = userService.findAllUsers();
		
		for(User u : users)
		{
			if(StringUtils.equalsIgnoreCase(username, u.getUsername()))
			{
				lea = u.getDistrict();
			}
		}
		
		return lea;
	}

}