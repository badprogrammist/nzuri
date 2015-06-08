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
import ru.nzuri.domain.master.Cosmetic;
import ru.nzuri.domain.master.CosmeticRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class CosmeticRepositoryJPA extends AbstractRepositoryJPA<Cosmetic> implements CosmeticRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CosmeticRepositoryJPA() {
        super(Cosmetic.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Cosmetic> findAll(Master master) {
        return getEntityManager().createNamedQuery("Cosmetic.findAllByMaster", Cosmetic.class)
                .setParameter("master", master)
                .getResultList();
    }
    
}
