/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nzuri.controllers.profile.ProfileController;
import ru.nzuri.domain.profile.Profile;
import ru.nzuri.domain.user.Roles;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.profile.ProfileService;

/**
 * @author bad
 */
@Controller
public class AppController {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ProfileService profileService;

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        if (authenticationService.isAuthenticated()) {
            if (authenticationService.hasRole(Roles.ROLE_MASTER.name())) {
                User user = authenticationService.getPrincipal();
                Profile profile =profileService.getProfile(user);
                model = ProfileController.prepareView(profile,user);
            }
        } else {
            model.setViewName("index");
        }
        return model;
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public ModelAndView page404() {
        ModelAndView model = new ModelAndView();
        model.setViewName("404");
        return model;
    }

}
