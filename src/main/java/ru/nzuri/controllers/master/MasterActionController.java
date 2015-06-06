/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.master;

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
import ru.nzuri.domain.Price;
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

import javax.inject.Inject;
import java.util.List;

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
    @RequestMapping(value = "/master/specialization/create", method = RequestMethod.GET)
    public ModelAndView createCustomSpecialization() {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if (master != null) {
            model.addObject("master", master);
            model.addObject("specialization", new Specialization());
            model.setViewName("master/specialization/create");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/specialization/save", method = RequestMethod.POST)
    public String saveCustomSpecialization(@ModelAttribute("specialization") Specialization specialization, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if (master != null) {
            masterActionService.createCustomSpecialization(master,specialization.getData());
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуга успешна обновлен!"));
        }
        return "redirect:/master/edit/actions";
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/action/create/{specializationId}", method = RequestMethod.GET)
    public ModelAndView createCustomAction(@PathVariable Long specializationId) {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if (master != null) {
            model.addObject("master", master);
            model.addObject("specialization", specializationService.get(specializationId));
            model.addObject("masterAction", masterActionService.createEmptyEntity());
            model.setViewName("master/action/create");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/action/save/{specializationId}", method = RequestMethod.POST)
    public String saveCustomAction(@ModelAttribute("masterAction") MasterAction masterAction,@PathVariable Long specializationId, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        Specialization specialization = specializationService.get(specializationId);
        if (master != null && specialization != null) {
            masterActionService.createCustomAction(master,specialization,masterAction.getAction().getData(),masterAction.getPrice());
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуга успешна обновлен!"));
        }
        return "redirect:/master/edit/actions";
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/action/update", method = RequestMethod.POST)
    public String updateAction(@ModelAttribute("masterAction") MasterAction masterAction, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if (master != null) {
            actionService.merge(masterAction.getAction());
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
        mav.setViewName("master/action/edit");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/action/attach", method = RequestMethod.POST)
    public String attachActions(Long[] actions, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if (master != null && actions != null && actions.length != 0) {
            for (Long actionId : actions) {
                Action action = actionService.get(actionId);
                if (action != null) {
                    masterActionService.attach(master, action, Price.NULL);
                }
            }
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуги успешно прикрепленны!"));
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Не удалось прикрепить услуги"));
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
