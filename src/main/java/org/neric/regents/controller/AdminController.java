package org.neric.regents.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.neric.regents.converture.DistrictEditor;
import org.neric.regents.converture.OptionPrintEditor;
import org.neric.regents.converture.SchoolEditor;
import org.neric.regents.model.District;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.Order;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.OrderFormDocument;
import org.neric.regents.model.OrderFormExam;
import org.neric.regents.model.School;
import org.neric.regents.model.SelectedDocument;
import org.neric.regents.model.Setting;
import org.neric.regents.model.SelectedExam;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderFormService;
import org.neric.regents.service.OrderService;
import org.neric.regents.service.SchoolService;
import org.neric.regents.service.SettingService;
import org.neric.regents.service.UserProfileService;
import org.neric.regents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
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
	SchoolService schoolService;
	
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
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	DistrictEditor districtEditor;
	
	@Autowired
	SchoolEditor schoolEditor;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
        binder.registerCustomEditor(District.class, districtEditor);
        binder.registerCustomEditor(School.class, schoolEditor);
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
        binder.registerCustomEditor(Set.class, "orderFormExams", new CustomCollectionEditor(Set.class)
        {
	          @Override
	          protected Object convertElement(Object element)
	          {
	              Integer id = null;
	
	              if(element instanceof String && !((String)element).equals("")){
	                  //From the JSP 'element' will be a String
	                  try{
	                      id = Integer.parseInt((String) element);
	                  }
	                  catch (NumberFormatException e) {
	                      System.out.println("Element was " + ((String) element));
	                      e.printStackTrace();
	                  }
	              }
	              else if(element instanceof Integer) {
	                  //From the database 'element' will be a Long
	                  id = (Integer) element;
	              }
	
	              return id != null ? examService.findById(id) : null;
	          }
        });
        
        binder.registerCustomEditor(Set.class, "orderFormDocuments", new CustomCollectionEditor(Set.class)
        {
	          @Override
	          protected Object convertElement(Object element)
	          { 
	              Integer id = null;
	              if(element instanceof String && !((String)element).equals(""))
	              {
	                  try
	                  {
	                      id = Integer.parseInt((String) element);
	                  }
	                  catch (NumberFormatException e) 
	                  {
	                      System.out.println("Element was " + ((String) element));
	                      e.printStackTrace();
	                  }
	              }
	              else if(element instanceof Integer) 
	              {
	                  //From the database 'element' will be a Long
	                  id = (Integer) element;
	              }

	              
	              if(id != null)
	              {
	            	  Document d = documentService.findById(id);
	            	  return d;
	              }
	              
	              return null;
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
	
	/************************** ORDER FORMS **************************/
	@RequestMapping(value = { "/admin/orderForms" }, method = RequestMethod.GET)
	public String listOrderForms(ModelMap model) {

		List<OrderForm> orderForms = orderFormService.findAllOrderForms();
		model.addAttribute("orderForms", orderForms);
		return "orderForms";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}" }, method = RequestMethod.GET)
	public String order(@PathVariable String uuid, ModelMap model) 
	{
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", false);
		return "orderForm";
	}
		
	@RequestMapping(value = { "/admin/orderForms/create" }, method = RequestMethod.GET)
	public String createOrderForm(ModelMap model) 
	{
		//Get Exams & Documents from DB
		List<Exam> exams = examService.findAllExams();
		List<Document> documents = documentService.findAllDocuments();
		
		OrderForm orderForm = new OrderForm();
		
		model.addAttribute("exams", exams);
		model.addAttribute("docs", documents);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", false);
		
		return "createOrEditOrderForm";
	}

	@RequestMapping(value = { "/admin/orderForms/create" }, method = RequestMethod.POST)
	public String createOrderForm(@Valid OrderForm orderForm, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditOrderForm";
		}		
		
		orderForm.setUuid(UUID.randomUUID().toString());
		for(OrderFormExam e : orderForm.getOrderFormExams())
		{
			e.setOrderForm(orderForm);
		}
		for(OrderFormDocument d : orderForm.getOrderFormDocuments())
		{
			d.setOrderForm(orderForm);
		}		
		orderForm.setLocked(false);
		orderForm.setVisible(true);
		
		orderFormService.saveOrderForm(orderForm);
		
		model.addAttribute("success", "OrderForm: " + orderForm.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/orderForms");
		model.addAttribute("returnLinkText", "Order Forms");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/edit" }, method = RequestMethod.GET)
	public String editOrderForm(@PathVariable String uuid, ModelMap model) 
	{
		//Get Exams & Documents from DB
		List<Exam> exams = examService.findAllExams();
		List<Document> documents = documentService.findAllDocuments();		
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		List<SelectedDocument> selectedDocuments = new ArrayList<>();
		List<SelectedExam> selectedExams = new ArrayList<>();
		
		for(Document d : documents)
		{
			SelectedDocument sd = new SelectedDocument();
			sd.setDocument(d);
			sd.setSelected(false);
			
			if(hasDocument(orderForm.getOrderFormDocuments(), d))
			{
				sd.setSelected(true);
			}
			
			selectedDocuments.add(sd);
		}
		
		for(Exam e : exams)
		{
			SelectedExam se = new SelectedExam();
			se.setExam(e);
			se.setSelected(false);
			
			if(hasExam(orderForm.getOrderFormExams(), e))
			{
				se.setSelected(true);
			}
			
			selectedExams.add(se);
		}
		
		model.addAttribute("exams", exams);
		model.addAttribute("selectedDocuments", selectedDocuments);
		model.addAttribute("selectedExams", selectedExams);
		model.addAttribute("docs", documents);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", true);
		return "createOrEditOrderForm";
	}
	
	private boolean hasDocument(Set<OrderFormDocument> orderFormDocuments, Document document)
	{
		for(OrderFormDocument ofd : orderFormDocuments)
		{
			if(ofd.getDocument().getId() == document.getId())
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean hasExam(Set<OrderFormExam> orderFormExams, Exam exam)
	{
		for(OrderFormExam ofe : orderFormExams)
		{
			if(ofe.getExam().getId() == exam.getId())
			{
				return true;
			}
		}
		
		return false;
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateOrderForm(@Valid OrderForm orderForm, @PathVariable String uuid, BindingResult result, ModelMap model)
	{
		if (result.hasErrors()) 
		{
			return "createOrEditOrderForm";
		}
		
		OrderForm orderForm2 = orderFormService.findByUUID(uuid);
		orderForm2.getOrderFormDocuments().clear();
		orderForm2.getOrderFormExams().clear();
		
		for(OrderFormExam e : orderForm.getOrderFormExams())
		{
			e.setOrderForm(orderForm);
			orderForm2.getOrderFormExams().add(e);
		}
		
		for(OrderFormDocument d : orderForm.getOrderFormDocuments())
		{
			d.setOrderForm(orderForm);
			orderForm2.getOrderFormDocuments().add(d);
		}
		
		orderFormService.updateOrderForm(orderForm2);
		
		model.addAttribute("success", "OrderForm: " + orderForm.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/orderForms");
		model.addAttribute("returnLinkText", "Order Forms");

		return "success";
	}
	
	@RequestMapping(value = { "admin/orderForms/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteOrderForm(@PathVariable String uuid, ModelMap model)
	{
		orderFormService.deleteOrderForm(uuid);
		
		return "redirect:/admin/orderForms";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockOrderForm(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		orderFormService.lockByOrderFormUUID(uuid, isLocked);
		return "redirect:/admin/orderForms";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideOrderForm(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		orderFormService.hideByOrderFormUUID(uuid, isHidden);
		return "redirect:/admin/orderForms";
	}

	@RequestMapping(value = { "/admin/orderForms/{uuid}/active/{isActive}" }, method = RequestMethod.GET)
	public String activateOrderForm(@PathVariable String uuid, @PathVariable boolean isActive) 
	{
		orderFormService.activateOrderFormUUID(uuid, isActive);
		return "redirect:/admin/orderForms";
	}
	
	
	/************************** USERS **************************/
	@RequestMapping(value = { "/admin/users" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) 
	{
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}
	
	@RequestMapping(value = { "admin/users/create" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) 
	{
		User user = new User();
		List<District> districts = districtService.findAllDistricts();
		model.addAttribute("user", user);
		model.addAttribute("districts", districts);
		model.addAttribute("edit", false);
		return "createUser";
	}
	
	@RequestMapping(value = { "admin/users/create" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createUser";
		}

		if(!userService.isUserUsernameUnique(user.getId(), user.getUsername()))
		{
			FieldError usernameError =new FieldError("user","username",messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
		    result.addError(usernameError);
			return "createUser";
		}
		
		user.setLocked(false);
		user.setVisible(true);
		user.setUuid(UUID.randomUUID().toString());
		userService.saveUser(user);

		model.addAttribute("success", "User: " + user.getUsername() + " was registered successfully");
		model.addAttribute("returnLink", "/admin/users");
		model.addAttribute("returnLinkText", "Users");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/edit" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String uuid, ModelMap model) 
	{
		User user = userService.findByUUID(uuid);
		List<District> districts = districtService.findAllDistricts();
		model.addAttribute("user", user);
		model.addAttribute("districts", districts);
		model.addAttribute("edit", true);
		return "createUser";
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createUser";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING USERNAME in UI which is a unique key to a User.
		if(!userService.isUserUsernameUnique(user.getId(), user.getUsername())){
			FieldError usernameError =new FieldError("user","username",messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
		    result.addError(usernameError);
			return "registration";
		}*/

		userService.updateUser(user);

		model.addAttribute("success", "User: " + user.getUsername() +  " was updated successfully");
		model.addAttribute("returnLink", "/admin/users");
		model.addAttribute("returnLinkText", "Users");
		return "success";
	}
	
	
	@RequestMapping(value = { "/admin/users/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String uuid) 
	{
		userService.deleteUserByUUID(uuid);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockUser(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		userService.lockByUUID(uuid, isLocked);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideUser(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		userService.hideByUUID(uuid, isHidden);
		return "redirect:/admin/users";
	}
	
	/************************** EXAMS **************************/
	@RequestMapping(value = { "/admin/exams" }, method = RequestMethod.GET)
	public String listExams(ModelMap model) {

		List<Exam> exams = examService.findAllExams();
		model.addAttribute("exams", exams);
		return "exams";
	}
	
	@RequestMapping(value = { "/admin/exams/create" }, method = RequestMethod.GET)
	public String createExam(ModelMap model) 
	{
		Exam exam = new Exam();
		model.addAttribute("exam", exam);
		model.addAttribute("edit", false);
		return "createOrEditExam";
	}

	@RequestMapping(value = { "/admin/exams/create" }, method = RequestMethod.POST)
	public String createExam(@Valid Exam exam, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditExam";
		}
		exam.setUuid(UUID.randomUUID().toString());
		exam.setVisible(true);
		exam.setLocked(false);
		examService.saveExam(exam);

		model.addAttribute("success", "Exam: " + exam.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/exams");
		model.addAttribute("returnLinkText", "Exams");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/exams/{uuid}/edit" }, method = RequestMethod.GET)
	public String editExam(@PathVariable String uuid, ModelMap model) 
	{
		Exam exam = examService.findByUUID(uuid);
		
		if(!exam.getLocked())
		{
			model.addAttribute("exam", exam);
			model.addAttribute("edit", true);
			return "createOrEditExam";
		}
		else return "403";
	}
	
	@RequestMapping(value = { "/admin/exams/{uuid}/edit" }, method = RequestMethod.POST)
	public String editExam(@Valid Exam exam, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditExam";
		}

		examService.updateExam(exam);

		model.addAttribute("success", "Exam: " + exam.getName() + " - " + exam.getCode() +  " was updated successfully");
		model.addAttribute("returnLink", "/admin/exams");
		model.addAttribute("returnLinkText", "Exams");
		return "success";
	}


	@RequestMapping(value = { "/admin/exams/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteExam(@PathVariable String uuid) 
	{
		Exam exam = examService.findByUUID(uuid);	
		if(!exam.getLocked())
		{
			examService.deleteByExamUUID(uuid);
			return "redirect:/admin/exams";
		}
		else return "403";
	}
	
	@RequestMapping(value = { "/admin/exams/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockExam(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		examService.lockByExamUUID(uuid, isLocked);
		return "redirect:/admin/exams";
	}
	
	@RequestMapping(value = { "/admin/exams/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideExam(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		examService.hideByExamUUID(uuid, isHidden);
		return "redirect:/admin/exams";
	}
	
	/************************** DOCUMENTS **************************/
	@RequestMapping(value = { "/admin/documents" }, method = RequestMethod.GET)
	public String listDocuments(ModelMap model) {

		List<Document> documents = documentService.findAllDocuments();
		model.addAttribute("documents", documents);
		return "documents";
	}
		
	@RequestMapping(value = { "/admin/documents/create" }, method = RequestMethod.GET)
	public String createDocument(ModelMap model) 
	{
		Document document = new Document();
		model.addAttribute("document", document);
		model.addAttribute("edit", false);
		return "createOrEditDocument";
	}

	@RequestMapping(value = { "/admin/documents/create" }, method = RequestMethod.POST)
	public String createDocument(@Valid Document document, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDocument";
		}
		document.setUuid(UUID.randomUUID().toString());
		document.setVisible(true);
		document.setLocked(false);
		documentService.saveDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/documents");
		model.addAttribute("returnLinkText", "Documents");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/edit" }, method = RequestMethod.GET)
	public String editDocument(@PathVariable String uuid, ModelMap model) 
	{
		Document document = documentService.findByUUID(uuid);
		
		if(!document.getLocked())
		{
			model.addAttribute("document", document);
			model.addAttribute("edit", true);
			return "createOrEditDocument";
		}
		else return "403";
		
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateDocument(@Valid Document document, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDocument";
		}

		documentService.updateDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/documents");
		model.addAttribute("returnLinkText", "Documents");
		return "success";
	}


	@RequestMapping(value = { "/admin/documents/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable String uuid) 
	{
		Document document = documentService.findByUUID(uuid);
		if(!document.getLocked())
		{
			documentService.deleteByDocumentUUID(uuid);
			return "redirect:/admin/documents";
		}
		else return "403";	
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockDocument(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		documentService.lockByDocumentUUID(uuid, isLocked);
		return "redirect:/admin/documents";
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideDocument(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		documentService.hideByDocumentUUID(uuid, isHidden);
		return "redirect:/admin/documents";
	}
	
	/************************** PRINT OPTIONS **************************/
	@RequestMapping(value = { "/admin/printOptions" }, method = RequestMethod.GET)
	public String listPrintOptions(ModelMap model) {

		List<OptionPrint> printOptions = optionPrintService.findAllOptionPrints();
		model.addAttribute("printOptions", printOptions);
		return "printOptions";
	}
		
	@RequestMapping(value = { "/admin/printOptions/create" }, method = RequestMethod.GET)
	public String createPrintOption(ModelMap model) 
	{
		OptionPrint printOption = new OptionPrint();
		model.addAttribute("printOption", printOption);
		model.addAttribute("edit", false);
		return "createOrEditPrintOption";
	}

	@RequestMapping(value = { "/admin/printOptions/create" }, method = RequestMethod.POST)
	public String createPrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditPrintOption";
		}	
		
		optionPrint.setUuid(UUID.randomUUID().toString());
		optionPrint.setVisible(true);
		optionPrint.setLocked(false);
		optionPrintService.save(optionPrint);
		
		model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/printOptions");
		model.addAttribute("returnLinkText", "Print Options");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/printOptions/{uuid}/edit" }, method = RequestMethod.GET)
	public String editPrintOption(@PathVariable String uuid, ModelMap model) 
	{
		OptionPrint printOption = optionPrintService.findByUUID(uuid);
		
		if(!printOption.getLocked())
		{
			model.addAttribute("printOption", printOption);
			model.addAttribute("edit", true);
			return "createOrEditPrintOption";
		}
		else return "403";
		
	}
	
	@RequestMapping(value = { "/admin/printOptions/{uuid}/edit" }, method = RequestMethod.POST)
	public String updatePrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditPrintOption";
		}

		optionPrintService.update(optionPrint);

		model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/printOptions");
		model.addAttribute("returnLinkText", "Print Options");
		return "success";
	}


	@RequestMapping(value = { "/admin/printOptions/{uuid}/delete" }, method = RequestMethod.GET)
	public String deletePrintOption(@PathVariable String uuid) 
	{
		OptionPrint op = optionPrintService.findByUUID(uuid);
		
		if(!op.getLocked())
		{
			optionPrintService.deleteByUUID(uuid);
			return "redirect:/admin/printOptions";
		}
		else return "403";	
	}
	
	@RequestMapping(value = { "/admin/printOptions/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockPrintOption(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		optionPrintService.lockByOptionPrintUUID(uuid, isLocked);
		return "redirect:/admin/printOptions";
	}
	
	@RequestMapping(value = { "/admin/printOptions/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hidePrintOption(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		optionPrintService.hideByOptionPrintUUID(uuid, isHidden);
		return "redirect:/admin/printOptions";
	}
	
	
	/************************** SCAN OPTIONS **************************/
	@RequestMapping(value = { "/admin/scanOptions" }, method = RequestMethod.GET)
	public String listScanOptions(ModelMap model) {

		List<OptionScan> scanOptions = optionScanService.findAllOptionScans();
		model.addAttribute("scanOptions", scanOptions);
		return "scanOptions";
	}
		
	@RequestMapping(value = { "/admin/scanOptions/create" }, method = RequestMethod.GET)
	public String createScanOption(ModelMap model) 
	{
		OptionScan scanOption = new OptionScan();
		model.addAttribute("scanOption", scanOption);
		model.addAttribute("edit", false);
		return "createOrEditScanOption";
	}

	@RequestMapping(value = { "/admin/scanOptions/create" }, method = RequestMethod.POST)
	public String createScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}	
		
		optionScan.setUuid(UUID.randomUUID().toString());
		optionScan.setVisible(true);
		optionScan.setLocked(false);
		optionScanService.save(optionScan);
		
		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/scanOptions");
		model.addAttribute("returnLinkText", "Scan Options");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{id}/edit" }, method = RequestMethod.GET)
	public String editScanOption(@PathVariable int id, ModelMap model) 
	{
		OptionScan scanOption = optionScanService.findById(id);
		
		if(!scanOption.getLocked())
		{
			model.addAttribute("scanOption", scanOption);
			model.addAttribute("edit", true);
			return "createOrEditScanOption";
		}
		else return "403";	
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{id}/edit" }, method = RequestMethod.POST)
	public String updateScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}

		optionScanService.update(optionScan);

		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/scanOptions");
		model.addAttribute("returnLinkText", "Scan Options");
		return "success";
	}


	@RequestMapping(value = { "/admin/scanOptions/{id}/delete" }, method = RequestMethod.GET)
	public String deleteScanOption(@PathVariable int id) 
	{
		OptionScan os = optionScanService.findById(id);
		
		if(!os.getLocked())
		{
			optionScanService.delete(id);
			return "redirect:/admin/scanOptions";
		}
		else return "403";
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{id}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockScanOption(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		optionScanService.lockByOptionScanId(id, isLocked);
		return "redirect:/admin/scanOptions";
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{id}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideScanOption(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		optionScanService.hideByOptionScanId(id, isHidden);
		return "redirect:/admin/scanOptions";
	}
	
	
	/************************** SETTINGS **************************/
	@RequestMapping(value = { "/admin/settings" }, method = RequestMethod.GET)
	public String listSettings(ModelMap model) {

		List<Setting> settings = settingService.findAll();
		model.addAttribute("settings", settings);
		return "settings";
	}
		
	@RequestMapping(value = { "/admin/settings/create" }, method = RequestMethod.GET)
	public String createSetting(ModelMap model) 
	{
		Setting setting = new Setting();
		model.addAttribute("setting", setting);
		model.addAttribute("edit", false);
		return "createOrEditSetting";
	}

	@RequestMapping(value = { "/admin/settings/create" }, method = RequestMethod.POST)
	public String createSetting(@Valid Setting setting, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditSetting";
		}	
		
		settingService.saveSetting(setting);
		model.addAttribute("success", "Setting: " + setting.getKey() + " was created successfully");
		model.addAttribute("returnLink", "/admin/settings");
		model.addAttribute("returnLinkText", "Settings");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/settings/{id}/edit" }, method = RequestMethod.GET)
	public String editSetting(@PathVariable int id, ModelMap model) 
	{
		Setting setting = settingService.findById(id);
		model.addAttribute("setting", setting);
		model.addAttribute("edit", true);
		return "createOrEditSetting";
	}
	
	@RequestMapping(value = { "/admin/settings/{id}/edit" }, method = RequestMethod.POST)
	public String updateSetting(@Valid Setting setting, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditSetting";
		}

		settingService.updateSetting(setting);

		model.addAttribute("success", "Setting: " + setting.getKey() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/settings");
		model.addAttribute("returnLinkText", "Settings");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/settings/{id}/delete" }, method = RequestMethod.GET)
	public String deleteSetting(@PathVariable int id) 
	{
		settingService.deleteBySettingId(id);
		return "redirect:/admin/settings";
	}
	
	/************************** DISTRICTS **************************/
	@RequestMapping(value = { "/admin/districts" }, method = RequestMethod.GET)
	public String listDistricts(ModelMap model) {

		List<District> districts = districtService.findAllDistricts();
		model.addAttribute("districts", districts);
		return "districts";
	}
		
	@RequestMapping(value = { "/admin/districts/create" }, method = RequestMethod.GET)
	public String createDistrict(ModelMap model) 
	{
		District district = new District();
		
		model.addAttribute("district", district);
		model.addAttribute("edit", false);
		return "createOrEditDistrict";
	}

	@RequestMapping(value = { "/admin/districts/create" }, method = RequestMethod.POST)
	public String createDistrict(@Valid District district, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDistrict";
		}	
		
		district.setUuid(UUID.randomUUID().toString());
		district.setVisible(true);
		district.setLocked(false);
		districtService.saveDistrict(district);
		
		
		model.addAttribute("success", "District: " + district.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/districts");
		model.addAttribute("returnLinkText", "Districts");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/districts/{uuid}/edit" }, method = RequestMethod.GET)
	public String editDistrict(@PathVariable String uuid, ModelMap model) 
	{
		District district = districtService.findByUUID(uuid);
		
		if(!district.getLocked())
		{
			model.addAttribute("district", district);
			model.addAttribute("edit", true);
			return "createOrEditDistrict";
		}
		else return "403";
	}
	
	@RequestMapping(value = { "/admin/districts/{id}/edit" }, method = RequestMethod.POST)
	public String updateDistrict(@Valid District district, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDistrict";
		}
		districtService.updateDistrict(district);

		model.addAttribute("success", "District: " + district.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/districts");
		model.addAttribute("returnLinkText", "Districts");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/districts/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteDistrict(@PathVariable String uuid) 
	{
		District d = districtService.findByUUID(uuid);
		if(!d.getLocked())
		{
			districtService.deleteByUUID(uuid);
			return "redirect:/admin/districts";
		}
		else return "403";	
	}
	
	@RequestMapping(value = { "/admin/districts/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockDistrict(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		districtService.lockByUUID(uuid, isLocked);
		return "redirect:/admin/districts";
	}
	
	@RequestMapping(value = { "/admin/districts/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideDistrict(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		districtService.hideByUUID(uuid, isHidden);
		return "redirect:/admin/districts";
	}
	
	/************************** SCHOOLS **************************/
	@RequestMapping(value = { "/admin/schools" }, method = RequestMethod.GET)
	public String listSchools(ModelMap model) {

		List<School> schools = schoolService.findAll();
		model.addAttribute("schools", schools);
		return "schools";
	}
		
	@RequestMapping(value = { "/admin/schools/create" }, method = RequestMethod.GET)
	public String createSchool(ModelMap model) 
	{
		School school = new School();
		List<District> districts = districtService.findAllDistricts();
		
		model.addAttribute("school", school);
		model.addAttribute("districts", districts);
		model.addAttribute("edit", false);
		return "createOrEditSchool";
	}

	@RequestMapping(value = { "/admin/schools/create" }, method = RequestMethod.POST)
	public String createSchool(@Valid School school, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditSchool";
		}	
		
		school.setUuid(UUID.randomUUID().toString());
		schoolService.save(school);
		model.addAttribute("success", "School: " + school.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/schools");
		model.addAttribute("returnLinkText", "Schools");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/schools/{uuid}/edit" }, method = RequestMethod.GET)
	public String editSchool(@PathVariable String uuid, ModelMap model) 
	{
		School school = schoolService.findByUUID(uuid);
		List<District> districts = districtService.findAllDistricts();	
		model.addAttribute("school", school);
		model.addAttribute("districts", districts);
		model.addAttribute("edit", true);
		return "createOrEditSchool";
	}
	
	@RequestMapping(value = { "/admin/schools/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateSchool(@Valid School school, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditSchool";
		}
		schoolService.update(school);

		model.addAttribute("success", "School: " + school.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/schools");
		model.addAttribute("returnLinkText", "Schools");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/schools/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteSchool(@PathVariable String uuid) 
	{
		schoolService.deleteByUUID(uuid);
		return "redirect:/admin/schools";
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