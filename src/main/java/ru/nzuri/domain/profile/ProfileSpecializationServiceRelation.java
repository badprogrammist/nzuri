/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.profile;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.Price;
import ru.nzuri.domain.service.Service;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "profile_specialization_service_relations")
@NamedQueries({
    @NamedQuery(name = "ProfileSpecializationServiceRelation.findAll",
            query = "Select c from ProfileSpecializationServiceRelation c"),
    @NamedQuery(name = "ProfileSpecializationServiceRelation.findByServiceAndProfile",
            query = "Select c from ProfileSpecializationServiceRelation c where c.profileSpecializationRelation.profile = :profile and c.service = :service")
})
public class ProfileSpecializationServiceRelation extends AbstractEntity {
    
    @ManyToOne
    @JoinColumn(name = "profile_specialization_relation_id")
    private ProfileSpecializationRelation profileSpecializationRelation;
    
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;
    
    @Embedded
    private Price price = new Price();

    public ProfileSpecializationServiceRelation(ProfileSpecializationRelation profileSpecializationRelation, Service service) {
        this.profileSpecializationRelation = profileSpecializationRelation;
        this.service = service;
    }

    public ProfileSpecializationServiceRelation() {
    }

    public void merge(ProfileSpecializationServiceRelation profileSpecializationService) {
        this.price = profileSpecializationService.getPrice();
    }
    
    public ProfileSpecializationRelation getProfileSpecializationRelation() {
        return profileSpecializationRelation;
    }

    public void setProfileSpecializationRelation(ProfileSpecializationRelation profileSpecializationRelation) {
        this.profileSpecializationRelation = profileSpecializationRelation;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    
}
