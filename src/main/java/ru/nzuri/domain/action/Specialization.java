/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.action;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import ru.nzuri.domain.AbstractEntity;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "specializations")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "Specialization.findAll",
            query = "Select c from Specialization c"),
    @NamedQuery(name = "Specialization.findAllByOwnType",
            query = "Select c from Specialization c where c.ownType = :ownType")
})
public class Specialization extends AbstractEntity<Specialization> {
    
    @Embedded
    private SpecializationData data = new SpecializationData();
    
    @Column(name = "own_type")
    @Enumerated(EnumType.STRING)
    private SpecializationOwnType ownType = SpecializationOwnType.COMMON;

    @OneToMany(mappedBy = "specialization",cascade = CascadeType.REMOVE)
    private List<Action> actions = new ArrayList<>();
    
    public Specialization() {
    }

    public Specialization(SpecializationData data, SpecializationOwnType ownType) {
        this.ownType = ownType;
        this.data = data;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void merge(Specialization entity) {
        this.data = entity.getData();
    }

    public SpecializationOwnType getOwnType() {
        return ownType;
    }

    public void setOwnType(SpecializationOwnType ownType) {
        this.ownType = ownType;
    }

    public SpecializationData getData() {
        return data;
    }

    public void setData(SpecializationData data) {
        this.data = data;
    }
    
    
}
