/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.action;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Specialization extends AbstractEntity<Specialization> {
    
    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "specialization",cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();
    
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

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void merge(Specialization entity) {
        this.title = entity.getTitle();
    }
    
    
}
