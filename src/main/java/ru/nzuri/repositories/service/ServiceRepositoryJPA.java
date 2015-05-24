/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.ServiceRepository;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class ServiceRepositoryJPA extends AbstractRepositoryJPA<Service> implements ServiceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ServiceRepositoryJPA() {
        super(Service.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Service> getServices(Specialization specialization) {
        return getEntityManager().createNamedQuery("Service.findAllBySpecialization", Service.class)
                .setParameter("specialization", specialization)
                .getResultList();
    }

}
