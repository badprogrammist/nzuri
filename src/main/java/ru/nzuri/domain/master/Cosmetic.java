/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "cosmetics")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "Cosmetic.findAll",
            query = "Select c from Cosmetic  c"),
    @NamedQuery(name = "Cosmetic.findAllByMaster",
            query = "Select c from Cosmetic  c where c.master = :master")
})
public class Cosmetic extends AbstractMasterCharacteristic<Cosmetic>{

    @Column(name = "title")
    private String title;
    
    public Cosmetic(Master master) {
        super(master);
    }

    public Cosmetic() {
    }
    
    
    @Override
    public void merge(Cosmetic entity) {
        this.title = entity.getTitle();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    

    
    
    
    
}
