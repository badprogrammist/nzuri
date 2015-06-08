package ru.nzuri.services.request;

import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.request.Request;
import ru.nzuri.domain.request.RequestAction;
import ru.nzuri.domain.request.RequestRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.AbstractService;

/**
 * Created by bad on 06.06.2015.
 */
@Service
public class DefaultRequestService extends AbstractService<Request> implements RequestService {

    @Inject
    private RequestRepository requestRepository;

    @Override
    protected EntityRepository<Request> getRepository() {
        return requestRepository;
    }

    @Override
    @Transactional
    public Request createRequest(User owner, Master master, List<MasterAction> actions,Date exerciseDate) {
        Request request = new Request(owner,master,exerciseDate);
        for(MasterAction masterAction : actions) {
            RequestAction requestAction = new RequestAction(request,masterAction.getPrice(),masterAction.getAction());
            request.getActions().add(requestAction);
        }
        store(request);
        return request;
    }

    @Override
    public List<Request> getRequests(Master master) {
        return requestRepository.findRequests(master);
    }

    @Override
    public List<Request> getRequests(User owner) {
        return requestRepository.findRequests(owner);
    }

    @Override
    public Request createEmptyEntity() {
        return new Request();
    }
}
