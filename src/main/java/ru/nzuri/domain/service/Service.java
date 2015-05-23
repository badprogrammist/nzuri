/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.service;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "services")
@NamedQueries({
    @NamedQuery(name = "Service.findAll",
            query = "Select c from Service c")
})
public class Service extends AbstractEntity {
    
    @Column(name="title")
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    public Service() {
    }

    public Service(String title, Specialization specialization) {
        this.title = title;
        this.specialization = specialization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
    
}
