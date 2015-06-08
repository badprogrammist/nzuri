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
import ru.nzuri.domain.master.Example;
import ru.nzuri.domain.master.ExampleRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class ExampleRepositoryJPA extends AbstractRepositoryJPA<Example> implements ExampleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ExampleRepositoryJPA() {
        super(Example.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Example> findExamples(Master master) {
        return getEntityManager().createNamedQuery("Example.findAllByMaster", Example.class)
                .setParameter("master", master)
                .getResultList();
    }
    
}
