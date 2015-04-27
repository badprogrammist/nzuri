/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.nzuri.domain.user.Role;
import ru.nzuri.domain.user.User;
import ru.nzuri.domain.user.UserCredential;
import ru.nzuri.domain.user.UserData;
import ru.nzuri.services.EntityService;

/**
 *
 * @author Ильдар
 */
public interface UserService extends EntityService<User>,UserDetailsService {
    public void registerNewUser(UserCredential credential, UserData data, Role role);
    public Role getRoleByName(String name);
    public Role createRole(String name);
}
