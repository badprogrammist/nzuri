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
import ru.nzuri.controllers.message.Message;
import ru.nzuri.controllers.message.MessageType;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.services.service.ServiceService;
import ru.nzuri.services.service.SpecializationService;

/**
 *
 * @author bad
 */
@Controller
public class ServiceController {

    @Inject
    private ServiceService serviceService;

    @Inject
    private SpecializationService specializationService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/service/new/{id}", method = RequestMethod.GET)
    public ModelAndView create(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        Specialization specialization = specializationService.get(id);
        if (specialization != null) {
            mav.addObject("service", serviceService.createEmptyEntity());
            mav.addObject("specialization", specialization);
            mav.setViewName("service/create");
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Специализация не найдена"));
            mav.setViewName("specialization/services");
        }
        return mav;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/service/save/{id}", method = RequestMethod.POST)
    public String save(@ModelAttribute("service") Service service, @PathVariable Long id, final RedirectAttributes redirectAttributes) {
        if (service != null && id != null) {
            Specialization specialization = specializationService.get(id);
            if (specialization != null) {
                service.setSpecialization(specialization);
                serviceService.store(service);
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Сохранение выполнено успешно"));
                return "redirect:/specialization/services/" + service.getSpecialization().getId();
            } else {
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Специализация не найдена"));
                return "";
            }
        } else {
            return "";
        }
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/service/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        Service service = serviceService.get(id);
        mav.addObject("service", service);
        mav.setViewName("service/edit");
        return mav;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/service/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("service") Service service, Long specializationId, final RedirectAttributes redirectAttributes) {
        if (service != null) {
            serviceService.update(service);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Сохранение выполнено успешно"));
            return "redirect:/specialization/services/" + specializationId;
        }
        return "";
    }
    
    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/service/remove/{id}", method = RequestMethod.POST)
    public String remove(@PathVariable Long id, Long specializationId,final RedirectAttributes redirectAttributes) {
        if(id != null) {
            serviceService.remove(id);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Удаление выполнено успешно"));
            return "redirect:/specialization/services/" + specializationId;
        } else {
            return "";
        }
    }

}
