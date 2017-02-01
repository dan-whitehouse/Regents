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
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderFormService;
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
public class AdminController {

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
	OrderFormService orderFormService;
	
	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = { "/orderForm-{uuid}" }, method = RequestMethod.GET)
	public String order(@PathVariable String uuid, ModelMap model) 
	{
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orderForm";
	}
	
	
	@RequestMapping(value = { "/createOrderForm" }, method = RequestMethod.GET)
	public String createOrderForm(ModelMap model) 
	{
		List<Exam> exams = examService.findAllExams();
		List<Document> documents = documentService.findAllDocuments();

		model.addAttribute("exams", exams);
		model.addAttribute("documents", documents);
		OrderForm orderForm = new OrderForm();
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditOrderForm";
	}
	
	@RequestMapping(value = { "/edit-orderForm-{uuid}" }, method = RequestMethod.GET)
	public String editOrderForm(@PathVariable String uuid, ModelMap model) 
	{
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditOrderForm";
	}

	
//	@RequestMapping(value = { "/order" }, method = RequestMethod.POST)
//	public String listExams(ModelMap model) {
//
//		List<Exam> exams = examService.findAllExams();
//		model.addAttribute("exams", exams); // exams (left) references (${exams} on jsp page)
//		model.addAttribute("loggedinuser", getPrincipal());
//		return "order"; // jsp page reference
//	}

	@RequestMapping(value = { "/orderForms" }, method = RequestMethod.GET)
	public String listOrderForms(ModelMap model) {

		List<OrderForm> orderForms = orderFormService.findAllOrderForms();
		model.addAttribute("orderForms", orderForms);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orderForms";
	}
	
	/************************** EXAMS **************************/
	@RequestMapping(value = { "/exams" }, method = RequestMethod.GET)
	public String listExams(ModelMap model) {

		List<Exam> exams = examService.findAllExams();
		model.addAttribute("exams", exams);
		model.addAttribute("loggedinuser", getPrincipal());
		return "exams";
	}
	
	
	/**
	 * This method will provide the medium to add a new Exam.
	 */
	@RequestMapping(value = { "/createExam" }, method = RequestMethod.GET)
	public String createExam(ModelMap model) 
	{
		Exam exam = new Exam();
		model.addAttribute("exam", exam);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditExam";
	}

	/**
	 * This method will be called on form submission, handling POST requests for
	 * saving Exams in database. It also validates the user input
	 */
	@RequestMapping(value = { "/createExam" }, method = RequestMethod.POST)
	public String createExam(@Valid Exam exam, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditExam";
		}
		
		examService.saveExam(exam);

		model.addAttribute("success", "Exam: " + exam.getName() + " was created successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "createOrEditExamSuccess";
	}
	
	@RequestMapping(value = { "/edit-exam-{id}" }, method = RequestMethod.GET)
	public String editExam(@PathVariable int id, ModelMap model) 
	{
		Exam exam = examService.findById(id);
		model.addAttribute("exam", exam);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditExam";
	}
	
	@RequestMapping(value = { "/edit-exam-{id}" }, method = RequestMethod.POST)
	public String editExam(@Valid Exam exam, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditExam";
		}

		examService.updateExam(exam);

		model.addAttribute("success", "Exam: " + exam.getName() + " - " + exam.getCode() +  " was updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditExamSuccess";
	}


	@RequestMapping(value = { "/delete-exam-{id}" }, method = RequestMethod.GET)
	public String deleteExam(@PathVariable int id) 
	{
		examService.deleteByExamId(id);
		return "redirect:/exams";
	}
	
	/************************** DOCUMENTS **************************/
	@RequestMapping(value = { "/documents" }, method = RequestMethod.GET)
	public String listDocuments(ModelMap model) {

		List<Document> documents = documentService.findAllDocuments();
		model.addAttribute("documents", documents);
		model.addAttribute("loggedinuser", getPrincipal());
		return "documents";
	}
		
	@RequestMapping(value = { "/createDocument" }, method = RequestMethod.GET)
	public String createDocument(ModelMap model) 
	{
		Document document = new Document();
		model.addAttribute("document", document);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditDocument";
	}

	/**
	 * This method will be called on form submission, handling POST requests for
	 * saving Exams in database. It also validates the user input
	 */
	@RequestMapping(value = { "/createDocument" }, method = RequestMethod.POST)
	public String createDocument(@Valid Document document, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDocument";
		}
		
		documentService.saveDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was created successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "createOrEditDocumentSuccess";
	}
	
	@RequestMapping(value = { "/edit-document-{id}" }, method = RequestMethod.GET)
	public String editDocument(@PathVariable int id, ModelMap model) 
	{
		Document document = documentService.findById(id);
		model.addAttribute("document", document);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditDocument";
	}
	
	@RequestMapping(value = { "/edit-document-{id}" }, method = RequestMethod.POST)
	public String updateDocument(@Valid Document document, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDocument";
		}

		documentService.updateDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditDocumentSuccess";
	}


	@RequestMapping(value = { "/delete-document-{id}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int id) 
	{
		documentService.deleteByDocumentId(id);
		return "redirect:/documents";
	}
	
	
	/************************** OTHER **************************/
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