/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.profile;

import ru.nzuri.domain.profile.ProfileSpecializationRelation;
import ru.nzuri.domain.service.Specialization;

/**
 *
 * @author bad
 */
public interface ProfileSpecializationService {
    public ProfileSpecializationRelation getProfileSpecializaion(Specialization specialization);
}
