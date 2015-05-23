/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.service;

import javax.inject.Inject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.services.service.SpecializationService;

/**
 *
 * @author bad
 */
@Controller
public class SpecializationController {
    
    @Inject
    private SpecializationService specializationService;
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specializations", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("specializations", specializationService.getAll());
        mav.setViewName("specialization/list");
        return mav;
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/new", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("specialization", specializationService.createEmptyEntity());
        mav.setViewName("specialization/create");
        return mav;
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("specialization", specializationService.get(id));
        mav.setViewName("specialization/edit");
        return mav;
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("specialization") Specialization specialization,final RedirectAttributes redirectAttributes) {
        if(specialization != null) {
            specializationService.store(specialization);
            redirectAttributes.addFlashAttribute("message", "Сохранение выполнено успешно");
            return "redirect:/specializations";
        } else {
            return "";
        }
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("specialization") Specialization specialization,final RedirectAttributes redirectAttributes) {
        if(specialization != null) {
            specializationService.update(specialization);
            redirectAttributes.addFlashAttribute("message", "Сохранение выполнено успешно");
            return "redirect:/specializations";
        } else {
            return "";
        }
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable Long id,final RedirectAttributes redirectAttributes) {
        if(id != null) {
            specializationService.remove(id);
            redirectAttributes.addFlashAttribute("message", "Удаление выполнено успешно");
            return "redirect:/specializations";
        } else {
            return "";
        }
    }
    
}
