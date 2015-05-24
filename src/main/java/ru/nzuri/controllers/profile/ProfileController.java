/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.profile;

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
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.profile.ProfileService;

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
