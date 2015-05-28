/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultMasterService extends AbstractService<Master> implements MasterService {

    @Inject
    private MasterRepository masterRepository;
    
    @Override
    public Master get(User user) {
        Master master = masterRepository.findByUser(user);
        if(master.equals(Master.NULL)) {
            master = new Master(user);
            store(master);
        }
        return master;
    }

    
    
    @Override
    protected EntityRepository getRepository() {
        return masterRepository;
    }

    @Override
    public Master createEmptyEntity() {
        return new Master();
    }
}
