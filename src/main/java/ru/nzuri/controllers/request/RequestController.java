package ru.nzuri.controllers.request;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
    public String attachActions(Long[] masterActions, Long masterId, final RedirectAttributes redirectAttributes) {
        Master master = masterService.get(masterId);
        User owner = authenticationService.getPrincipal();
        if (master != null && owner != null && masterActions != null && masterActions.length != 0) {
            List<MasterAction> masterActionList = new ArrayList<>();
            for (Long masterActionId : masterActions) {
                MasterAction masterAction = masterActionService.get(masterActionId);
                if (masterAction != null) {
                    masterActionList.add(masterAction);
                }
            }
            if(!masterActionList.isEmpty()) {
                requestService.createRequest(owner,master,masterActionList);
                redirectAttributes.addFlashAttribute("message", new Message(MessageType.SUCCESS, "Заявка успешно создана"));
            }
        } else {
            redirectAttributes.addFlashAttribute("message", new Message(MessageType.DANGER, "Не удалось создать заявку"));
        }
        return "redirect:/master/"+masterId;
    }

}
