/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.action;

import java.util.List;
import ru.nzuri.domain.EntityRepository;

/**
 *
 * @author bad
 */
public interface ActionRepository extends EntityRepository<Action> {
    public List<Action> findCommonActions(Specialization specialization);
}
