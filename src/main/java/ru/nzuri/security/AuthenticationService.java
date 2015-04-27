/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.security;

import ru.nzuri.domain.user.User;


/**
 *
 * @author Ильдар
 */
public interface AuthenticationService {
    
    public UserTransfer authenticate(Credentials credentials) throws SecurityException;
    
    public boolean isAuthenticated();
    
    public User getPrincipal();
}
