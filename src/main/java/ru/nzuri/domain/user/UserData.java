/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.user;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ильдар
 */
@Embeddable
public class UserData implements Serializable {

    @Column(name = "name",nullable = false)
    private String name;
    
    @Column(name = "lastname",nullable = false)
    private String lastname;
    
    @Column(name = "patronymic",nullable = false)
    private String patronymic;
    
    public UserData() {
    }

    public UserData(String name, String lastname, String patronymic) {
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    
    
}
