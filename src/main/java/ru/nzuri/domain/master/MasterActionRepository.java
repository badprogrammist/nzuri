/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.Specialization;

/**
 *
 * @author bad
 */
public interface MasterActionRepository extends EntityRepository<MasterAction> {
    public MasterSpecialization findMasterSpecializaion(Master master, Specialization specialization);
    public MasterAction find(Master master, Action action);
    public void store(MasterSpecialization masterSpecialization);
    public void remove(MasterSpecialization masterSpecialization);
}
