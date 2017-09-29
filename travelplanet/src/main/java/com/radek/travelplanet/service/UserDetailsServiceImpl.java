package com.radek.travelplanet.service;

import com.radek.travelplanet.model.Employee;
import com.radek.travelplanet.model.Role;
import com.radek.travelplanet.model.State;
import com.radek.travelplanet.repository.EmployeeRepository;
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
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String coreId) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByCoreId(coreId);
        if (employee == null) {
            LOGGER.debug("Employee not found! coreId: " + coreId);
            throw new UsernameNotFoundException("Username not found");
        }
        LOGGER.debug("Got employee. coreId: " + coreId);
        boolean enabled = employee.getState() == State.ACTIVE;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        Set<Role> roles = employee.getRolesSet();
        if (roles.isEmpty()) {
            LOGGER.debug("Employee Roles: [EMPTY]");
        }

        return new User(
                employee.getCoreId(),
                employee.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(roles)
        );
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
