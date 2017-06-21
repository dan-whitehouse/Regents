package org.neric.regents.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.neric.regents.converture.OptionPrintEditor;
import org.neric.regents.converture.OptionScanEditor;
import org.neric.regents.model.Document;
import org.neric.regents.model.Exam;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.OptionScan;
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.School;
import org.neric.regents.model.User;
import org.neric.regents.model.XDocumentWrapper;
import org.neric.regents.model.XExamWrapper;
import org.neric.regents.model.XForm2;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.SchoolService;
import org.neric.regents.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author daniel.whitehouse
 * http://howtodoinjava.com/spring/spring-mvc/spring-mvc-populate-and-validate-dropdown-example/
 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/spring-form-tld.html#spring-form.tld.hidden
 * https://www.mkyong.com/spring-mvc/spring-mvc-radiobutton-and-radiobuttons-example/
 * https://stackoverflow.com/questions/41861528/spring-4-mvc-form-create-object-with-list-of-sub-objects/41870452?noredirect=1#comment71414339_41870452
 */


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class XController2 
{
	private Validator validator;
	
	public XController2()
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
	

	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
        binder.registerCustomEditor(OptionPrint.class, optionPrintEditor);
        binder.registerCustomEditor(OptionScan.class, optionScanEditor);
    }

	@ModelAttribute("allExamOptions")
    public List<XExamWrapper> populateExamOptions() 
    {
        List<XExamWrapper> options = new ArrayList<>();
        
        for(Exam e : examService.findAllExams())
        {
        	options.add(new XExamWrapper(new OrderExam(e)));
        }
        return options;
    }
	
	@ModelAttribute("allDocumentOptions")
    public List<XDocumentWrapper> populateDocumentOptions() 
    {
        List<XDocumentWrapper> options = new ArrayList<>();
        
        for(Document e : documentService.findAllDocuments())
        {
        	options.add(new XDocumentWrapper(new OrderDocument(e)));
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
	
	
	@RequestMapping(value = { "/xorder2" }, method = RequestMethod.GET)
    public String setupForm(Model model) 
	{
		XForm2 xForm = new XForm2();
        model.addAttribute("xForm2", xForm);
        return "xorder2";
    }
	
	 @RequestMapping(value = { "/xorder2" }, method = RequestMethod.POST)
	 public String submitForm(@ModelAttribute("xForm2") XForm2 xForm, BindingResult result, SessionStatus status) 
	 {
	 
		Set<ConstraintViolation<XForm2>> violations = validator.validate(xForm);
		 
		for (ConstraintViolation<XForm2> violation : violations) 
		{
		    String propertyPath = violation.getPropertyPath().toString();
		    String message = violation.getMessage();
		    
		    // Add JSR-303 errors to BindingResult
		    // This allows Spring to display them in view via a FieldError
		    result.addError(new FieldError("xForm2", propertyPath, "Invalid "+ propertyPath + "(" + message + ")"));
        }
		 
        if (result.hasErrors()) 
        {
            return "xorder2Errors";
		}
		// Store the employee information in database
		// manager.createNewRecord(employeeVO);
		         
        //System.out.println(xForm);
        //System.out.println(xForm.getSelectedOptionPrint().getName());
        System.out.println(xForm.getSelectedOptionScan().getName());
        
        for(XExamWrapper e : xForm.getSelectedExams())
        {
        	if(e.isSelected())
        	{
        		System.out.println(e.getOrderExam().getExam().getId() + " - " + e.getOrderExam().getExamAmount());
        	}
        }
		 
		// Mark Session Complete
		status.setComplete();
		return "redirect:xorder2Success";
    }
	 
	 
	 @RequestMapping(value = "/xorder2Success", method = RequestMethod.GET)
	 public String success(Model model) 
	 {
        return "xorder2Success";
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
	