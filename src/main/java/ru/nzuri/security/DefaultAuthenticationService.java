/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nzuri.security;

import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nzuri.domain.user.User;
import ru.nzuri.services.user.UserService;

/**
 *
 * @author Ильдар
 */
@Service
@Transactional
public class DefaultAuthenticationService implements AuthenticationService {

    public static final String CREDENTIALS_IS_NOT_VALID = "Credentials is not valid";
    
    @Inject
    private UserService userService;

    @Inject
//    @Autowired
//    @Qualifier("org.springframework.security.authenticationManager")
//    @Qualifier("org.springframework.security.authentication.ProviderManager#0")
    private AuthenticationManager authManager;

    @Override
    public UserTransfer authenticate(Credentials credentials) throws SecurityException {
        if (credentials.isValid()) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(credentials.getLogin(), credentials.getPassword());
            Authentication authentication = this.authManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, Boolean> roles = new HashMap<>();
            UserDetails userDetails = this.userService.loadUserByUsername(credentials.getLogin());
            userDetails.getAuthorities().stream().forEach((authority) -> {
                roles.put(authority.toString(), Boolean.TRUE);
            });
            return new UserTransfer(userDetails.getUsername(), roles, TokenUtils.createToken(userDetails));
        } else {
            throw new SecurityException(CREDENTIALS_IS_NOT_VALID);
        }
    }
    
    
    @Override
    public boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser";
    }
    
    @Override
    public User getPrincipal() {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return user;
        } catch (Exception ex) {
            return User.NULL;
        }
    }
}
