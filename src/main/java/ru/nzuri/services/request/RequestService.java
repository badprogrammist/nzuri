package ru.nzuri.services.request;

import java.util.Date;
import java.util.List;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.request.Request;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.EntityService;

/**
 * Created by bad on 06.06.2015.
 */
public interface RequestService extends EntityService<Request> {

    public Request createRequest(User owner, Master master, List<MasterAction> actions,Date exerciseDate);

    public List<Request> getRequests(Master master);
    public List<Request> getRequests(User owner);

}
