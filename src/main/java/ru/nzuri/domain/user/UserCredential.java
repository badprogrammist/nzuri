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
public class UserCredential implements Serializable {

    @Column(name = "login",unique = true)
    private String login;
    
    @Column(name="encoded_password")
    private String encodedPassword;
    
    public UserCredential(String login, String password) {
        this.login = login;
        this.encodedPassword = password;
    }
    
    public UserCredential() {
    }
    
    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

}
