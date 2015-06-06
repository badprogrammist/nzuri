package ru.nzuri.domain.request;

import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;

import java.util.List;

/**
 * Created by bad on 06.06.2015.
 */
public interface RequestRepository extends EntityRepository<Request> {
    public List<Request> findRequests(Master master);
    public List<Request> findRequests(User owner);
}
