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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nzuri.controllers.message.Message;
import ru.nzuri.controllers.message.MessageType;
import ru.nzuri.domain.master.Comment;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.master.CommentService;
import ru.nzuri.services.master.MasterService;

/**
 *
 * @author bad
 */
@Controller
public class CommentController {

    @Inject
    private MasterService masterService;

    @Inject
    private CommentService commentService;

    @Inject
    private AuthenticationService authenticationService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/master/comment/add/{masterId}", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("comment") Comment comment,@PathVariable Long masterId, final RedirectAttributes redirectAttributes) {
        Master master = masterService.get(masterId);
        User currentUser = authenticationService.getPrincipal();
        if (master != null && currentUser != null && comment.getContent() != null && !comment.getContent().isEmpty()) {
            comment.setMaster(master);
            comment.setUser(currentUser);
            commentService.store(comment);
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Комментарий добавлен"));
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Не удалось добавить комментарий"));
        }
        return "redirect:/master/"+masterId;
    }

   
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//        sdf.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//    }
    


}
