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

	/************************** ORDER FORMS **************************/
	@RequestMapping(value = { "/orderForms" }, method = RequestMethod.GET)
	public String listOrderForms(ModelMap model) {

		List<OrderForm> orderForms = orderFormService.findAllOrderForms();
		model.addAttribute("orderForms", orderForms);
		model.addAttribute("loggedinuser", getPrincipal());
		return "orderForms";
	}
	
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

	@RequestMapping(value = { "/createOrderForm" }, method = RequestMethod.POST)
	public String createOrderForm(@Valid OrderForm orderForm, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditOrderForm";
		}
		
		orderFormService.saveOrderForm(orderForm);

		model.addAttribute("success", "OrderForm: " + orderForm.getName() + " was created successfully");
		model.addAttribute("loggedinuser", getPrincipal());

		return "createOrEditOrderFormSuccess";
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


	/************************** EXAMS **************************/
	@RequestMapping(value = { "/exams" }, method = RequestMethod.GET)
	public String listExams(ModelMap model) {

		List<Exam> exams = examService.findAllExams();
		model.addAttribute("exams", exams);
		model.addAttribute("loggedinuser", getPrincipal());
		return "exams";
	}
	
	@RequestMapping(value = { "/createExam" }, method = RequestMethod.GET)
	public String createExam(ModelMap model) 
	{
		Exam exam = new Exam();
		model.addAttribute("exam", exam);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditExam";
	}

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
	
	@RequestMapping(value = { "/lock-exam-{id}-{isLocked}" }, method = RequestMethod.GET)
	public String lockExam(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		examService.lockByExamId(id, isLocked);
		return "redirect:/exams";
	}
	
	@RequestMapping(value = { "/hide-exam-{id}-{isHidden}" }, method = RequestMethod.GET)
	public String hideExam(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		examService.hideByExamId(id, isHidden);
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
	
	@RequestMapping(value = { "/lock-document-{id}-{isLocked}" }, method = RequestMethod.GET)
	public String lockDocument(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		documentService.lockByDocumentId(id, isLocked);
		return "redirect:/documents";
	}
	
	@RequestMapping(value = { "/hide-document-{id}-{isHidden}" }, method = RequestMethod.GET)
	public String hideDocument(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		documentService.hideByDocumentId(id, isHidden);
		return "redirect:/documents";
	}
	
	/************************** PRINT OPTIONS **************************/
	@RequestMapping(value = { "/printOptions" }, method = RequestMethod.GET)
	public String listPrintOptions(ModelMap model) {

		List<OptionPrint> printOptions = optionPrintService.findAllOptionPrints();
		model.addAttribute("printOptions", printOptions);
		model.addAttribute("loggedinuser", getPrincipal());
		return "printOptions";
	}
		
	@RequestMapping(value = { "/createPrintOption" }, method = RequestMethod.GET)
	public String createPrintOption(ModelMap model) 
	{
		OptionPrint printOption = new OptionPrint();
		model.addAttribute("printOption", printOption);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditPrintOption";
	}

	@RequestMapping(value = { "/createPrintOption" }, method = RequestMethod.POST)
	public String createPrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditPrintOption";
		}	
		
		optionPrintService.save(optionPrint);
		model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was created successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditPrintOptionSuccess";
	}
	
	@RequestMapping(value = { "/edit-printOption-{id}" }, method = RequestMethod.GET)
	public String editPrintOption(@PathVariable int id, ModelMap model) 
	{
		OptionPrint printOption = optionPrintService.findById(id);
		model.addAttribute("printOption", printOption);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditPrintOption";
	}
	
	@RequestMapping(value = { "/edit-printOption-{id}" }, method = RequestMethod.POST)
	public String updatePrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditPrintOption";
		}

		optionPrintService.update(optionPrint);

		model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditPrintOptionSuccess";
	}


	@RequestMapping(value = { "/delete-printOption-{id}" }, method = RequestMethod.GET)
	public String deletePrintOption(@PathVariable int id) 
	{
		optionPrintService.delete(id);
		return "redirect:/printOptions";
	}
	
	@RequestMapping(value = { "/lock-printOption-{id}-{isLocked}" }, method = RequestMethod.GET)
	public String lockPrintOption(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		optionPrintService.lockByOptionPrintId(id, isLocked);
		return "redirect:/printOptions";
	}
	
	@RequestMapping(value = { "/hide-printOption-{id}-{isHidden}" }, method = RequestMethod.GET)
	public String hidePrintOption(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		optionPrintService.hideByOptionPrintId(id, isHidden);
		return "redirect:/printOptions";
	}
	
	
	/************************** SCAN OPTIONS **************************/
	@RequestMapping(value = { "/scanOptions" }, method = RequestMethod.GET)
	public String listScanOptions(ModelMap model) {

		List<OptionScan> scanOptions = optionScanService.findAllOptionScans();
		model.addAttribute("scanOptions", scanOptions);
		model.addAttribute("loggedinuser", getPrincipal());
		return "scanOptions";
	}
		
	@RequestMapping(value = { "/createScanOption" }, method = RequestMethod.GET)
	public String createScanOption(ModelMap model) 
	{
		OptionScan scanOption = new OptionScan();
		model.addAttribute("scanOption", scanOption);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOption";
	}

	@RequestMapping(value = { "/createScanOption" }, method = RequestMethod.POST)
	public String createScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}	
		
		optionScanService.save(optionScan);
		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was created successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOptionSuccess";
	}
	
	@RequestMapping(value = { "/edit-scanOption-{id}" }, method = RequestMethod.GET)
	public String editScanOption(@PathVariable int id, ModelMap model) 
	{
		OptionScan scanOption = optionScanService.findById(id);
		model.addAttribute("scanOption", scanOption);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOption";
	}
	
	@RequestMapping(value = { "/edit-scanOption-{id}" }, method = RequestMethod.POST)
	public String updateScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model, @PathVariable int id) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}

		optionScanService.update(optionScan);

		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOptionSuccess";
	}


	@RequestMapping(value = { "/delete-scanOption-{id}" }, method = RequestMethod.GET)
	public String deleteScanOption(@PathVariable int id) 
	{
		optionScanService.delete(id);
		return "redirect:/scanOptions";
	}
	
	@RequestMapping(value = { "/lock-scanOption-{id}-{isLocked}" }, method = RequestMethod.GET)
	public String lockScanOption(@PathVariable int id, @PathVariable boolean isLocked) 
	{
		optionScanService.lockByOptionScanId(id, isLocked);
		return "redirect:/scanOptions";
	}
	
	@RequestMapping(value = { "/hide-scanOption-{id}-{isHidden}" }, method = RequestMethod.GET)
	public String hideScanOption(@PathVariable int id, @PathVariable boolean isHidden) 
	{
		optionScanService.hideByOptionScanId(id, isHidden);
		return "redirect:/scanOptions";
	}
	
	
	/************************** SETTINGS **************************/
	@RequestMapping(value = { "/settings" }, method = RequestMethod.GET)
	public String listSettings(ModelMap model) {

		List<Setting> settings = settingService.findAll();
		model.addAttribute("settings", settings);
		model.addAttribute("loggedinuser", getPrincipal());
		return "settings";
	}
		
	@RequestMapping(value = { "/createSetting" }, method = RequestMethod.GET)
	public String createSetting(ModelMap model) 
	{
		Setting setting = new Setting();
		model.addAttribute("setting", setting);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditScanOption";
	}

	@RequestMapping(value = { "/createSetting" }, method = RequestMethod.POST)
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
	
	@RequestMapping(value = { "/edit-setting-{id}" }, method = RequestMethod.GET)
	public String editSetting(@PathVariable int id, ModelMap model) 
	{
		Setting setting = settingService.findById(id);
		model.addAttribute("setting", setting);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "createOrEditSetting";
	}
	
	@RequestMapping(value = { "/edit-setting-{id}" }, method = RequestMethod.POST)
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


	@RequestMapping(value = { "/delete-setting-{id}" }, method = RequestMethod.GET)
	public String deleteSetting(@PathVariable int id) 
	{
		settingService.deleteBySettingId(id);
		return "redirect:/settings";
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

}