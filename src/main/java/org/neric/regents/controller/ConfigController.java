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
import org.neric.regents.model.Config;
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
import org.neric.regents.service.ConfigService;
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
import org.springframework.validation.ObjectError;
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
public class ConfigController extends AbstractController {

	@Autowired
	ConfigService configService;
	
	/************************** Configurable Data **************************/
	
	@RequestMapping(value = { "/admin/config" }, method = RequestMethod.GET)
	public String listDistricts(ModelMap model) {

		List<Config> configurables = configService.findAll();
		model.addAttribute("configurables", configurables);
		return "config/config";
	}
	
	@RequestMapping(value = { "/admin/config/{uuid}/edit" }, method = RequestMethod.GET)
	public String orderFormInfo(@PathVariable String uuid, ModelMap model) {
		Config config = configService.findByUUID(uuid);
		model.addAttribute("config", config);
		return "config/configEdit";
	}
	
	
	@RequestMapping(value = { "/admin/config/{uuid}/edit" }, method = RequestMethod.POST)
	public String createOrderForm(@Valid Config config, @PathVariable String uuid, BindingResult result, ModelMap model) 
	{
		if (result.hasErrors()) 
		{
			for(ObjectError error : result.getAllErrors()) {
				System.out.println(error.getDefaultMessage());
			}
			return "config/configEdit";
		}
			
		Config updatedConfig = configService.findByUUID(uuid);
		updatedConfig.setId(config.getId());
		updatedConfig.setUuid(config.getUuid());
		updatedConfig.setHref(config.getHref());
		updatedConfig.setDescription(config.getDescription());
		updatedConfig.setData(config.getData());
		configService.updateConfig(updatedConfig);
		
		model.addAttribute("success", "Configurable: " + config.getId() + " was created successfully.");
		model.addAttribute("returnLink", "/admin/config");
		model.addAttribute("returnLinkText", "Configurables");
		return "message/success";
	}
}