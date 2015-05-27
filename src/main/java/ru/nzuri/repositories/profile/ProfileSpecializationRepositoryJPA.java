/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.repositories.profile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import ru.nzuri.domain.profile.Profile;
import ru.nzuri.domain.profile.ProfileSpecializationRelation;
import ru.nzuri.domain.profile.ProfileSpecializationRepository;
import ru.nzuri.domain.profile.ProfileSpecializationServiceRelation;
import ru.nzuri.domain.service.Service;
import ru.nzuri.domain.service.Specialization;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class ProfileSpecializationRepositoryJPA extends AbstractRepositoryJPA<ProfileSpecializationRelation> implements ProfileSpecializationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ProfileSpecializationRepositoryJPA() {
        super(ProfileSpecializationRelation.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public ProfileSpecializationRelation findProfileSpecializaion(Profile profile, Specialization specialization) {
        try {
            return getEntityManager().createNamedQuery("ProfileSpecializationRelation.findByProfileAndSpecialization", ProfileSpecializationRelation.class)
                    .setParameter("specialization", specialization)
                    .setParameter("profile", profile)
                    .getSingleResult();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public ProfileSpecializationServiceRelation findProfileService(Profile profile, Service service) {
        try {
            return getEntityManager().createNamedQuery("ProfileSpecializationServiceRelation.findByServiceAndProfile", ProfileSpecializationServiceRelation.class)
                    .setParameter("service", service)
                    .setParameter("profile", profile)
                    .getSingleResult();
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public ProfileSpecializationServiceRelation findProfileService(Long id) {
        return getEntityManager().find(ProfileSpecializationServiceRelation.class, id);
    }

    @Override
    public ProfileSpecializationServiceRelation updateProfileService(ProfileSpecializationServiceRelation profileSpecializationService) {
        return getEntityManager().merge(profileSpecializationService);
    }
    
}
