/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.security;

import org.springframework.security.core.context.SecurityContextHolder;
import ru.nzuri.domain.user.User;

/**
 *
 * @author Ильдар
 */
public class SecurityContext {
    public static User getPrincipal() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
