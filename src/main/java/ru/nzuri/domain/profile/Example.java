/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.profile;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.file.File;

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
    
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private File image;
    
    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    public Example(Profile profile, File image, String comment) {
        this.profile = profile;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
    
    
    
}
