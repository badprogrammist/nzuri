/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.master;

import java.util.ArrayList;
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
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.master.MasterSpecialization;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.action.ActionService;
import ru.nzuri.services.action.SpecializationService;
import ru.nzuri.services.master.MasterActionService;
import ru.nzuri.services.master.MasterService;

/**
 *
 * @author bad
 */
@Controller
public class MasterActionController {

    @Inject
    private MasterService masterService;

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ActionService actionService;

    @Inject
    private SpecializationService specializationService;

    @Inject
    private MasterActionService masterActionService;

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/actions", method = RequestMethod.GET)
    public ModelAndView editActions() {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if (master != null) {
            List<MasterSpecialization> masterSpecializations = master.getSpecializations();
            model.addObject("master", master);
            model.addObject("masterSpecializations", masterSpecializations);
            model.addObject("specializations", masterActionService.getAttachCandidates(master));
            model.setViewName("master/edit/actions");
        } else {
            model.setViewName("404");
        }
        return model;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/action/update", method = RequestMethod.POST)
    public String updateService(@ModelAttribute("masterAction") MasterAction masterAction, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if (master != null) {
            masterActionService.merge(masterAction);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуга успешна обновлен!"));
        }
        return "redirect:/master/edit/actions";
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/action/edit/{id}", method = RequestMethod.GET)
    public ModelAndView masterActionEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("masterAction", masterActionService.get(id));
        mav.setViewName("master/edit/masterActionEdit");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/action/attach", method = RequestMethod.POST)
    public String attachActions(Long[] actions, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if (master != null) {
            for (Long actionId : actions) {
                Action action = actionService.get(actionId);
                if (action != null) {
                    masterActionService.attach(master, action);
                }
            }
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуги успешно прикрепленны!"));
        }
        return "redirect:/master/edit/actions";
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/action/detach", method = RequestMethod.POST)
    public String attachActions(Long actionId, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if (master != null) {
            Action action = actionService.get(actionId);
            if (action != null) {
                masterActionService.detach(master, action);
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуга успешно удалена!"));
            }
        }
        return "redirect:/master/edit/actions";
    }

    private Master getCurrentMaster() {
        User currentUser = authenticationService.getPrincipal();
        return masterService.get(currentUser);
    }

}
