package com.radek.travelplanet.service.auth;

import com.radek.travelplanet.model.Role;
import com.radek.travelplanet.model.State;
import com.radek.travelplanet.model.UserAccount;
import com.radek.travelplanet.repository.UserAccountRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        if (userAccount == null) {
            LOGGER.error("UserAccount not found! email: " + email);
            throw new UsernameNotFoundException("Username not found");
        }
        LOGGER.debug("Got userAccount. email: " + email);
        boolean enabled = userAccount.getState() == State.ACTIVE;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Set<Role> roles = userAccount.getRolesSet();
        if (roles.isEmpty()) {
            LOGGER.warn("UserAccount Roles: [EMPTY]");
        }

        return new User(userAccount.getEmail(),
                        userAccount.getPassword(),
                        enabled,
                        accountNonExpired,
                        credentialsNonExpired,
                        accountNonLocked,
                        getAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        return getGrantedAuthorities(getRoles(roles));
    }

    private List<String> getRoles(Set<Role> role) {
        return role.stream().map(r -> "ROLE_" + r.getRoleType().toString()).collect(Collectors.toList());
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
