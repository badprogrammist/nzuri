/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.master.MasterSpecialization;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface MasterActionService extends EntityService<MasterAction> {
    public MasterSpecialization getMasterSpecializaion(Master profile, Specialization specialization);
    public MasterAction get(Master profile, Action service);
}
