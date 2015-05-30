/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import ru.nzuri.domain.AbstractEntity;

/**
 *
 * @author bad
 */
@MappedSuperclass
public abstract class AbstractMasterCharacteristic<E extends AbstractMasterCharacteristic> extends AbstractEntity<E> {
    
    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    public AbstractMasterCharacteristic(Master master) {
        this.master = master;

    }

    public AbstractMasterCharacteristic() {
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

   
    
    
}
