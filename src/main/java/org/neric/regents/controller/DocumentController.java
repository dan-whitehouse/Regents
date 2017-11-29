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
public class DocumentController extends AbstractController {

	@Autowired
	DocumentService documentService;

	/************************** DOCUMENTS **************************/
	@RequestMapping(value = { "/admin/documents" }, method = RequestMethod.GET)
	public String listDocuments(ModelMap model) {

		List<Document> documents = documentService.findAllDocuments();
		model.addAttribute("documents", documents);
		return "document/documents";
	}
		
	@RequestMapping(value = { "/admin/documents/create" }, method = RequestMethod.GET)
	public String createDocument(ModelMap model) 
	{
		Document document = new Document();
		model.addAttribute("document", document);
		model.addAttribute("edit", false);
		return "document/createOrEditDocument";
	}

	@RequestMapping(value = { "/admin/documents/create" }, method = RequestMethod.POST)
	public String createDocument(@Valid Document document, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			return "document/createOrEditDocument";
		}
		document.setUuid(UUID.randomUUID().toString());
		document.setVisible(true);
		document.setLocked(false);
		documentService.saveDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was created successfully.");
		model.addAttribute("returnLink", "/admin/documents");
		model.addAttribute("returnLinkText", "Documents");
		return "message/success";
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/edit" }, method = RequestMethod.GET)
	public String editDocument(@PathVariable String uuid, ModelMap model) 
	{
		Document document = documentService.findByUUID(uuid);
		
		if(!document.getLocked())
		{
			model.addAttribute("document", document);
			model.addAttribute("edit", true);
			return "document/createOrEditDocument";
		}
		else
		{
			model.addAttribute("error_message", "The document you are trying to edit is locked, please unlock it and try again.");
			return "message/403";
		}
		
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateDocument(@Valid Document document, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "document/createOrEditDocument";
		}

		documentService.updateDocument(document);

		model.addAttribute("success", "Document: " + document.getName() + " was updated successfully.");
		model.addAttribute("returnLink", "/admin/documents");
		model.addAttribute("returnLinkText", "Documents");
		return "message/success";
	}


	@RequestMapping(value = { "/admin/documents/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable String uuid, ModelMap model) 
	{
		Document document = documentService.findByUUID(uuid);
		if(!document.getLocked())
		{
			documentService.deleteByDocumentUUID(uuid);
			return "redirect:/admin/documents";
		}
		else
		{
			model.addAttribute("error_message", "The document you are trying to delete is locked, please unlock it and try again.");
			return "message/403";	
		}
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockDocument(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		documentService.lockByDocumentUUID(uuid, isLocked);
		return "redirect:/admin/documents";
	}
	
	@RequestMapping(value = { "/admin/documents/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideDocument(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		documentService.hideByDocumentUUID(uuid, isHidden);
		return "redirect:/admin/documents";
	}

}