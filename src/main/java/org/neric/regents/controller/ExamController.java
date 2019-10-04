package org.neric.regents.controller;

import org.neric.regents.model.Exam;
import org.neric.regents.service.ExamService;
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
public class ExamController extends AbstractController {

    @Autowired
    ExamService examService;

    /************************** EXAMS **************************/
    @RequestMapping(value = {"/admin/exams"}, method = RequestMethod.GET)
    public String listExams(ModelMap model) {

        List<Exam> exams = examService.findAllExams();
        model.addAttribute("exams", exams);
        return "exam/exams";
    }

    @RequestMapping(value = {"/admin/exams/create"}, method = RequestMethod.GET)
    public String createExam(ModelMap model) {
        Exam exam = new Exam();
        model.addAttribute("exam", exam);
        model.addAttribute("edit", false);
        return "exam/createOrEditExam";
    }

    @RequestMapping(value = {"/admin/exams/create"}, method = RequestMethod.POST)
    public String createExam(@Valid Exam exam, BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return "exam/createOrEditExam";
        }
        exam.setUuid(UUID.randomUUID().toString());
        exam.setVisible(true);
        exam.setLocked(false);
        examService.saveExam(exam);

        model.addAttribute("success", "Exam: " + exam.getName() + " was created successfully.");
        model.addAttribute("returnLink", "/admin/exams");
        model.addAttribute("returnLinkText", "Exams");
        return "message/success";
    }

    @RequestMapping(value = {"/admin/exams/{uuid}/edit"}, method = RequestMethod.GET)
    public String editExam(@PathVariable String uuid, ModelMap model) {
        Exam exam = examService.findByUUID(uuid);

        if(!exam.getLocked()) {
            model.addAttribute("exam", exam);
            model.addAttribute("edit", true);
            return "exam/createOrEditExam";
        }
        else {
            model.addAttribute("error_message", "The exam you are trying to edit is locked, please unlock it and try again.");
            return "message/403";
        }
    }

    @RequestMapping(value = {"/admin/exams/{uuid}/edit"}, method = RequestMethod.POST)
    public String editExam(@Valid Exam exam, BindingResult result, ModelMap model, @PathVariable String uuid) {
        if(result.hasErrors()) {
            return "exam/createOrEditExam";
        }

        examService.updateExam(exam);

        model.addAttribute("success", "Exam: " + exam.getName() + " - " + exam.getCode() + " was updated successfully.");
        model.addAttribute("returnLink", "/admin/exams");
        model.addAttribute("returnLinkText", "Exams");
        return "message/success";
    }


    @RequestMapping(value = {"/admin/exams/{uuid}/delete"}, method = RequestMethod.GET)
    public String deleteExam(@PathVariable String uuid, ModelMap model) {
        Exam exam = examService.findByUUID(uuid);
        if(!exam.getLocked()) {
            examService.deleteByExamUUID(uuid);
            return "redirect:/admin/exams";
        }
        else {
            model.addAttribute("error_message", "The exam you are trying to delete is locked, please unlock it and try again.");
            return "message/403";
        }
    }

    @RequestMapping(value = {"/admin/exams/{uuid}/lock/{isLocked}"}, method = RequestMethod.GET)
    public String lockExam(@PathVariable String uuid, @PathVariable boolean isLocked) {
        examService.lockByExamUUID(uuid, isLocked);
        return "redirect:/admin/exams";
    }

    @RequestMapping(value = {"/admin/exams/{uuid}/hide/{isHidden}"}, method = RequestMethod.GET)
    public String hideExam(@PathVariable String uuid, @PathVariable boolean isHidden) {
        examService.hideByExamUUID(uuid, isHidden);
        return "redirect:/admin/exams";
    }
}