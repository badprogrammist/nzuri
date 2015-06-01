/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.action;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.ActionOwnType;
import ru.nzuri.domain.action.ActionRepository;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class ActionRepositoryJPA extends AbstractRepositoryJPA<Action> implements ActionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ActionRepositoryJPA() {
        super(Action.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Action> findCommonActions(Specialization specialization) {
        return getEntityManager().createNamedQuery("Action.findAllBySpecialization", Action.class)
                .setParameter("specialization", specialization)
                .setParameter("ownType", ActionOwnType.COMMON)
                .getResultList();
    }

}
