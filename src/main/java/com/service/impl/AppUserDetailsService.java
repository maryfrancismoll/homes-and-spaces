package com.service.impl;

import com.domain.AppUserDetails;
import com.domain.Role;
import com.domain.User;
import com.domain.UserInformation;
import com.domain.UserRole;
import com.repository.UserInformationRepository;
import com.repository.UserRepository;
import com.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maryfrancis Remo Moll
 *
 */
@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    /*
        This method performs the check on the database if the user does exist or not
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", userName));
        }

        //get user's default company
        UserInformation userInformation = userInformationRepository.findByUserId(user.getId());

        //get roles for default company
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();

        List<Role> roles = new ArrayList<>();
        userRoles.forEach(userRole -> {
            roles.add(userRole.getRole());
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getName()));
        });

        user.setRoles(roles);

        return new AppUserDetails(user);
    }

}
