/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.profile;

import java.util.Map;
import javax.inject.Inject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nzuri.domain.profile.Example;
import ru.nzuri.services.profile.ExampleService;

/**
 *
 * @author bad
 */
@Controller
public class ExampleController {
    
    private static int count = 10;
    
    @Inject
    private ExampleService exampleService;
    
    @RequestMapping(value = "/examples", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.addObject("examples", exampleService.getAll());
        model.addObject("count", count);
        model.setViewName("profile/index");
        return model;
    }
    
    @Secured("ROLE_USER")
    @RequestMapping(value = "/example/create", method = RequestMethod.GET)
    public String create(Map<String, Object> map) {
        map.put("example", new Example());
        return "profile/create";
    }
    
//    @Secured("hasRole(USER)")
    @RequestMapping(value = "/example/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("example") Example example) {
        exampleService.store(example);
        return "redirect:/examples";
    }
    
//    @Secured("hasRole(USER)")
    @RequestMapping(value = "/example/increment", method = RequestMethod.POST)
    public ModelAndView increment() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("count", ++count);
        mav.setViewName("profile/count");
        return mav;
    }
    
}
