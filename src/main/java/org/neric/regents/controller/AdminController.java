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
import org.neric.regents.model.Setting;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderFormService;
import org.neric.regents.service.OrderService;
import org.neric.regents.service.SettingService;
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
	
	@Autowired
	SettingService settingService;
	
	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	
	/************************** USERS **************************/
	@RequestMapping(value = { "/admin/users" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) 
	{
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
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
		model.addAttribute("loggedinuser", getPrincipal());
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
		
		userService.saveUser(user);

		model.addAttribute("success", "User: " + user.getUsername() + " was registered successfully");
		model.addAttribute("returnLink", "/admin/users");
		model.addAttribute("returnLinkText", "Users");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/users/{username}/edit" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String username) 
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
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/users/{username}/edit" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, ModelMap model) 
	{
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createUser";
	}
	
	@RequestMapping(value = { "/admin/users/{username}/delete" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username) 
	{
		userService.deleteUserByUsername(username);
		return "redirect:/admin/users";
	}
	
	
	/************************** ORDER FORMS **************************/
	@RequestMapping(value = { "/admin/orderForms" }, method = RequestMethod.GET)
	public String listOrderForms(ModelMap model) {

		List<OrderForm> orderForms = orderFormService.findAllOrderForms();
		model.addAttribute("orderForms", orderForms);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orderForms";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}" }, method = RequestMethod.GET)
	public String order(@PathVariable String uuid, ModelMap model) 
	{
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orderForm";
	}
		
	@RequestMapping(value = { "/admin/orderForms/create" }, method = RequestMethod.GET)
	public String createOrderForm(ModelMap model) 
	{
		List<Exam> exams = examService.findAllExams();
		List<Document> documents = documentService.findAllDocuments();

		OrderForm orderForm = new OrderForm();
		model.addAttribute("exams", exams);
		model.addAttribute("documents", documents);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		
		return "createOrEditOrderForm";
	}

	@RequestMapping(value = { "/admin/orderForms/create" }, method = RequestMethod.POST)
	public String createOrderForm(@Valid OrderForm orderForm, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditOrderForm";
		}		
		orderFormService.saveOrderForm(orderForm);

		model.addAttribute("success", "OrderForm: " + orderForm.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/orderForms");
		model.addAttribute("returnLinkText", "Order Forms");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/edit" }, method = RequestMethod.GET)
	public String editOrderForm(@PathVariable String uuid, ModelMap model) 
	{
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		model.addAttribute("orderForm", orderForm);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditOrderForm";
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


	/************************** EXAMS **************************/
	@RequestMapping(value = { "/admin/exams" }, method = RequestMethod.GET)
	public String listExams(ModelMap model) {

		List<Exam> exams = examService.findAllExams();
		model.addAttribute("exams", exams);
		model.addAttribute("loggedinuser", getPrincipal());
		return "exams";
	}
	
	@RequestMapping(value = { "/admin/exams/create" }, method = RequestMethod.GET)
	public String createExam(ModelMap model) 
	{
		Exam exam = new Exam();
		model.addAttribute("exam", exam);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditExam";
	}

	@RequestMapping(value = { "/admin/exams/create" }, method = RequestMethod.POST)
	public String createExam(@Valid Exam exam, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditExam";
		}		
		examService.saveExam(exam);

		model.addAttribute("success", "Exam: " + exam.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/exams");
		model.addAttribute("returnLinkText", "Exams");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/exams/{id}/edit" }, method = RequestMethod.GET)
	public String editExam(@PathVariable int id, ModelMap model) 
	{
		Exam exam = examService.findById(id);
		model.addAttribute("exam", exam);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditExam";
	}
	
	@RequestMapping(value = { "/admin/exams/{id}/edit" }, method = RequestMethod.POST)
	public String editExam(@Valid Exam exam, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditExam";
		}

		examService.updateExam(exam);

		model.addAttribute("success", "Exam: " + exam.getName() + " - " + exam.getCode() +  " was updated successfully");
		model.addAttribute("returnLink", "/admin/exams");
		model.addAttribute("returnLinkText", "Exams");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}


	@RequestMapping(value = { "/admin/exams/{id}/delete" }, method = RequestMethod.GET)
	public String deleteExam(@PathVariable int id) 
	{
		examService.deleteByExamId(id);
		return "redirect:/admin/exams";
	}
	
	@RequestMapping(value = { "/admin/exams/{id}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockExam(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		examService.lockByExamId(id, isLocked);
		return "redirect:/admin/exams";
	}
	
	@RequestMapping(value = { "/admin/exams/{id}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideExam(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		examService.hideByExamId(id, isHidden);
		return "redirect:/admin/exams";
	}
	
	/************************** DOCUMENTS **************************/
	@RequestMapping(value = { "/admin/documents" }, method = RequestMethod.GET)
	public String listDocuments(ModelMap model) {

		List<Document> documents = documentService.findAllDocuments();
		model.addAttribute("documents", documents);
		model.addAttribute("loggedinuser", getPrincipal());
		return "documents";
	}
		
	@RequestMapping(value = { "/admin/documents/create" }, method = RequestMethod.GET)
	public String createDocument(ModelMap model) 
	{
		Document document = new Document();
		model.addAttribute("document", document);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditDocument";
	}

	@RequestMapping(value = { "/admin/documents/create" }, method = RequestMethod.POST)
	public String createDocument(@Valid Document document, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDocument";
		}		
		documentService.saveDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/documents");
		model.addAttribute("returnLinkText", "Documents");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/documents/{id}/edit" }, method = RequestMethod.GET)
	public String editDocument(@PathVariable int id, ModelMap model) 
	{
		Document document = documentService.findById(id);
		model.addAttribute("document", document);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditDocument";
	}
	
	@RequestMapping(value = { "/admin/documents/{id}/edit" }, method = RequestMethod.POST)
	public String updateDocument(@Valid Document document, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditDocument";
		}

		documentService.updateDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/documents");
		model.addAttribute("returnLinkText", "Documents");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}


	@RequestMapping(value = { "/admin/documents/{id}/delete" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int id) 
	{
		documentService.deleteByDocumentId(id);
		return "redirect:/admin/documents";
	}
	
	@RequestMapping(value = { "/admin/documents/{id}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockDocument(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		documentService.lockByDocumentId(id, isLocked);
		return "redirect:/admin/documents";
	}
	
	@RequestMapping(value = { "/admin/documents/{id}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideDocument(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		documentService.hideByDocumentId(id, isHidden);
		return "redirect:/admin/documents";
	}
	
	/************************** PRINT OPTIONS **************************/
	@RequestMapping(value = { "/admin/printOptions" }, method = RequestMethod.GET)
	public String listPrintOptions(ModelMap model) {

		List<OptionPrint> printOptions = optionPrintService.findAllOptionPrints();
		model.addAttribute("printOptions", printOptions);
		model.addAttribute("loggedinuser", getPrincipal());
		return "printOptions";
	}
		
	@RequestMapping(value = { "/admin/printOptions/create" }, method = RequestMethod.GET)
	public String createPrintOption(ModelMap model) 
	{
		OptionPrint printOption = new OptionPrint();
		model.addAttribute("printOption", printOption);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditPrintOption";
	}

	@RequestMapping(value = { "/admin/printOptions/create" }, method = RequestMethod.POST)
	public String createPrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditPrintOption";
		}	
		
		optionPrintService.save(optionPrint);
		model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/printOptions");
		model.addAttribute("returnLinkText", "Print Options");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/printOptions/{id}/edit" }, method = RequestMethod.GET)
	public String editPrintOption(@PathVariable int id, ModelMap model) 
	{
		OptionPrint printOption = optionPrintService.findById(id);
		model.addAttribute("printOption", printOption);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditPrintOption";
	}
	
	@RequestMapping(value = { "/admin/printOptions/{id}/edit" }, method = RequestMethod.POST)
	public String updatePrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditPrintOption";
		}

		optionPrintService.update(optionPrint);

		model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/printOptions");
		model.addAttribute("returnLinkText", "Print Options");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}


	@RequestMapping(value = { "/admin/printOptions/{id}/delete" }, method = RequestMethod.GET)
	public String deletePrintOption(@PathVariable int id) 
	{
		optionPrintService.delete(id);
		return "redirect:/admin/printOptions";
	}
	
	@RequestMapping(value = { "/admin/printOptions/{id}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockPrintOption(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		optionPrintService.lockByOptionPrintId(id, isLocked);
		return "redirect:/admin/printOptions";
	}
	
	@RequestMapping(value = { "/admin/printOptions/{id}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hidePrintOption(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		optionPrintService.hideByOptionPrintId(id, isHidden);
		return "redirect:/admin/printOptions";
	}
	
	
	/************************** SCAN OPTIONS **************************/
	@RequestMapping(value = { "/admin/scanOptions" }, method = RequestMethod.GET)
	public String listScanOptions(ModelMap model) {

		List<OptionScan> scanOptions = optionScanService.findAllOptionScans();
		model.addAttribute("scanOptions", scanOptions);
		model.addAttribute("loggedinuser", getPrincipal());
		return "scanOptions";
	}
		
	@RequestMapping(value = { "/admin/scanOptions/create" }, method = RequestMethod.GET)
	public String createScanOption(ModelMap model) 
	{
		OptionScan scanOption = new OptionScan();
		model.addAttribute("scanOption", scanOption);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOption";
	}

	@RequestMapping(value = { "/admin/scanOptions/create" }, method = RequestMethod.POST)
	public String createScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}	
		
		optionScanService.save(optionScan);
		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/scanOptions");
		model.addAttribute("returnLinkText", "Scan Options");
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{id}/edit" }, method = RequestMethod.GET)
	public String editScanOption(@PathVariable int id, ModelMap model) 
	{
		OptionScan scanOption = optionScanService.findById(id);
		model.addAttribute("scanOption", scanOption);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOption";
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
		model.addAttribute("loggedinuser", getPrincipal());
		return "success";
	}


	@RequestMapping(value = { "/admin/scanOptions/{id}/delete" }, method = RequestMethod.GET)
	public String deleteScanOption(@PathVariable int id) 
	{
		optionScanService.delete(id);
		return "redirect:/admin/scanOptions";
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
		model.addAttribute("loggedinuser", getPrincipal());
		return "settings";
	}
		
	@RequestMapping(value = { "/admin/settings/create" }, method = RequestMethod.GET)
	public String createSetting(ModelMap model) 
	{
		Setting setting = new Setting();
		model.addAttribute("setting", setting);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOption";
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
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditSettingSuccess";
	}
	
	@RequestMapping(value = { "/admin/settings/{id}/edit" }, method = RequestMethod.GET)
	public String editSetting(@PathVariable int id, ModelMap model) 
	{
		Setting setting = settingService.findById(id);
		model.addAttribute("setting", setting);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
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
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditSettingSuccess";
	}
	
	


	@RequestMapping(value = { "/admin/settings/{id}/delete" }, method = RequestMethod.GET)
	public String deleteSetting(@PathVariable int id) 
	{
		settingService.deleteBySettingId(id);
		return "redirect:/admin/settings";
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