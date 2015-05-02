/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.registration;

import ru.nzuri.domain.user.UserData;
import ru.nzuri.security.Credentials;

/**
 *
 * @author bad
 */
public interface RegistrationService {
    public boolean register(Credentials credentials, UserData userData, String role);
}
