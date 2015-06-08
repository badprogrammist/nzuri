package ru.nzuri.domain.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.master.Master;
import ru.nzuri.domain.user.User;

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
    
    @Column(name = "exercise_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date exerciseDate;

    @OneToMany(mappedBy = "request",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<RequestAction> actions = new ArrayList<>();


    public Request(User owner, Master master,Date exerciseDate) {
        this.owner = owner;
        this.master = master;
        this.exerciseDate = exerciseDate;
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

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }


}
