/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.domain.service.SpecializationRepository;
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

}
