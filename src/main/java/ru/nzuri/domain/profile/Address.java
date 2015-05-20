package ru.nzuri.domain.profile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import ru.nzuri.domain.AbstractEntity;

/**
 * Created by bad on 19.05.2015.
 */
@Entity
@Table(name = "addresses")
@NamedQueries({
    @NamedQuery(name = "Address.findAll",
        query = "Select c from Address c")
})
public class Address extends AbstractEntity {

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "building")
    private String building;

    @Column(name = "flat")
    private String flat;

    public Address() {
    }

    public void merge(Address address) {
        this.city = address.getCity();
        this.street = address.getStreet();
        this.house = address.getHouse();
        this.building = address.getBuilding();
        this.flat = address.getFlat();
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
