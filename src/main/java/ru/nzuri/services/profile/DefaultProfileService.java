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
import ru.nzuri.domain.profile.Profile;
import ru.nzuri.domain.profile.ProfileRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultProfileService extends AbstractService<Profile> implements ProfileService {

    @Inject
    private ProfileRepository profileRepository;
    
    @Override
    public Profile getProfile(User user) {
        Profile profile = profileRepository.findByUser(user);
        if(profile.equals(Profile.NULL)) {
            profile = new Profile(user);
            store(profile);
        }
        return profile;
    }

    
    
    @Override
    protected EntityRepository getRepository() {
        return profileRepository;
    }

    @Override
    protected Profile createEmptyEntity() {
        return new Profile();
    }
}
