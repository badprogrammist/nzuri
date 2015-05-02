/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.profile;

import ru.nzuri.domain.profile.Profile;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface ProfileService extends EntityService<Profile> {
    public Profile getProfile(User user);
    
}
