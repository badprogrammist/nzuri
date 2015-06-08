/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.action;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "actions")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "Action.findAll",
            query = "Select c from Action c"),
    @NamedQuery(name = "Action.findAllBySpecialization",
            query = "Select c from Action c where c.specialization = :specialization and c.ownType = :ownType")
})
public class Action extends AbstractEntity<Action> {
    
    @Embedded
    private ActionData data = new ActionData();
    
    @Column(name = "own_type")
    @Enumerated(EnumType.STRING)
    private ActionOwnType ownType = ActionOwnType.COMMON;
    
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    public Action() {
    }

    public Action(ActionData data,ActionOwnType ownType, Specialization specialization) {
        this.data = data;
        this.specialization = specialization;
        this.ownType = ownType;
    }

    @Override
    public void merge(Action action) {
        this.data = action.getData();
    }
    
    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public ActionOwnType getOwnType() {
        return ownType;
    }

    public void setOwnType(ActionOwnType ownType) {
        this.ownType = ownType;
    }

    public ActionData getData() {
        return data;
    }

    public void setData(ActionData data) {
        this.data = data;
    }
    
}
