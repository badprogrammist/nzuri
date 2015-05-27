/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.user.User;

/**
 *
 * @author bad
 */
public interface MasterRepository extends EntityRepository<Master> {
    public Master findByUser(User user);
}
