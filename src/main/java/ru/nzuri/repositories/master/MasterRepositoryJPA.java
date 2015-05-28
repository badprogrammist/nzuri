/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.master;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class MasterRepositoryJPA extends AbstractRepositoryJPA<Master> implements MasterRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public MasterRepositoryJPA() {
        super(Master.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Master findByUser(User user) {
        try {
            return getEntityManager().createNamedQuery("Master.findByUser", Master.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch(Exception ex) {
            return Master.NULL;
        }
    }
    
}
