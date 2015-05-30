/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.master;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.master.Education;
import ru.nzuri.domain.master.EducationRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class EducationRepositoryJPA extends AbstractRepositoryJPA<Education> implements EducationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public EducationRepositoryJPA() {
        super(Education.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Education> findAll(Master master) {
        return getEntityManager().createNamedQuery("Education.findAllByMaster", Education.class)
                .setParameter("master", master)
                .getResultList();
    }
    
}
