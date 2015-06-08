/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.file.File;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "examples")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "Example.findAll",
            query = "Select c from Example  c"),
    @NamedQuery(name = "Example.findAllByMaster",
            query = "Select c from Example c where c.master = :master")
})
public class Example extends AbstractMasterCharacteristic<Example> {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private File image;
    
    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    public Example(Master master, File image, String comment) {
        super(master);
        this.image = image;
        this.comment = comment;
    }

    public Example() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public void merge(Example entity) {
        this.comment = entity.comment;
    }
    
    
    
}
