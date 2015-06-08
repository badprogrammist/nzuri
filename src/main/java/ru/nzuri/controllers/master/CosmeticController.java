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
import ru.nzuri.domain.master.Cosmetic;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.master.CosmeticService;
import ru.nzuri.services.master.MasterService;

/**
 *
 * @author bad
 */
@Controller
public class CosmeticController {

    @Inject
    private MasterService masterService;

    @Inject
    private CosmeticService cosmeticService;

    @Inject
    private AuthenticationService authenticationService;

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/cosmetic", method = RequestMethod.GET)
    public ModelAndView masterCosmeticEdit() {
        ModelAndView mav = new ModelAndView();
        prepareList(mav);
        mav.addObject("cosmetic", cosmeticService.createEmptyEntity());
        mav.setViewName("master/edit/cosmetic");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/cosmetic/save", method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute("cosmetic") Cosmetic cosmetic, final RedirectAttributes redirectAttributes) {
        Master master = getCurrentMaster();
        ModelAndView mav = new ModelAndView();
        if (master != null) {
            cosmetic.setMaster(master);
            cosmeticService.store(cosmetic);
            prepareList(mav);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Марка косметики добавленна!"));
        }
        mav.setViewName("master/cosmetic/_list");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/cosmetic/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("cosmetic", cosmeticService.get(id));
        mav.setViewName("master/cosmetic/_edit");
        return mav;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/cosmetic/list", method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView();
        prepareList(mav);
        mav.setViewName("master/cosmetic/_list");
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/cosmetic/update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute("cosmetic") Cosmetic cosmetic, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        cosmeticService.merge(cosmetic);
        prepareList(mav);
        mav.setViewName("master/cosmetic/_list");
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Изменения сохранены"));
        return mav;
    }

    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/edit/cosmetic/remove/{id}", method = RequestMethod.POST)
    public ModelAndView remove(@PathVariable Long id, final RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        cosmeticService.remove(id);
        prepareList(mav);
        mav.setViewName("master/cosmetic/_list");
        redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Объект удален"));
        return mav;
    }
    
    private void prepareList(ModelAndView mav) {
        Master master = getCurrentMaster();
        User currentUser = authenticationService.getPrincipal();
        mav.addObject("cosmetics", cosmeticService.getAll(master));
        mav.addObject("editable", master.getUser().equals(currentUser));
    }

    private Master getCurrentMaster() {
        User currentUser = authenticationService.getPrincipal();
        return masterService.get(currentUser);
    }

}
