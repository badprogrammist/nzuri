/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.ActionData;
import ru.nzuri.domain.action.ActionOwnType;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.action.SpecializationData;
import ru.nzuri.domain.action.SpecializationOwnType;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.master.MasterActionRepository;
import ru.nzuri.domain.master.MasterSpecialization;
import ru.nzuri.services.AbstractService;
import ru.nzuri.services.action.ActionService;
import ru.nzuri.services.action.SpecializationService;

/**
 *
 * @author bad
 */
@Service
public class DefaultMasterActionService extends AbstractService<MasterAction> implements MasterActionService {

    @Inject
    private MasterActionRepository masterActionRepository;

    @Inject
    private SpecializationService specializationService;
    
    @Inject
    private ActionService actionService;
    
    @Inject
    private MasterService masterService;

    @Override
    public MasterAction get(Master master, Action action) {
        MasterSpecialization masterSpecialization = getMasterSpecializaion(master, action.getSpecialization());
        if (masterSpecialization != null) {
            for (MasterAction masterAction : masterSpecialization.getMasterActions()) {
                if (masterAction.getAction().equals(action)) {
                    return masterAction;
                }
            }
        }
        return null;
    }

    @Override
    public MasterAction createEmptyEntity() {
        return new MasterAction();
    }

    @Override
    protected EntityRepository<MasterAction> getRepository() {
        return masterActionRepository;
    }

    @Override
    public List<Specialization> getAttachCandidates(Master master) {
        List<Specialization> specializations = actionService.getDecomposedCommonActions();
        List<Specialization> toRemoveSpecializations = new ArrayList<>();
        for (Specialization specialization : specializations) {
            List<Action> toRemoveActions = new ArrayList<>();
            for (Action action : specialization.getActions()) {
                for (MasterSpecialization masterSpecialization : master.getSpecializations()) {
                    for (MasterAction masterAction : masterSpecialization.getMasterActions()) {
                        if (masterAction.getAction().equals(action)) {
                            toRemoveActions.add(action);
                        }
                    }
                }
            }
            specialization.getActions().removeAll(toRemoveActions);
            if(specialization.getActions().isEmpty()) {
                toRemoveSpecializations.add(specialization);
            }
        }
        specializations.removeAll(toRemoveSpecializations);
        return specializations;
    }

    @Override
    @Transactional
    public MasterAction attach(Master master, Action action) {
        MasterSpecialization masterSpecialization = attach(master, action.getSpecialization());
        MasterAction masterAction = null;
        for (MasterAction ma : masterSpecialization.getMasterActions()) {
            if (ma.getAction().equals(action)) {
                masterAction = ma;
                break;
            }
        }
        if (masterAction == null) {
            masterAction = new MasterAction(masterSpecialization, action);
            masterSpecialization.getMasterActions().add(masterAction);
            masterActionRepository.update(masterSpecialization);
        }
        return masterAction;
    }

    @Transactional
    public MasterSpecialization attach(Master master, Specialization specialization) {
        MasterSpecialization masterSpecialization = getMasterSpecializaion(master, specialization);
        if (masterSpecialization == null) {
            masterSpecialization = new MasterSpecialization(master, specialization);
            master.getSpecializations().add(masterSpecialization);
            masterService.update(master);
        }
        return masterSpecialization;
    }

    @Override
    public MasterSpecialization getMasterSpecializaion(Master master, Specialization specialization) {
        return masterActionRepository.findMasterSpecializaion(master, specialization);
    }

    @Override
    @Transactional
    public void detach(Master master, Action action) {
        MasterAction masterAction = get(master, action);
        MasterSpecialization masterSpecialization = getMasterSpecializaion(master, action.getSpecialization());
        if (masterAction != null) {
            masterSpecialization.getMasterActions().remove(masterAction);
            masterActionRepository.update(masterSpecialization);
            if (masterSpecialization.getMasterActions().isEmpty()) {
                master.getSpecializations().remove(masterSpecialization);
                masterService.update(master);
            }
        }

    }

    @Override
    @Transactional
    public void createCustomSpecialization(Master master, SpecializationData specializationData) {
        if(master != null) {
            Specialization specialization = new Specialization(specializationData, SpecializationOwnType.CUSTOM);
//            specializationService.store(specialization);
            attach(master, specialization);
        }
    }

    @Override
    @Transactional
    public void createCustomAction(Master master, Specialization specialization, ActionData actionData) {
        if(master != null && specialization != null) {
            Action action = new Action(actionData, ActionOwnType.CUSTOM, specialization);
//            actionService.store(action);
            attach(master, action);
        }
    }

}
