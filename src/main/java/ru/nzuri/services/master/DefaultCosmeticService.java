/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.master.Education;
import ru.nzuri.domain.master.EducationRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultEducationService extends AbstractService<Education> implements EducationService {

    @Inject
    private EducationRepository educationRepository;
    
    
    
    @Override
    protected EntityRepository getRepository() {
        return educationRepository;
    }

    @Override
    public Education createEmptyEntity() {
        return new Education();
    }

    @Override
    public List<Education> getAll(Master master) {
        return educationRepository.findAll(master);
    }
    
}
