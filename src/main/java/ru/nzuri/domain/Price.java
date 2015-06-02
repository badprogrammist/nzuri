/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author bad
 */
@Embeddable
public class Price implements Serializable {
    
    public static Price NULL = new Price();
    
    @Column(name = "price_value", scale = 2, precision = 10)
    private BigDecimal value = new BigDecimal(0.0);

    public Price() {
    }

    public Price(BigDecimal value) {
        this.value = value;
    }
    
    
    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
}
