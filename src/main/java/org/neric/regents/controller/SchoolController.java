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
public class SchoolController extends AbstractController {

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
		school.setVisible(true);
		school.setLocked(false);
		
		schoolService.save(school);
		model.addAttribute("success", "School: " + school.getName() + " was created successfully.");
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

		model.addAttribute("success", "School: " + school.getName() + " was updated successfully.");
		model.addAttribute("returnLink", "/admin/schools");
		model.addAttribute("returnLinkText", "Schools");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/schools/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteSchool(@PathVariable String uuid, ModelMap model) 
	{
		School s = schoolService.findByUUID(uuid);
		
		if(!s.getLocked())
		{
			schoolService.deleteByUUID(uuid);
			return "redirect:/admin/schools";
		}
		else
		{
			model.addAttribute("error_message", "The school you are trying to delete is locked, please unlock it and try again.");
			return "403";
		}
	}
	
	@RequestMapping(value = { "/admin/schools/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockSchool(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		schoolService.lockByUUID(uuid, isLocked);
		return "redirect:/admin/schools";
	}
	
	@RequestMapping(value = { "/admin/schools/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideSchool(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		schoolService.hideByUUID(uuid, isHidden);
		return "redirect:/admin/schools";
	}
}