package org.neric.regents.controller;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class ErrorController {
    private static final String DEFAULT_ERROR_VIEW = "message/error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest httpRequest, Exception e) throws Exception {
        // If the exception is annotated with @ResponseStatus rethrow it and let
        // the framework handle it - like the OrderNotFoundException example at the start of this post.
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
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
    public ModelAndView constraintViolationExceptionHandler(HttpServletRequest httpRequest, ConstraintViolationException e) throws ConstraintViolationException {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
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

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ModelAndView dataIntegrityViolationExceptionHandler(HttpServletRequest httpRequest, DataIntegrityViolationException e) throws DataIntegrityViolationException {
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        //String value = StringUtils.substringBetween(e.getMessage(), "'");

        // Otherwise setup and send the user to a default error-view.
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorCode", "400");
        mav.addObject("errorMsg", "Bad Request");
        mav.addObject("exception", "The object you are trying to delete has a dependency. Mark as invisible to no longer display. ");
        mav.addObject("url", httpRequest.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        int code = 403;
        try {
            code = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
        }
        catch(Exception ignored) {
        }
        return code;
    }

    private String getErrorMessage(int httpErrorCode) {
        String errorMsg;
        switch(httpErrorCode) {
            case 400: {
                errorMsg = "Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Unauthorized";
                break;
            }
            case 403: {
                errorMsg = "Access Denied";
                break;
            }
            case 404: {
                errorMsg = "Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Internal Server Error";
                break;
            }
            default: {
                errorMsg = "Some other error...";
                break;
            }
        }
        return errorMsg;
    }
}