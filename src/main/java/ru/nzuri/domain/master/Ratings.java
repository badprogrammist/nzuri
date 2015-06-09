/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.master;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ildar
 */
@Embeddable
public class Ratings implements Serializable {
    
    public final double MAX_VALUE = 5.0;
    
    @Column(name = "experience")
    private Double experience;
    
    @Column(name = "politeness")
    private Double politeness;
    
    @Column(name = "price")
    private Double price;

    public Double getTotalRating() {
        return (experience + politeness + price) / 3;
    }
    
    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public Double getPoliteness() {
        return politeness;
    }

    public void setPoliteness(Double politeness) {
        this.politeness = politeness;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    
    
}
