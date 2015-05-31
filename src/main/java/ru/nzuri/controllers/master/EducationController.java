/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.master;

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
import ru.nzuri.domain.master.Education;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.master.EducationService;
import ru.nzuri.services.master.MasterService;

/**
 *
 * @author bad
 */
@Controller
public class EducationController {

    @Inject
    private MasterService masterService;

    @Inject
    private EducationService educationService;

    @Inject
    private AuthenticationService authenticationService;

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/education", method = RequestMethod.GET)
    public ModelAndView masterEducationEdit() {
        ModelAndView mav = new ModelAndView();
        prepareList(mav);
        mav.addObject("education", educationService.createEmptyEntity());
        mav.setViewName("master/edit/education");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/education/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("education") Education education, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        ModelAndView mav = new ModelAndView();
        if (master != null) {
            education.setMaster(master);
            educationService.store(education);
            prepareList(mav);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Образовательное учреждение добавленно!"));
        }
        mav.setViewName("master/education/_list");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/education/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("education", educationService.get(id));
        mav.setViewName("master/education/_edit");
        return mav;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/education/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        prepareList(mav);
        mav.setViewName("master/education/_list");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/education/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("education") Education education, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        educationService.merge(education);
        prepareList(mav);
        mav.setViewName("master/education/_list");
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуги успешно прикрепленны!"));
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/education/remove/{id}", method = RequestMethod.POST)
    public ModelAndView remove(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        educationService.remove(id);
        prepareList(mav);
        mav.setViewName("master/education/_list");
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Услуги успешно прикрепленны!"));
        return mav;
    }
    
    private void prepareList(ModelAndView mav) {
        Master master = getCurrentMaster();
        User currentUser = authenticationService.getPrincipal();
        mav.addObject("educations", educationService.getAll(master));
        mav.addObject("editable", master.getUser().equals(currentUser));
    }

    private Master getCurrentMaster() {
        User currentUser = authenticationService.getPrincipal();
        return masterService.get(currentUser);
    }

}
