/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.action;

import java.util.List;
import ru.nzuri.domain.action.Action;
import ru.nzuri.domain.action.Specialization;
import ru.nzuri.services.EntityService;

/**
 *
 * @author bad
 */
public interface ActionService extends EntityService<Action> {
    public List<Action> getCommonActions(Specialization specialization);
    public List<Specialization> getDecomposedCommonActions();
}
