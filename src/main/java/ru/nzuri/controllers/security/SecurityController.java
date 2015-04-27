/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.security;

import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nzuri.domain.user.UserData;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.security.Credentials;
import ru.nzuri.services.registration.RegistrationService;

/**
 *
 * @author bad
 */
@Controller
public class SecurityController {
    
    @Inject
    private AuthenticationService authenticationService;
    
    @Inject
    private RegistrationService registrationService;
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView model = new ModelAndView();
        model.addObject("credentials", new Credentials());
        model.setViewName("security/login");
        return model;
    }
    
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("credentials") Credentials credentials) {
        try {
            authenticationService.authenticate(credentials);
        } catch (SecurityException ex) {
            return "redirect:/login";
        }
        return "redirect:/";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView model = new ModelAndView();
        model.addObject("registrationData", new RegistrationData());
        model.setViewName("security/registration");
        return model;
    }
    
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("registrationData") RegistrationData rd) {
        try {
            UserData userData = createUserData(rd);
            registrationService.register(rd,userData);
        } catch (SecurityException ex) {
            return "redirect:/registration";
        }
        return "redirect:/";
    }
    
    private UserData createUserData(RegistrationData rd) {
        return new UserData(rd.getName(), rd.getLastname() ,rd.getPatronymic());
    }
}
