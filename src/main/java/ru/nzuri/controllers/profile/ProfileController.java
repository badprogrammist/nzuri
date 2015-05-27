/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.profile;

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
import ru.nzuri.domain.profile.Address;
import ru.nzuri.domain.profile.Profile;
import ru.nzuri.domain.profile.ProfileSpecializationRelation;
import ru.nzuri.domain.profile.ProfileSpecializationServiceRelation;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.profile.ProfileService;
import ru.nzuri.services.service.ServiceService;
import ru.nzuri.services.service.SpecializationService;

/**
 *
 * @author bad
 */
@Controller
public class ProfileController {
    
    @Inject
    private ProfileService profileService;

    @Inject
    private AuthenticationService authenticationService;
    
    @Inject
    private ServiceService serviceService;
    
    @Inject
    private SpecializationService specializationService;
    
    @RequestMapping(value = "/masters", method = RequestMethod.GET)
    public ModelAndView masters() {
        ModelAndView model = new ModelAndView();
        model.addObject("profiles", profileService.getAll());
        model.setViewName("profile/list");
        return model;
    }
    
    @RequestMapping(value = "/master/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Long id) {
        Profile profile = profileService.get(id);
        User currentUser = authenticationService.getPrincipal();
        return prepareView(profile,currentUser);
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit", method = RequestMethod.GET)
    public ModelAndView editMain() {
        ModelAndView model = new ModelAndView();
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            model.addObject("profile", profile);
            model.setViewName("profile/edit/main");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/examples", method = RequestMethod.GET)
    public ModelAndView editExamples() {
        ModelAndView model = new ModelAndView();
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            model.addObject("profile", profile);
            model.setViewName("profile/edit/examples");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/address", method = RequestMethod.GET)
    public ModelAndView editAddress() {
        ModelAndView model = new ModelAndView();
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            model.addObject("address", profile.getAddress());
            model.setViewName("profile/edit/address");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/updateAddress", method = RequestMethod.POST)
    public String updateAddress(@ModelAttribute("address") Address address,final RedirectAttributes redirectAttributes) {
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            profile.getAddress().merge(address);
            profileService.update(profile);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Адрес успешно обновлен!"));
        }
        return "redirect:/master/edit/address";
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/services", method = RequestMethod.GET)
    public ModelAndView editServices() {
        ModelAndView model = new ModelAndView();
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            List<ProfileSpecializationRelation> profileSpecializations = profile.getSpecializations();
            List<Specialization> specializations = specializationService.getAll();
            for(Specialization specialization : specializations) {
                List<Service> toRemove = new ArrayList<>();
                for(Service service : specialization.getServices()) {
                    
                    for(ProfileSpecializationRelation profileSpecialization : profileSpecializations) {
                        for(ProfileSpecializationServiceRelation profileService : profileSpecialization.getProfileServices()) {
                            if(profileService.getService().equals(service)) {
                                toRemove.add(service);
                            }
                        }
                    }
                }
                specialization.getServices().removeAll(toRemove);
            }
            model.addObject("profile", profile);
            model.addObject("profileSpecializations", profileSpecializations);
            model.addObject("specializations", specializations);
            model.addObject("servicesUpdateContainer", new ProfileServiceUpdateContainer());
            model.setViewName("profile/edit/services");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/updateServices", method = RequestMethod.POST)
    public String updateServices(@ModelAttribute("servicesUpdateContainer") ProfileServiceUpdateContainer servicesUpdateContainer, final RedirectAttributes redirectAttributes) {
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Адрес успешно обновлен!"));
        }
        return "redirect:/master/edit/services";
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/attachServices", method = RequestMethod.POST)
    public String attachServices(Long[] services, final RedirectAttributes redirectAttributes) {
        Profile profile = getCurrentUserProfile();
        if(profile != null) {
            for(Long serviceId : services) {
                Service service = serviceService.get(serviceId);
                if(service != null) {
                    ProfileSpecializationRelation profileSpecialization = null;
                    for(ProfileSpecializationRelation profileSpecializationCandidate : profile.getSpecializations()) {
                        if(profileSpecializationCandidate.getSpecialization().equals(service.getSpecialization())) {
                            profileSpecialization = profileSpecializationCandidate;
                            break;
                        }
                    }
                    if(profileSpecialization == null) {
                        profileSpecialization = new ProfileSpecializationRelation(profile, service.getSpecialization());
                        profile.getSpecializations().add(profileSpecialization);
                    }
                    boolean exist = false;
                    for(ProfileSpecializationServiceRelation profileSpecializationService : profileSpecialization.getProfileServices()) {
                        if(profileSpecializationService.getService().equals(service)) {
                            exist = true;
                            break;
                        }
                    }
                    if(!exist) {
                        ProfileSpecializationServiceRelation profileSpecializationService = new ProfileSpecializationServiceRelation(profileSpecialization, service);
                        profileSpecialization.getProfileServices().add(profileSpecializationService);
                    }
                }
            }
            profileService.update(profile);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуги успешно прикрепленны!"));
        }
        return "redirect:/master/edit/services";
    }
    
    private Profile getCurrentUserProfile() {
        User currentUser = authenticationService.getPrincipal();
        return profileService.getProfile(currentUser);
    }

    public static ModelAndView prepareView(Profile profile, User currentUser) {
        ModelAndView model = new ModelAndView();
        if(profile != null) {
            model.addObject("profile", profile);
            model.addObject("editable", profile.getUser().equals(currentUser));
            model.setViewName("profile/view");
        } else {
            model.setViewName("404");
        }
        return model;
    }

    
}
