package ru.nzuri.domain.request;

import ru.nzuri.domain.AbstractEntity;
import ru.nzuri.domain.Price;
import ru.nzuri.domain.action.Action;

import javax.persistence.*;

/**
 * Created by bad on 06.06.2015.
 */
@Entity
@Table(name = "request_actions")
@Cacheable
@NamedQueries({
    @NamedQuery(name = "RequestAction.findAll",
        query = "Select c from RequestAction c"),
    @NamedQuery(name = "RequestAction.findByRequest",
        query = "Select c from RequestAction c where c.request = :request")
})
public class RequestAction extends AbstractEntity<RequestAction> {

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;

    @ManyToOne
    @JoinColumn(name = "action_id")
    private Action action;

    @Embedded
    private Price price;


    public RequestAction(Request request, Price price, Action action) {
        this.request = request;
        this.price = price;
        this.action = action;
    }

    public RequestAction() {

    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public void merge(RequestAction entity) {

    }
}
