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
    @RequestMapping(value = "/master/edit/services", method = RequestMethod.GET)
    public ModelAndView editActions() {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if(master != null) {
            List<MasterSpecialization> masterSpecializations = master.getSpecializations();
            List<Specialization> specializations = specializationService.getAll();
            specializations.stream().forEach((specialization) -> {
                List<Action> toRemove = new ArrayList<>();
                specialization.getActions().stream().forEach((action) -> {
                    masterSpecializations.stream().forEach((masterSpecialization) -> {
                        masterSpecialization.getMasterActions().stream().filter((profileService) -> (profileService.getAction().equals(action))).forEach((_item) -> {
                            toRemove.add(action);
                        });
                    });
                });
                specialization.getActions().removeAll(toRemove);
            });
            model.addObject("master", master);
            model.addObject("masterSpecializations", masterSpecializations);
            model.addObject("specializations", specializations);
            model.setViewName("master/edit/services");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/service/update", method = RequestMethod.POST)
    public String updateService(@ModelAttribute("masterAction") MasterAction masterAction, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if(master != null) {
            masterActionService.merge(masterAction);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуга успешна обновлен!"));
        }
        return "redirect:/master/edit/services";
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/service/edit/{id}", method = RequestMethod.GET)
    public ModelAndView profileServiceEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("profileService", masterActionService.getProfileService(id));
        mav.setViewName("profile/edit/profileServiceEdit");
        return mav;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/attachServices", method = RequestMethod.POST)
    public String attachServices(Long[] services, final RedirectAttributes redirectAttributes) {
        Master profile = getCurrentMaster();
        if(profile != null) {
            for(Long serviceId : services) {
                Action service = actionService.get(serviceId);
                if(service != null) {
                    MasterSpecialization profileSpecialization = null;
                    for(MasterSpecialization profileSpecializationCandidate : profile.getSpecializations()) {
                        if(profileSpecializationCandidate.getSpecialization().equals(service.getSpecialization())) {
                            profileSpecialization = profileSpecializationCandidate;
                            break;
                        }
                    }
                    if(profileSpecialization == null) {
                        profileSpecialization = new MasterSpecialization(profile, service.getSpecialization());
                        profile.getSpecializations().add(profileSpecialization);
                    }
                    boolean exist = false;
                    for(MasterAction profileSpecializationService : profileSpecialization.getMasterActions()) {
                        if(profileSpecializationService.getAction().equals(service)) {
                            exist = true;
                            break;
                        }
                    }
                    if(!exist) {
                        MasterAction profileSpecializationService = new MasterAction(profileSpecialization, service);
                        profileSpecialization.getMasterActions().add(profileSpecializationService);
                    }
                }
            }
            masterService.update(profile);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуги успешно прикрепленны!"));
        }
        return "redirect:/master/edit/services";
    }
    
    private Master getCurrentMaster() {
        User currentUser = authenticationService.getPrincipal();
        return masterService.get(currentUser);
    }

    

    
}
