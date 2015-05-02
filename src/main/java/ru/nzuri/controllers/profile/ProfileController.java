/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.profile;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nzuri.services.profile.ProfileService;

/**
 *
 * @author bad
 */
@Controller
public class ProfileController {
    
    @Inject
    private ProfileService profileService;
    
    @RequestMapping(value = "/masters", method = RequestMethod.GET)
    public ModelAndView masters() {
        ModelAndView model = new ModelAndView();
        model.addObject("profiles", profileService.getAll());
        model.setViewName("profile/list");
        return model;
    }
    
    @RequestMapping(value = "/master/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Long id) {
        ModelAndView model = new ModelAndView();
        model.addObject("profile", profileService.get(id));
        model.setViewName("profile/view");
        return model;
    }
    
}
