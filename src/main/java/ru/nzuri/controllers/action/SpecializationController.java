/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.action;

import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nzuri.controllers.message.Message;
import ru.nzuri.controllers.message.MessageType;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.services.action.ActionService;
import ru.nzuri.services.action.SpecializationService;

/**
 *
 * @author bad
 */
@Controller
public class SpecializationController {
    
    @Inject
    private SpecializationService specializationService;
    
    @Inject
    private ActionService actionService;
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/actions/{id}", method = RequestMethod.GET)
    public ModelAndView actions(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Specialization specialization = specializationService.get(id);
        List<Action> actions = Collections.emptyList();
        if(specialization != null) {
            actions = actionService.getAll(specialization);
            mav.addObject("specialization", specialization);
        }
        mav.addObject("actions", actions);
        mav.setViewName("specialization/actions");
        return mav;
    }
    
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
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Сохранение выполнено успешно"));
            return "redirect:/specializations";
        } else {
            return "";
        }
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/specialization/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("specialization") Specialization specialization,final RedirectAttributes redirectAttributes) {
        if(specialization != null) {
            specializationService.merge(specialization);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Сохранение выполнено успешно"));
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
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Удаление выполнено успешно"));
            return "redirect:/specializations";
        } else {
            return "";
        }
    }
    
}
