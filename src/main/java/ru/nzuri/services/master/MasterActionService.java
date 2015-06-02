/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.master;

import java.util.List;
import ru.nzuri.domain.Price;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.ActionData;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.domain.action.SpecializationData;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.master.MasterAction;
import ru.nzuri.domain.master.MasterSpecialization;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface MasterActionService extends EntityService<MasterAction> {
    
    public MasterSpecialization getMasterSpecializaion(Master master, Specialization specialization);
    public MasterAction get(Master master, Action service);
    public List<Specialization> getAttachCandidates(Master master);
    public MasterAction attach(Master master, Action action,Price price);
    public void detach(Master master, Action action);
    
    public void createCustomSpecialization(Master master, SpecializationData specializationData);
    public void createCustomAction(Master master, Specialization specialization, ActionData actionData, Price price);
}
