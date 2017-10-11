package org.neric.regents.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ErrorController 
{
	public static final String DEFAULT_ERROR_VIEW = "error";
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest httpRequest, Exception e) throws Exception 
	{
	    // If the exception is annotated with @ResponseStatus rethrow it and let
	    // the framework handle it - like the OrderNotFoundException example
	    // at the start of this post.
	    // AnnotationUtils is a Spring Framework utility class.
	    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
	    {
	    	throw e;
	    }

	    int httpErrorCode = getErrorCode(httpRequest);
	    String errorMessage = getErrorMessage(httpErrorCode);
	    
	    // Otherwise setup and send the user to a default error-view.
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("errorCode", httpErrorCode);
	    mav.addObject("errorMsg", errorMessage);
	    mav.addObject("exception", e);
	    mav.addObject("url", httpRequest.getRequestURL());
	    mav.setViewName(DEFAULT_ERROR_VIEW);
	    return mav;
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ModelAndView constraintViolationExceptionHandler(HttpServletRequest httpRequest, ConstraintViolationException e) throws ConstraintViolationException 
	{
	    if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
	    {
	    	throw e;
	    }

	    String value = StringUtils.substringBetween(e.getSQLException().getMessage(), "'");
	    
	    // Otherwise setup and send the user to a default error-view.
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("errorCode", "400");
	    mav.addObject("errorMsg", "Bad Request");
	    mav.addObject("exception", "Could not insert record, key constraint violation was detected on: " + value);
	    mav.addObject("url", httpRequest.getRequestURL());
	    mav.setViewName(DEFAULT_ERROR_VIEW);
	    return mav;
	}
	
	/*******************/
	
  private int getErrorCode(HttpServletRequest httpRequest) 
  {
	  int code = 404;
	  
	  try
	  {
		  code = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	  }
	  catch(Exception e) {}
	  
	  return code; 
}
	
	
	public String getErrorMessage(int httpErrorCode)
	{
		String errorMsg = "";
		switch (httpErrorCode)
		{
			case 400:
			{
				errorMsg = "Http Error Code: 400. Bad Request";
				break;
			}
			case 401:
			{
				errorMsg = "Http Error Code: 401. Unauthorized";
				break;
			}
			case 404:
			{
				errorMsg = "Http Error Code: 404. Resource not found";
				break;
			}
			case 500:
			{
				errorMsg = "Http Error Code: 500. Internal Server Error";
				break;
			}
			default:
			{
				errorMsg = "Some other error...";
				break;
			}
		}
		return errorMsg;
	}
	
	
	
	
//	@ExceptionHandler(Exception.class)
//    public String handle(Exception ex) 
//	{
//        return "redirect:/error";
//    }
//
//    @RequestMapping(value = {"/error"}, method = RequestMethod.GET)
//    public String NotFoudPage(ModelMap model, HttpServletRequest httpRequest) 
//    {    	
//    	  String errorMsg = "";
//        int httpErrorCode = getErrorCode(httpRequest);
// 
//        switch (httpErrorCode) {
//            case 400: {
//                errorMsg = "Http Error Code: 400. Bad Request";
//                break;
//            }
//            case 401: {
//                errorMsg = "Http Error Code: 401. Unauthorized";
//                break;
//            }
//            case 404: {
//                errorMsg = "Http Error Code: 404. Resource not found";
//                break;
//            }
//            case 500: {
//                errorMsg = "Http Error Code: 500. Internal Server Error";
//                break;
//            }
//        }
//        model.addAttribute("errorMsg", errorMsg);
//        return "error";
//    }
//    
//    private int getErrorCode(HttpServletRequest httpRequest) {
//        return (Integer) httpRequest
//          .getAttribute("javax.servlet.error.status_code");
//    }

    /***********************/
	
//    @ExceptionHandler(Exception.class)
//    public String handleException(Exception e) 
//    {
////    	String errorMsg = "";
////        
////    	System.err.println(e.getClass().toString());
////    	
////        
////        switch (e.getClass().toString()) {
////          case "400": {
////              errorMsg = "Http Error Code: 400. Bad Request";
////              break;
////          }
////          case "401": {
////              errorMsg = "Http Error Code: 401. Unauthorized";
////              break;
////          }
////          case "org.springframework.web.servlet.NoHandlerFoundException": {
////              errorMsg = "Http Error Code: 404. Resource not found";
////              break;
////          }
////          case "500": {
////              errorMsg = "Http Error Code: 500. Internal Server Error";
////              break;
////          }
////      }
//    	
//    	//model.addAttribute("errorMsg", errorMsg);
//        return "error";// view name for 404 error
//    }   
    
   

}