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
import org.neric.regents.controller.AbstractController;
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
import org.neric.regents.model.SelectedDistrict;
import org.neric.regents.model.SelectedDocument;
import org.neric.regents.model.SelectedExam;
import org.neric.regents.model.User;
import org.neric.regents.model.UserDistrict;
import org.neric.regents.model.UserProfile;
import org.neric.regents.service.DistrictService;
import org.neric.regents.service.DocumentService;
import org.neric.regents.service.ExamService;
import org.neric.regents.service.OptionPrintService;
import org.neric.regents.service.OptionScanService;
import org.neric.regents.service.OrderFormService;
import org.neric.regents.service.OrderService;
import org.neric.regents.service.SchoolService;
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
public class OrderFormController extends AbstractController {

	@Autowired
	ExamService examService;
	
	@Autowired
	DocumentService documentService;

	@Autowired
	DistrictService districtService;

	@Autowired
	OrderFormService orderFormService;

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
		
		model.addAttribute("success", "OrderForm: " + orderForm.getName() + " was created successfully.");
		model.addAttribute("returnLink", "/admin/orderForms");
		model.addAttribute("returnLinkText", "Order Forms");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/edit" }, method = RequestMethod.GET)
	public String editOrderForm(@PathVariable String uuid, ModelMap model) 
	{
		OrderForm orderForm = orderFormService.findByUUID(uuid);
		
		if(!orderForm.getLocked())
		{
			//Get Exams & Documents from DB
			List<Exam> exams = examService.findAllExams();
			List<Document> documents = documentService.findAllDocuments();		
			
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
		else
		{
			model.addAttribute("error_message", "The order form you are trying to edit is locked, please unlock it and try again.");
			return "403";	
		}
	}
	
	
	@RequestMapping(value = { "/admin/orderForms/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateOrderForm(@Valid OrderForm orderForm, @PathVariable String uuid, BindingResult result, ModelMap model)
	{
		if (result.hasErrors()) 
		{
			return "createOrEditOrderForm";
		}
		
		OrderForm orderFormUpdate = orderFormService.findByUUID(uuid);
		orderFormUpdate.setName(orderForm.getName());
		orderFormUpdate.setPeriod(orderForm.getPeriod());
		orderFormUpdate.setStartDate(orderForm.getStartDate());
		orderFormUpdate.setEndDate(orderForm.getEndDate());
		orderFormUpdate.setNonSecureDocumentFee(orderForm.getNonSecureDocumentFee());
		orderFormUpdate.setInDistrictScanFee(orderForm.getInDistrictScanFee());
		orderFormUpdate.setProcessingFee(orderForm.getProcessingFee());
		orderFormUpdate.setRescanFee(orderForm.getRescanFee());
		orderFormUpdate.getOrderFormDocuments().clear();
		orderFormUpdate.getOrderFormExams().clear();
		
		for(OrderFormExam e : orderForm.getOrderFormExams())
		{
			e.setOrderForm(orderForm);
			orderFormUpdate.getOrderFormExams().add(e);
		}
		
		for(OrderFormDocument d : orderForm.getOrderFormDocuments())
		{
			d.setOrderForm(orderForm);
			orderFormUpdate.getOrderFormDocuments().add(d);
		}
		
		orderFormService.updateOrderForm(orderFormUpdate);
		
		model.addAttribute("success", "OrderForm: " + orderForm.getName() + " was updated successfully.");
		model.addAttribute("returnLink", "/admin/orderForms");
		model.addAttribute("returnLinkText", "Order Forms");

		return "success";
	}
	
	@RequestMapping(value = { "admin/orderForms/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteOrderForm(@PathVariable String uuid, ModelMap model)
	{	
		OrderForm o = orderFormService.findByUUID(uuid);	
		if(!o.getLocked())
		{
			orderFormService.deleteOrderForm(uuid);
			return "redirect:/admin/orderForms";
		}
		else
		{
			model.addAttribute("error_message", "The order form you are trying to delete is locked, please unlock it and try again.");
			return "403";	
		}
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
	

	/************************** OTHER **************************/

	private boolean hasDistrict(Set<UserDistrict> set, District district)
	{
		for(UserDistrict d : set)
		{
			if(d.getDistrict().getId() == district.getId())
			{
				return true;
			}
		}
		return false;
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

}