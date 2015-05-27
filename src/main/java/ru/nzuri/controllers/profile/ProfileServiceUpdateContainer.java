/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.profile;

import java.util.ArrayList;
import java.util.List;
import ru.nzuri.domain.profile.ProfileSpecializationServiceRelation;

/**
 *
 * @author bad
 */
public class ProfileServiceUpdateContainer {

    private List<ProfileSpecializationServiceRelation> services = new ArrayList<>();

    public ProfileServiceUpdateContainer() {
    }

    public List<ProfileSpecializationServiceRelation> getServices() {
        return services;
    }

    public void setServices(List<ProfileSpecializationServiceRelation> services) {
        this.services = services;
    }

    
    
    
    
}
