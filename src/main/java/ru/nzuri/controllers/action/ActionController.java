/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.action;

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
public class ActionController {

    @Inject
    private ActionService actionService;

    @Inject
    private SpecializationService specializationService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/action/new/{id}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        Specialization specialization = specializationService.get(id);
        if (specialization != null) {
            mav.addObject("action", actionService.createEmptyEntity());
            mav.addObject("specialization", specialization);
            mav.setViewName("action/create");
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Специализация не найдена"));
            mav.setViewName("specialization/actions");
        }
        return mav;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/action/save/{id}", method = RequestMethod.POST)
    public String save(@ModelAttribute("action") Action action, @PathVariable Long id, final RedirectAttributes redirectAttributes) {
        if (action != null && id != null) {
            Specialization specialization = specializationService.get(id);
            if (specialization != null) {
                action.setSpecialization(specialization);
                actionService.store(action);
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Сохранение выполнено успешно"));
                return "redirect:/specialization/" + action.getSpecialization().getId()+"/actions";
            } else {
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Специализация не найдена"));
                return "";
            }
        } else {
            return "";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/action/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Action action = actionService.get(id);
        mav.addObject("action", action);
        mav.setViewName("action/edit");
        return mav;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/action/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("action") Action action, Long specializationId, final RedirectAttributes redirectAttributes) {
        if (action != null) {
            actionService.merge(action);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Сохранение выполнено успешно"));
            return "redirect:/specialization/" + specializationId +"/actions";
        }
        return "";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/action/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable Long id, Long specializationId,final RedirectAttributes redirectAttributes) {
        if(id != null) {
            actionService.remove(id);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Удаление выполнено успешно"));
            return "redirect:/specialization/" + specializationId +"/actions";
        } else {
            return "";
        }
    }

}
