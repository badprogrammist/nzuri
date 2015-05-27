/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.profile;

import javax.inject.Inject;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.profile.Profile;
import ru.nzuri.domain.profile.ProfileSpecializationRelation;
import ru.nzuri.domain.profile.ProfileSpecializationRepository;
import ru.nzuri.domain.profile.ProfileSpecializationServiceRelation;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author bad
 */
@org.springframework.stereotype.Service
@Transactional
public class DefaultProfileSpecializationService extends AbstractService<ProfileSpecializationRelation> implements ProfileSpecializationService{

    @Inject
    private ProfileSpecializationRepository profileSpecializationRepository;
    
    @Override
    public ProfileSpecializationRelation getProfileSpecializaion(Profile profile,Specialization specialization) {
        return profileSpecializationRepository.findProfileSpecializaion(profile, specialization);
    }

    @Override
    public ProfileSpecializationServiceRelation getProfileService(Profile profile,Service service) {
        return profileSpecializationRepository.findProfileService(profile, service);
    }
    
    @Override
    public ProfileSpecializationRelation createEmptyEntity() {
        return new ProfileSpecializationRelation();
    }
    
    @Override
    protected EntityRepository<ProfileSpecializationRelation> getRepository() {
        return profileSpecializationRepository;
    }

    @Override
    public ProfileSpecializationServiceRelation getProfileService(Long id) {
        return profileSpecializationRepository.findProfileService(id);
    }

    @Override
    public ProfileSpecializationServiceRelation updateProfileService(ProfileSpecializationServiceRelation profileSpecializationService) {
        return profileSpecializationRepository.updateProfileService(profileSpecializationService);
    }
    
}
