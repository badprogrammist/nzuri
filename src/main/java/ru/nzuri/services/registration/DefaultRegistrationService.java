/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.registration;

import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.user.Role;
import ru.nzuri.domain.user.Roles;
import ru.nzuri.domain.user.UserCredential;
import ru.nzuri.domain.user.UserData;
import ru.nzuri.security.Credentials;
import ru.nzuri.services.user.UserService;

/**
 *
 * @author bad
 */
@Service
@Transactional
public class DefaultRegistrationService implements RegistrationService {

    @Inject
    private PasswordEncoder passwordEncoder;
    
    @Inject
    private UserService userService;
    
    @Override
    public boolean register(Credentials credentials, UserData userData) {
        if (credentials.isValid()) {
            UserDetails userDetails = this.userService.loadUserByUsername(credentials.getLogin());
            if (userDetails == null) {
                Role userRole = userService.createRole(Roles.ROLE_USER.name());
                UserCredential userCredential = createUserCredential(credentials);
                if (userRole != null) {
                    userService.registerNewUser(userCredential,userData, userRole);
                    return true;
                }
            }
        }
        return false;
        
    }
    
    private UserCredential createUserCredential(Credentials credentials) {
        String encodedPassword = passwordEncoder.encode(credentials.getPassword());
        return new UserCredential(credentials.getLogin(), encodedPassword);
    }
    
}
