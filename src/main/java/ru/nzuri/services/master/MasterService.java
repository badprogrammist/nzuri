/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface MasterService extends EntityService<Master> {
    public Master get(User user);
    
}
