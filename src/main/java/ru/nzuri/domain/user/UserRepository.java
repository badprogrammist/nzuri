/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.domain.user;

import ru.nzuri.domain.EntityRepository;


/**
 *
 * @author Ильдар
 */
public interface UserRepository extends EntityRepository<User> {
    public User findUserByLogin(String login);
}
