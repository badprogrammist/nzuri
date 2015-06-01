/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.action;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.ActionRepository;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author Ильдар
 */
@Service
public class DefaultActionService extends AbstractService<Action> implements ActionService {

    @Inject
    private ActionRepository actionRepository;
    
    @Inject
    private SpecializationService specializationService;

    @Override
    protected EntityRepository getRepository() {
        return actionRepository;
    }

    @Override
    public Action createEmptyEntity() {
        return new Action();
    }

    @Override
    public List<Action> getCommonActions(Specialization specialization) {
        return actionRepository.findCommonActions(specialization);
    }

    @Override
    public List<Specialization> getDecomposedCommonActions() {
        List<Specialization> commonSpecializations = specializationService.getCommonSpecializations();
        for(Specialization specialization : commonSpecializations) {
            specialization.setActions(getCommonActions(specialization));
        }
        return commonSpecializations;
    }

}
