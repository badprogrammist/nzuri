/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.master.MasterActionRepository;
import ru.nzuri.domain.master.MasterSpecialization;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@org.springframework.stereotype.Service
@Transactional
public class DefaultMasterActionService extends AbstractService<MasterAction> implements MasterActionService {

    @Inject
    private MasterActionRepository masterActionRepository;
    
    @Override
    public MasterSpecialization getMasterSpecializaion(Master master,Specialization specialization) {
        return masterActionRepository.findMasterSpecializaion(master, specialization);
    }

    @Override
    public MasterAction get(Master master, Action action) {
        return masterActionRepository.find(master, action);
    }
    
    @Override
    public MasterAction createEmptyEntity() {
        return new MasterAction();
    }
    
    @Override
    protected EntityRepository<MasterAction> getRepository() {
        return masterActionRepository;
    }

}
