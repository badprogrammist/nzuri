/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ru.nzuri.domain.user.User;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "comments")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "Comment.findAll",
            query = "Select c from Comment  c"),
    @NamedQuery(name = "Comment.findAllByMaster",
            query = "Select c from Comment c where c.master = :master")
})
public class Comment extends AbstractMasterCharacteristic<Comment> {

    @Column(name="content", columnDefinition = "text")
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    
    @Embedded
    private Ratings ratings = new Ratings();

    public Comment() {
    }

    public Comment(String content, User user, Master master) {
        super(master);
        this.content = content;
        this.user = user;
    }
    
    @Override
    public void merge(Comment entity) {
        this.content = entity.getContent();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Ratings getRatings() {
        return ratings;
    }

    public void setRatings(Ratings ratings) {
        this.ratings = ratings;
    }
    
    
    
}
