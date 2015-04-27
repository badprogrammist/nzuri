/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bad
 */
@Controller
public class AppController {
    
    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Tutorial");
        model.addObject("message", "Welcome Page !");
        model.setViewName("index");
        return model;
    }
    
    @RequestMapping(value = "/about", method = {RequestMethod.GET})
    public ModelAndView about() {
        ModelAndView model = new ModelAndView();
        model.addObject("content", "About content");
        model.setViewName("about");
        return model;
    }

//    @RequestMapping(value = "/protected**", method = RequestMethod.GET)
//    public ModelAndView protectedPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security 3.2.4 Hello World Tutorial");
//        model.addObject("message", "This is protected page - Only for Admin Users!");
//        model.setViewName("protected");
//        return model;
//
//    }
//
//    @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
//    public ModelAndView adminPage() {
//
//        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security 3.2.4 Hello World Tutorial");
//        model.addObject("message", "This is confidential page - Need Super Admin Role!");
//        model.setViewName("protected");
//
//        return model;
//
//    }
}
