/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.profile;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.profile.Example;
import ru.nzuri.domain.profile.ExampleRepository;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultExampleService extends AbstractService<Example> implements ExampleService {

    @Inject
    private ExampleRepository exampleRepository;
    
    @Override
    protected EntityRepository getRepository() {
        return exampleRepository;
    }

    @Override
    protected Example createEmptyEntity() {
        return new Example();
    }
    
}
