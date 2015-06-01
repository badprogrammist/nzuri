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
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.action.SpecializationRepository;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author Ильдар
 */
@Service
public class DefaultSpecializationService extends AbstractService<Specialization> implements SpecializationService {

    @Inject
    private SpecializationRepository specializationRepository;

    @Override
    protected EntityRepository getRepository() {
        return specializationRepository;
    }

    @Override
    public Specialization createEmptyEntity() {
        return new Specialization();
    }

    @Override
    public List<Specialization> getCommonSpecializations() {
        return specializationRepository.findCommonSpecializations();
    }

}
