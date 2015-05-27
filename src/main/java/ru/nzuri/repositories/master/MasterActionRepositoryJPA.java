/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.master;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.master.MasterActionRepository;
import ru.nzuri.domain.master.MasterSpecialization;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class MasterActionRepositoryJPA extends AbstractRepositoryJPA<MasterAction> implements MasterActionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public MasterActionRepositoryJPA() {
        super(MasterAction.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public MasterSpecialization findMasterSpecializaion(Master master, Specialization specialization) {
        try {
            return getEntityManager().createNamedQuery("MasterSpecialization.findByMasterAndSpecialization", MasterSpecialization.class)
                    .setParameter("specialization", specialization)
                    .setParameter("master", master)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public MasterAction find(Master master, Action action) {
        try {
            return getEntityManager().createNamedQuery("MasterAction.findByServiceAndMaster", MasterAction.class)
                    .setParameter("action", action)
                    .setParameter("master", master)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

}
