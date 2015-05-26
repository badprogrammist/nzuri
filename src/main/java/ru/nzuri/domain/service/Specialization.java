/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "specializations")
@NamedQueries({
    @NamedQuery(name = "Specialization.findAll",
            query = "Select c from Specialization c")
})
public class Specialization extends AbstractEntity {
    
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "specialization",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services = new ArrayList<>();
    
    public Specialization() {
    }

    public Specialization(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    
}
