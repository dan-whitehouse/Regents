package org.neric.regents.controller;

import java.util.ArrayList;
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
import org.neric.regents.model.OrderDocument;
import org.neric.regents.model.OrderExam;
import org.neric.regents.model.OrderForm;
import org.neric.regents.model.User;
import org.neric.regents.model.UserProfile;
import org.neric.regents.model.XDocumentWrapper;
import org.neric.regents.model.XExamWrapper;
import org.neric.regents.model.XForm;
import org.neric.regents.model.XOptionPrintWrapper;
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
public class XController 
{

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
	
	//model populated by the method below
    //moved as we also need it populated on POST
    @RequestMapping(value = { "/xorder" }, method = RequestMethod.GET)
    public String order(ModelMap modelMap) 
    {
    	//only needed on GET so put in model here
        List<XOptionPrintWrapper> availablePrintOptions = new ArrayList<>();
        for(OptionPrint optionPrint : optionPrintService.findAllOptionPrints())
        {
        	availablePrintOptions.add(new XOptionPrintWrapper(optionPrint));
        }
        
        //Stuff shit in here, it will go to the  form
        modelMap.put("availablePrintOptions", availablePrintOptions);
        
        return "xorder"; 
    }

    //handles for submit
    //model attribute is automatically populated by the framework
    @RequestMapping(value = { "/xorder" }, method = RequestMethod.POST)
    public String order(@ModelAttribute("xForm") XForm orderForm) 
    {
        System.err.println("Exams: ");
        
        for(XExamWrapper w : orderForm.getAllAvailableExams())
        {
        	if(w.isSelected())
        	{
	        	System.err.println(w.getOrderExam().getExam().getName());
	        	System.err.println(w.getOrderExam().getExamAmount());
	        	System.err.println(w.getOrderExam().getAnswerSheetAmount());
        	}
        }
        
        System.err.println("Options: " + orderForm.getSelectedOptionPrint());

        return "xorder"; 
    }

    //on get populates the initial model for display
    //on post create an instance which the form params will be bound to
    @ModelAttribute("xForm")
    public XForm getOrderForm()
    {
        XForm xForm = new XForm();
        List<Exam> exams = examService.findAllExams();
        List<Document> documents = documentService.findAllDocuments();
        
        for(Exam exam : exams)
        {
            xForm.getAllAvailableExams().add(new XExamWrapper(new OrderExam(exam)));
        }
        
        for(Document document : documents)
        {
            xForm.getAllAvailableDocuments().add(new XDocumentWrapper(new OrderDocument(document)));
        }

        return xForm;
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