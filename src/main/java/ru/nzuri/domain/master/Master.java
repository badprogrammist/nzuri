/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.user.User;

/**
 *
 * @author bad
 */
@Entity
@Table(name = "masters")
@Cacheable
@NamedQueries({
    @NamedQuery(
            name = "Master.findAll",
            query = "Select c from Master c"
    ),
    @NamedQuery(
            name = "Master.findByUser",
            query = "Select c from Master c where c.user = :user"
    )
})
public class Master extends AbstractEntity<Master> {

    public static final Master NULL = new Master(User.NULL);

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Example> examples = new ArrayList<>();

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MasterSpecialization> specializations = new ArrayList<>();
    
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();
    
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cosmetic> cosmetics = new ArrayList<>();
 
    public Master() {
    }

    public Master(User user) {
        this.user = user;
    }

    public double getTotalRating() {
        Double totalRating = 0.0;
        for(Comment comment : comments) {
            totalRating += comment.getRatings().getTotalRating();
        }
        totalRating /= comments.size();
        return totalRating;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Example> getExamples() {
        return examples;
    }

    public void setExamples(List<Example> examples) {
        this.examples = examples;
    }

    public Address getAddress() {
        if (address == null) {
            address = new Address();
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MasterSpecialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<MasterSpecialization> specializations) {
        this.specializations = specializations;
    }

    @Override
    public void merge(Master entity) {
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Cosmetic> getCosmetics() {
        return cosmetics;
    }

    public void setCosmetics(List<Cosmetic> cosmetics) {
        this.cosmetics = cosmetics;
    }

}
