/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.Price;
import ru.nzuri.domain.action.Action;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "master_actions")
@NamedQueries({
    @NamedQuery(name = "MasterAction.findAll",
            query = "Select c from MasterAction c"),
    @NamedQuery(name = "MasterAction.findByServiceAndMaster",
            query = "Select c from MasterAction c where c.masterSpecialization.master = :master and c.action = :action")
})
public class MasterAction extends AbstractEntity<MasterAction> {
    
    @ManyToOne
    @JoinColumn(name = "master_specialization_id")
    private MasterSpecialization masterSpecialization;
    
    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;
    
    @Embedded
    private Price price = new Price();

    public MasterAction(MasterSpecialization masterSpecialization, Action action) {
        this.masterSpecialization = masterSpecialization;
        this.action = action;
    }

    public MasterAction() {
    }

    @Override
    public void merge(MasterAction masterAction) {
        this.price = masterAction.getPrice();
    }
    
    public MasterSpecialization getMasterSpecialization() {
        return masterSpecialization;
    }

    public void setMasterSpecialization(MasterSpecialization masterSpecialization) {
        this.masterSpecialization = masterSpecialization;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
    
}
