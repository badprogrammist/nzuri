package ru.nzuri.services.request;

import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.request.Request;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.EntityService;

import java.util.List;

/**
 * Created by bad on 06.06.2015.
 */
public interface RequestService extends EntityService<Request> {

    public Request createRequest(User owner, Master master, List<MasterAction> actions);

    public List<Request> getRequests(Master master);
    public List<Request> getRequests(User owner);

}
