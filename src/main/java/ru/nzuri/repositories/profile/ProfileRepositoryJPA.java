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
import ru.nzuri.domain.profile.ProfileRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.repositories.AbstractRepositoryJPA;

/**
 *
 * @author bad
 */
@Repository
public class ProfileRepositoryJPA extends AbstractRepositoryJPA<Profile> implements ProfileRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public ProfileRepositoryJPA() {
        super(Profile.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public Profile findByUser(User user) {
        try {
            return getEntityManager().createNamedQuery("Profile.findByUser", Profile.class)
                    .setParameter("user", user)
                    .getSingleResult();
        } catch(Exception ex) {
            return Profile.NULL;
        }
    }
    
}
