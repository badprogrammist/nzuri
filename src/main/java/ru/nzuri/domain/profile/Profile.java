/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.profile;

import java.util.ArrayList;
import java.util.List;
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
@Table(name = "profiles")
@NamedQueries({
    @NamedQuery(
        name = "Profile.findAll",
        query = "Select c from Profile c"
    ),
    @NamedQuery(
        name = "Profile.findByUser",
        query = "Select c from Profile c where c.user = :user"
    )
})
public class Profile extends AbstractEntity {

    public static final Profile NULL = new Profile(User.NULL);
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Example> examples = new ArrayList<>();

    public Profile() {
    }

    public Profile(User user) {
        this.user = user;
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
        if(address == null) {
            address = new Address();
        }
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
