/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.service;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.ServiceRepository;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author Ильдар
 */
@org.springframework.stereotype.Service
@Transactional
public class DefaultServiceService extends AbstractService<Service> implements ServiceService {

    @Inject
    private ServiceRepository serviceRepository;


    @Override
    protected EntityRepository getRepository() {
        return serviceRepository;
    }

    @Override
    public Service createEmptyEntity() {
        return new Service();
    }

}
