package org.neric.regents.controller;

import org.neric.regents.controller.AbstractController;
import org.neric.regents.converture.DistrictEditor;
import org.neric.regents.converture.SchoolEditor;
import org.neric.regents.model.*;
import org.neric.regents.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class DistrictController extends AbstractController {

	@Autowired
	DistrictService districtService;

	@Autowired
	SchoolService schoolService;

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
	}


	/************************** DISTRICTS **************************/
	@RequestMapping(value = { "/admin/districts" }, method = RequestMethod.GET)
	public String listDistricts(ModelMap model) {

		List<District> districts = districtService.findAllDistricts();
		model.addAttribute("districts", districts);
		return "district/districts";
	}
		
	@RequestMapping(value = { "/admin/districts/create" }, method = RequestMethod.GET)
	public String createDistrict(ModelMap model) 
	{
		District district = new District();
		
		model.addAttribute("district", district);
		model.addAttribute("edit", false);
		return "district/createOrEditDistrict";
	}

	@RequestMapping(value = { "/admin/districts/create" }, method = RequestMethod.POST)
	public String createDistrict(@Valid District district, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "district/createOrEditDistrict";
		}	
		
		district.setUuid(UUID.randomUUID().toString());
		district.setVisible(true);
		district.setLocked(false);
		districtService.saveDistrict(district);
		
		
		model.addAttribute("success", "District: " + district.getName() + " was created successfully");
		model.addAttribute("returnLink", "/admin/districts");
		model.addAttribute("returnLinkText", "Districts");
		return "message/success";
	}

	@RequestMapping(value = { "/admin/districts/{uuid}" }, method = RequestMethod.GET)
	public String getDistrict(@PathVariable String uuid, ModelMap model)
	{
		District district = districtService.findByUUID(uuid);
		model.addAttribute("district", district);
		return "district/district";
	}

	
	@RequestMapping(value = { "/admin/districts/{uuid}/edit" }, method = RequestMethod.GET)
	public String editDistrict(@PathVariable String uuid, ModelMap model) 
	{
		District district = districtService.findByUUID(uuid);
		
		if(!district.getLocked())
		{
			model.addAttribute("district", district);
			model.addAttribute("edit", true);
			return "district/createOrEditDistrict";
		}
		else 
		{
			model.addAttribute("error_message", "The district you are trying to edit is locked, please unlock it and try again");
			return "message/403";
		}
	}
	
	@RequestMapping(value = { "/admin/districts/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateDistrict(@Valid District district, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "district/createOrEditDistrict";
		}
		districtService.updateDistrict(district);

		model.addAttribute("success", "District: " + district.getName() + " was updated successfully");
		model.addAttribute("returnLink", "/admin/districts");
		model.addAttribute("returnLinkText", "Districts");
		return "message/success";
	}
	
	@RequestMapping(value = { "/admin/districts/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteDistrict(@PathVariable String uuid, ModelMap model) 
	{
		District d = districtService.findByUUID(uuid);
		if(!d.getLocked())
		{
			districtService.deleteByUUID(uuid);
			return "redirect:/admin/districts";
		}
		else 
		{
			model.addAttribute("error_message", "The district you are trying to delete is locked, please unlock it and try again");
			return "message/403";	
		}
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
}