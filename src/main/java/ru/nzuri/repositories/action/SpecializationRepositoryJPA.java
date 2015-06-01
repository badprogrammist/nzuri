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
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.action.SpecializationOwnType;
import ru.nzuri.domain.action.SpecializationRepository;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class SpecializationRepositoryJPA extends AbstractRepositoryJPA<Specialization> implements SpecializationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public SpecializationRepositoryJPA() {
        super(Specialization.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Specialization> findCommonSpecializations() {
        return getEntityManager().createNamedQuery("Specialization.findAllByOwnType", Specialization.class)
                .setParameter("ownType", SpecializationOwnType.COMMON)
                .getResultList();
    }

}
