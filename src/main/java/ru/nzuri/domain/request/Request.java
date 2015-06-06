package ru.nzuri.domain.request;

import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bad on 06.06.2015.
 */
@Entity
@Table(name = "requests")
@NamedQueries({
    @NamedQuery(name = "Request.findAll",
        query = "Select c from Request c"),
    @NamedQuery(name = "Request.findByMaster",
        query = "Select c from Request c where c.master = :master"),
    @NamedQuery(name = "Request.findByOwner",
        query = "Select c from Request c where c.owner = :owner")
})
public class Request extends AbstractEntity<Request> {

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation = new Date();

    @OneToMany(mappedBy = "request",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<RequestAction> actions = new ArrayList<>();


    public Request(User owner, Master master) {
        this.owner = owner;
        this.master = master;
    }

    public Request() {
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<RequestAction> getActions() {
        return actions;
    }

    public void setActions(List<RequestAction> actions) {
        this.actions = actions;
    }

    @Override
    public void merge(Request entity) {

    }


}
