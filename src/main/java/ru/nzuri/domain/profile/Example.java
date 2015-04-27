/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "examples")
@NamedQueries({
    @NamedQuery(name = "Example.findAll",
            query = "Select c from Example  c")
})
public class Example extends AbstractEntity {
    
    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    public Example() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
