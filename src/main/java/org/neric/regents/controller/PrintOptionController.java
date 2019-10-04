package org.neric.regents.controller;

import org.neric.regents.model.OptionPrint;
import org.neric.regents.service.OptionPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class PrintOptionController extends AbstractController {

    @Autowired
    OptionPrintService optionPrintService;

    /************************** PRINT OPTIONS **************************/
    @RequestMapping(value = {"/admin/printOptions"}, method = RequestMethod.GET)
    public String listPrintOptions(ModelMap model) {

        List<OptionPrint> printOptions = optionPrintService.findAllOptionPrints();
        model.addAttribute("printOptions", printOptions);
        return "printoption/printOptions";
    }

    @RequestMapping(value = {"/admin/printOptions/create"}, method = RequestMethod.GET)
    public String createPrintOption(ModelMap model) {
        OptionPrint printOption = new OptionPrint();
        model.addAttribute("printOption", printOption);
        model.addAttribute("edit", false);
        return "printoption/createOrEditPrintOption";
    }

    @RequestMapping(value = {"/admin/printOptions/create"}, method = RequestMethod.POST)
    public String createPrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return "printoption/createOrEditPrintOption";
        }

        optionPrint.setUuid(UUID.randomUUID().toString());
        optionPrint.setVisible(true);
        optionPrint.setLocked(false);
        optionPrintService.save(optionPrint);

        model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was created successfully.");
        model.addAttribute("returnLink", "/admin/printOptions");
        model.addAttribute("returnLinkText", "Print Options");
        return "message/success";
    }

    @RequestMapping(value = {"/admin/printOptions/{uuid}/edit"}, method = RequestMethod.GET)
    public String editPrintOption(@PathVariable String uuid, ModelMap model) {
        OptionPrint printOption = optionPrintService.findByUUID(uuid);

        if(!printOption.getLocked()) {
            model.addAttribute("printOption", printOption);
            model.addAttribute("edit", true);
            return "printoption/createOrEditPrintOption";
        }
        else {
            model.addAttribute("error_message", "The print option you are trying to edit is locked, please unlock it and try again.");
            return "message/403";
        }

    }

    @RequestMapping(value = {"/admin/printOptions/{uuid}/edit"}, method = RequestMethod.POST)
    public String updatePrintOption(@Valid OptionPrint optionPrint, BindingResult result, ModelMap model, @PathVariable String uuid) {
        if(result.hasErrors()) {
            return "printoption/createOrEditPrintOption";
        }

        optionPrintService.update(optionPrint);

        model.addAttribute("success", "Print Option: " + optionPrint.getName() + " was updated successfully.");
        model.addAttribute("returnLink", "/admin/printOptions");
        model.addAttribute("returnLinkText", "Print Options");
        return "message/success";
    }


    @RequestMapping(value = {"/admin/printOptions/{uuid}/delete"}, method = RequestMethod.GET)
    public String deletePrintOption(@PathVariable String uuid, ModelMap model) {
        OptionPrint op = optionPrintService.findByUUID(uuid);

        if(!op.getLocked()) {
            optionPrintService.deleteByUUID(uuid);
            return "redirect:/admin/printOptions";
        }
        else {
            model.addAttribute("error_message", "The print option you are trying to delete is locked, please unlock it and try again.");
            return "message/403";
        }
    }

    @RequestMapping(value = {"/admin/printOptions/{uuid}/lock/{isLocked}"}, method = RequestMethod.GET)
    public String lockPrintOption(@PathVariable String uuid, @PathVariable boolean isLocked) {
        optionPrintService.lockByOptionPrintUUID(uuid, isLocked);
        return "redirect:/admin/printOptions";
    }

    @RequestMapping(value = {"/admin/printOptions/{uuid}/hide/{isHidden}"}, method = RequestMethod.GET)
    public String hidePrintOption(@PathVariable String uuid, @PathVariable boolean isHidden) {
        optionPrintService.hideByOptionPrintUUID(uuid, isHidden);
        return "redirect:/admin/printOptions";
    }
}