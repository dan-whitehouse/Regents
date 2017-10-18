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
public class ScanOptionController extends AbstractController {

	@Autowired
	OptionScanService optionScanService;

	/************************** SCAN OPTIONS **************************/
	@RequestMapping(value = { "/admin/scanOptions" }, method = RequestMethod.GET)
	public String listScanOptions(ModelMap model) {

		List<OptionScan> scanOptions = optionScanService.findAllOptionScans();
		model.addAttribute("scanOptions", scanOptions);
		return "scanOptions";
	}
		
	@RequestMapping(value = { "/admin/scanOptions/create" }, method = RequestMethod.GET)
	public String createScanOption(ModelMap model) 
	{
		OptionScan scanOption = new OptionScan();
		model.addAttribute("scanOption", scanOption);
		model.addAttribute("edit", false);
		return "createOrEditScanOption";
	}

	@RequestMapping(value = { "/admin/scanOptions/create" }, method = RequestMethod.POST)
	public String createScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}	
		
		optionScan.setUuid(UUID.randomUUID().toString());
		optionScan.setVisible(true);
		optionScan.setLocked(false);
		optionScanService.save(optionScan);
		
		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was created successfully.");
		model.addAttribute("returnLink", "/admin/scanOptions");
		model.addAttribute("returnLinkText", "Scan Options");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{uuid}/edit" }, method = RequestMethod.GET)
	public String editScanOption(@PathVariable String uuid, ModelMap model) 
	{
		OptionScan scanOption = optionScanService.findByUUID(uuid);
		
		if(!scanOption.getLocked())
		{
			model.addAttribute("scanOption", scanOption);
			model.addAttribute("edit", true);
			return "createOrEditScanOption";
		}
		else
		{
			model.addAttribute("error_message", "The scan option you are trying to edit is locked, please unlock it and try again.");
			return "403";	
		}
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateScanOption(@Valid OptionScan optionScan, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createOrEditScanOption";
		}

		optionScanService.update(optionScan);

		model.addAttribute("success", "Scan Option: " + optionScan.getName() + " was updated successfully.");
		model.addAttribute("returnLink", "/admin/scanOptions");
		model.addAttribute("returnLinkText", "Scan Options");
		return "success";
	}


	@RequestMapping(value = { "/admin/scanOptions/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteScanOption(@PathVariable String uuid, ModelMap model) 
	{
		OptionScan os = optionScanService.findByUUID(uuid);
		
		if(!os.getLocked())
		{
			optionScanService.deleteByUUID(uuid);
			return "redirect:/admin/scanOptions";
		}
		else 
		{
			model.addAttribute("error_message", "The scan option you are trying to delete is locked, please unlock it and try again.");
			return "403";
		}
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockScanOption(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		optionScanService.lockByOptionScanUUID(uuid, isLocked);
		return "redirect:/admin/scanOptions";
	}
	
	@RequestMapping(value = { "/admin/scanOptions/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideScanOption(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		optionScanService.hideByOptionScanUUID(uuid, isHidden);
		return "redirect:/admin/scanOptions";
	}
}