/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.service;

import java.util.List;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface ServiceService extends EntityService<Service> {
    public List<Service> getServices(Specialization specialization);
}