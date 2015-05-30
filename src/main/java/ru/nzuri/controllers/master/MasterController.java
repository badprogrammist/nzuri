/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.master;

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
import ru.nzuri.domain.master.Address;
import ru.nzuri.domain.master.Comment;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.action.ActionService;
import ru.nzuri.services.action.SpecializationService;
import ru.nzuri.services.master.CommentService;
import ru.nzuri.services.master.MasterService;

/**
 *
 * @author bad
 */
@Controller
public class MasterController {
    
    @Inject
    private MasterService masterService;
    
    @Inject
    private AuthenticationService authenticationService;
    
    @Inject
    private CommentService commentService;
    
    @Inject
    private ActionService actionService;
    
    @Inject
    private SpecializationService specializationService;
    
    @RequestMapping(value = "/masters", method = RequestMethod.GET)
    public ModelAndView masters() {
        ModelAndView model = new ModelAndView();
        model.addObject("masters", masterService.getAll());
        model.setViewName("master/list");
        return model;
    }
    
    @RequestMapping(value = "/master/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable Long id) {
        Master master = masterService.get(id);
        User currentUser = authenticationService.getPrincipal();
        return prepareView(master,currentUser,commentService.getAll(master));
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit", method = RequestMethod.GET)
    public ModelAndView editMain() {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if(master != null) {
            model.addObject("master", master);
            model.setViewName("master/edit/main");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/examples", method = RequestMethod.GET)
    public ModelAndView editExamples() {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if(master != null) {
            model.addObject("master", master);
            model.setViewName("master/edit/examples");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/address", method = RequestMethod.GET)
    public ModelAndView editAddress() {
        ModelAndView model = new ModelAndView();
        Master master = getCurrentMaster();
        if(master != null) {
            model.addObject("address", master.getAddress());
            model.setViewName("master/edit/address");
        } else {
            model.setViewName("404");
        }
        return model;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/address/update", method = RequestMethod.POST)
    public String updateAddress(@ModelAttribute("address") Address address,final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        if(master != null) {
            master.getAddress().merge(address);
            masterService.update(master);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Адрес успешно обновлен!"));
        }
        return "redirect:/master/edit/address";
    }
    
    private Master getCurrentMaster() {
        User currentUser = authenticationService.getPrincipal();
        return masterService.get(currentUser);
    }

    public static ModelAndView prepareView(Master master, User currentUser, List<Comment> comments) {
        ModelAndView model = new ModelAndView();
        if(master != null) {
            model.addObject("master", master);
            model.addObject("comment", new Comment());
            model.addObject("comments", comments);
            model.addObject("editable", master.getUser().equals(currentUser));
            model.setViewName("master/view");
        } else {
            model.setViewName("404");
        }
        return model;
    }

    
}
