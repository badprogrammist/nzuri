/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.profile;

import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.Specialization;

/**
 *
 * @author bad
 */
public interface ProfileSpecializationRepository extends EntityRepository<ProfileSpecializationRelation> {
    public ProfileSpecializationRelation findProfileSpecializaion(Profile profile, Specialization specialization);
    public ProfileSpecializationServiceRelation findProfileService(Profile profile, Service service);
    public ProfileSpecializationServiceRelation findProfileService(Long id);
    public ProfileSpecializationServiceRelation updateProfileService(ProfileSpecializationServiceRelation profileSpecializationService);
}
