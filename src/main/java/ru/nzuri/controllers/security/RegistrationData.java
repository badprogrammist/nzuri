/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.controllers.security;

import ru.nzuri.domain.file.File;
import ru.nzuri.security.Credentials;

/**
 *
 * @author bad
 */
public class RegistrationData extends Credentials {

    private String name;
    
    private String lastname;
    
    private String patronymic;
    
    private File icon;

    public RegistrationData(File icon, String name, String lastname, String patronymic, String login, String password) {
        super(login, password);
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.icon = icon;
    }
    
    public RegistrationData() {
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

    public File getIcon() {
        return icon;
    }

    public void setIcon(File icon) {
        this.icon = icon;
    }

   
    
    
    
}
