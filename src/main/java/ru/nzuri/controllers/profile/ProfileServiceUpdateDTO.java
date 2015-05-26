/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.profile;

import java.math.BigDecimal;

/**
 *
 * @author bad
 */
public class ProfileServiceUpdateDTO {

    private String price;
    private Long serviceId;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }
    
    
    
}
