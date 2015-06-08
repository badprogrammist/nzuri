package ru.nzuri.controllers.request;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.nzuri.controllers.message.Message;
import ru.nzuri.controllers.message.MessageType;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.user.User;
import ru.nzuri.security.AuthenticationService;
import ru.nzuri.services.master.MasterActionService;
import ru.nzuri.services.master.MasterService;
import ru.nzuri.services.request.RequestService;

/**
 * Created by bad on 06.06.2015.
 */
@Controller
public class RequestController {

    @Inject
    private RequestService requestService;

    @Inject
    private MasterService masterService;

    @Inject
    private MasterActionService masterActionService;

    @Inject
    private AuthenticationService authenticationService;

    @Secured("ROLE_USER")
    @RequestMapping(value = "/request/new", method = RequestMethod.POST)
    public String newRequest(Long[] masterActions, Long masterId, Date exerciseDate, final RedirectAttributes redirectAttributes) {
        Master master = masterService.get(masterId);
        User owner = authenticationService.getPrincipal();
        if (master != null
                && owner != null
                && masterActions != null
                && masterActions.length != 0
                && exerciseDate != null
                && exerciseDate.after(new Date())) {
            List<MasterAction> masterActionList = new ArrayList<>();
            for (Long masterActionId : masterActions) {
                MasterAction masterAction = masterActionService.get(masterActionId);
                if (masterAction != null) {
                    masterActionList.add(masterAction);
                }
            }
            if(!masterActionList.isEmpty()) {
                requestService.createRequest(owner,master,masterActionList,exerciseDate);
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Заявка успешно создана"));
            }
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Не удалось создать заявку"));
        }
        return "redirect:/master/"+masterId;
    }
    
    @Secured("ROLE_MASTER")
    @RequestMapping(value = "/master/requests", method = RequestMethod.GET)
    public ModelAndView masterRequest() {
        ModelAndView mav = new ModelAndView();
        User currentUser = authenticationService.getPrincipal();
        Master master = masterService.get(currentUser);
        if(master != null) {
            mav.addObject("requests", requestService.getRequests(master));
        }
        mav.setViewName("request/master/list");
        return mav;
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
