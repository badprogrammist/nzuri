package ru.nzuri.repositories.request;

import org.springframework.stereotype.Repository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.request.Request;
import ru.nzuri.domain.request.RequestRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.repositories.AbstractRepositoryJPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by bad on 06.06.2015.
 */
@Repository
public class RequestRepositoryJPA extends AbstractRepositoryJPA<Request> implements RequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public RequestRepositoryJPA() {
        super(Request.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Request> findRequests(Master master) {
        return getEntityManager().createNamedQuery("Request.findByMaster", Request.class)
            .setParameter("master" ,master)
            .getResultList();
    }

    @Override
    public List<Request> findRequests(User owner) {
        return getEntityManager().createNamedQuery("Request.findByOwner", Request.class)
            .setParameter("owner" ,owner)
            .getResultList();
    }
}
