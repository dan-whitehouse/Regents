package org.neric.regents.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.neric.regents.converture.OptionPrintEditor;
import org.neric.regents.model.OptionPrint;
import org.neric.regents.model.XForm2;
import org.neric.regents.service.OptionPrintService;
import org.springframework.beans.factory.annotation.Autowired;
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
	OptionPrintService optionPrintService;
	
	@Autowired
	OptionPrintEditor optionPrintEditor;
	
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(OptionPrint.class, optionPrintEditor);
    }

	@ModelAttribute("allPrintOptions")
    public List<OptionPrint> populatePrintOptions() 
    {
        List<OptionPrint> options = optionPrintService.findAllOptionPrints();
        return options;
    }
	
	
//	@ModelAttribute("allPrintOptions")
//    public List<OptionPrint> populatePrintOptions() 
//    {
//        ArrayList<OptionPrint> options = new ArrayList<OptionPrint>();
//        options.add(new OptionPrint(-1,  "Select Department"));
//        options.add(new OptionPrint(1,  "Human Resource"));
//        options.add(new OptionPrint(2,  "Finance"));
//        options.add(new OptionPrint(3,  "Information Technology"));
//        return options;
//    }
	
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
		         
        System.out.println(xForm);
		 
		// Mark Session Complete
		status.setComplete();
		return "redirect:xorder2Success";
    }
	 
	 
	 @RequestMapping(value = "/xorder2Success", method = RequestMethod.GET)
	 public String success(Model model) 
	 {
        return "xorder2Success";
	 }
}
	