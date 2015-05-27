/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.profile;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.service.Specialization;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "profile_specialization_relations")
@NamedQueries({
    @NamedQuery(
            name = "ProfileSpecializationRelation.findAll",
            query = "Select c from ProfileSpecializationRelation c"),
    @NamedQuery(
            name = "ProfileSpecializationRelation.findByProfileAndSpecialization",
            query = "Select c from ProfileSpecializationRelation c where c.profile = :profile and c.specialization = :specialization")
})
public class ProfileSpecializationRelation extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    @OneToMany(mappedBy = "profileSpecializationRelation", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProfileSpecializationServiceRelation> profileServices = new ArrayList<>();

    public ProfileSpecializationRelation(Profile profile, Specialization specialization) {
        this.profile = profile;
        this.specialization = specialization;
    }

    public ProfileSpecializationRelation() {
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<ProfileSpecializationServiceRelation> getProfileServices() {
        return profileServices;
    }

    public void setProfileServices(List<ProfileSpecializationServiceRelation> profileServices) {
        this.profileServices = profileServices;
    }

}
