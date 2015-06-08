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
import ru.nzuri.domain.file.File;
import ru.nzuri.domain.master.Example;
import ru.nzuri.domain.master.ExampleRepository;
import ru.nzuri.domain.master.Master;
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
    public Example addExample(Master master, File image, String comment) {
        Example example = new Example(master, image, comment);
        exampleRepository.store(example);
        return example;
    }
    
    @Override
    protected EntityRepository getRepository() {
        return exampleRepository;
    }
    
    public List<Example> getExamples(Master master) {
        return exampleRepository.findExamples(master);
    }

    @Override
    public Example createEmptyEntity() {
        return new Example();
    }
    
}
