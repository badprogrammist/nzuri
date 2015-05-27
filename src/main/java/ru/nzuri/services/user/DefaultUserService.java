/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.services.user;

import javax.inject.Inject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.EntityRepository;
import ru.nzuri.domain.user.Role;
import ru.nzuri.domain.user.RoleRepository;
import ru.nzuri.domain.user.User;
import ru.nzuri.domain.user.UserCredential;
import ru.nzuri.domain.user.UserData;
import ru.nzuri.domain.user.UserRepository;
import ru.nzuri.domain.user.UserRole;
import ru.nzuri.services.AbstractService;

/**
 *
 * @author Ильдар
 */
@Service(value = "userService")
@Transactional
public class DefaultUserService extends AbstractService<User> implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Override
    public void registerNewUser(UserCredential credential, UserData data, Role role) {
        User user = new User(credential,data);
        UserRole roleRelation = new UserRole(user, role);
        user.getRoles().add(roleRelation);
        userRepository.store(user);
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getByName(name);
    }

    @Override
    public Role createRole(String name) {
        Role role = roleRepository.getByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            roleRepository.store(role);
        }
        return role;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDetails userDetails = userRepository.findUserByLogin(username);
        return userDetails;
    }

    @Override
    protected EntityRepository getRepository() {
        return userRepository;
    }

    @Override
    public User createEmptyEntity() {
        return new User();
    }

}
