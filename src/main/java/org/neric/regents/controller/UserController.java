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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class UserController extends AbstractController {

	@Autowired
	DistrictService districtService;

	@Autowired
	OptOutService optOutService;

	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;

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


	/************************** USERS **************************/
	@RequestMapping(value = { "/admin/users" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) 
	{
		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@RequestMapping(value = { "/admin/users/{uuid}" }, method = RequestMethod.GET)
	public String showUser(@PathVariable String uuid, ModelMap model) {

		User user = userService.findByUUID(uuid);
		List<OptOut> optOuts =  optOutService.findAllOptOutsByUserUUID(uuid);

		model.addAttribute("user", user);
		model.addAttribute("optOuts", optOuts);

		model.addAttribute("edit", false);
		model.addAttribute("loggedinusername", getPrincipal());
		return "user";
	}

	@RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
	public String profile(ModelMap model) {

		User user = loggedInUser();
		List<OptOut> optOuts =  optOutService.findAllOptOutsByUserUUID(user.getUuid());

		model.addAttribute("user", user);
		model.addAttribute("optOuts", optOuts);

		model.addAttribute("edit", false);
		model.addAttribute("loggedinusername", getPrincipal());
		return "user";
	}
	
	@RequestMapping(value = { "admin/users/create" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) 
	{
		User user = new User();
		List<District> districts = districtService.findAllDistricts();
		model.addAttribute("user", user);
		model.addAttribute("districts", districts);
		model.addAttribute("edit", false);
		return "createUser";
	}
	
	@RequestMapping(value = { "admin/users/create" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			for(ObjectError objectError : result.getAllErrors())
			{
				System.err.println(objectError.getDefaultMessage());
			}
			return "createUser";
		}

		if(!userService.isUserUsernameUnique(user.getId(), user.getUsername()))
		{
			FieldError usernameError =new FieldError("user","username",messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
		    result.addError(usernameError);
			return "createUser";
		}
		
		user.setLocked(false);
		user.setVisible(true);
		user.setUuid(UUID.randomUUID().toString());
		
		
		for(UserDistrict ud : user.getUserDistricts())
		{
			ud.setUser(user);
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User: " + user.getUsername() + " was registered successfully.");
		model.addAttribute("returnLink", "/admin/users");
		model.addAttribute("returnLinkText", "Users");
		return "success";
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/edit" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String uuid, ModelMap model) 
	{
		User user = userService.findByUUID(uuid);
			
		if(!user.getLocked())
		{
			List<District> districts = districtService.findAllDistricts();
			List<SelectedDistrict> selectedDistricts = new ArrayList<>();
			
			for(District d : districts)
			{
				SelectedDistrict sd = new SelectedDistrict();
				sd.setDistrict(d);
				sd.setSelected(false);
				
				if(hasDistrict(user.getUserDistricts(), d))
				{
					sd.setSelected(true);
				}
				
				selectedDistricts.add(sd);
			}
			
			model.addAttribute("user", user);
			model.addAttribute("districts", districts);
			model.addAttribute("selectedDistricts", selectedDistricts);
			model.addAttribute("edit", true);
			return "createUser";
		}
		else
		{
			model.addAttribute("error_message", "The user you are trying to edit is locked, please unlock it and try again.");
			return "403";
		}
	}
	
	
	@RequestMapping(value = { "/admin/users/{uuid}/edit" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String uuid) 
	{
		if (result.hasErrors()) 
		{
			return "createUser";
		}
		
		User updatedUser = userService.findByUUID(uuid);
		updatedUser.getUserDistricts().clear();
		
		for(UserDistrict ud : user.getUserDistricts())
		{
			ud.setUser(user);
			updatedUser.getUserDistricts().add(ud);
		}
		
		userService.updateUser(updatedUser);

		model.addAttribute("success", "User: " + user.getUsername() +  " was updated successfully.");
		model.addAttribute("returnLink", "/admin/users");
		model.addAttribute("returnLinkText", "Users");
		return "success";
	}
	
	
	@RequestMapping(value = { "/admin/users/{uuid}/delete" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String uuid, ModelMap model) 
	{	
		User user = userService.findByUUID(uuid);	
		if(!user.getLocked())
		{
			userService.deleteUserByUUID(uuid);
			return "redirect:/admin/users";
		}
		else
		{
			model.addAttribute("error_message", "The user you are trying to delete is locked, please unlock it and try again.");
			return "403";	
		}
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/lock/{isLocked}" }, method = RequestMethod.GET)
	public String lockUser(@PathVariable String uuid, @PathVariable boolean isLocked) 
	{
		userService.lockByUUID(uuid, isLocked);
		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = { "/admin/users/{uuid}/hide/{isHidden}" }, method = RequestMethod.GET)
	public String hideUser(@PathVariable String uuid, @PathVariable boolean isHidden) 
	{
		userService.hideByUUID(uuid, isHidden);
		return "redirect:/admin/users";
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